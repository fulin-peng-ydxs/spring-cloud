package gateway.filter;

import gateway.dto.GatewayContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.buffer.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.multipart.Part;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
 

/**请求体全局缓存过滤器
 * 2023/5/11 0011-13:45
 * @author pengfulin
*/
@Slf4j
@Component
public class CacheBodyGlobalFilter implements Ordered, GlobalFilter {
 
    private final List<HttpMessageReader<?>> messageReaders;

    private final ParameterizedTypeReference<MultiValueMap<String, Part>> MULTI_PART = new ParameterizedTypeReference<MultiValueMap<String, Part>>(){};
 
    public CacheBodyGlobalFilter(ServerCodecConfigurer configurer) {
        this.messageReaders = configurer.getReaders();
    }
 
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MediaType contentType = request.getHeaders().getContentType();
        //缓存 json 和 multipart 表单两种请求类型
        if (Objects.nonNull(contentType) && Objects.nonNull(request.getMethod()) && request.getMethod().equals(HttpMethod.POST)) {
            GatewayContext gatewayContext = new GatewayContext();
            exchange.getAttributes().put(GatewayContext.CACHE_GATEWAY_CONTEXT, gatewayContext);
            if (MediaType.MULTIPART_FORM_DATA.isCompatibleWith(contentType)) {
                return readMultiPartFormData(exchange, chain, gatewayContext);
            } else if (MediaType.APPLICATION_JSON.isCompatibleWith(contentType)) {
                return readBody(exchange, chain, gatewayContext);
            }else{
                exchange.getAttributes().remove(GatewayContext.CACHE_GATEWAY_CONTEXT);
            }
        }
        return chain.filter(exchange);
    }


    /**缓存form-data数据
     * 2023/5/11 0011-13:58
     * @author pengfulin
    */
    private Mono<Void> readMultiPartFormData(ServerWebExchange exchange, GatewayFilterChain chain, GatewayContext gatewayContext) {
        return getRequestBody(exchange).flatMap(dataBuffer -> {
            //包装请求并放入到新创建的ServerWebExchange
            ServerWebExchange mutatedExchange = exchange.mutate().request(getServerHttpRequestDecorator(dataBuffer,exchange)).build();
            //从新的ServerWebExchange获取请求体中的数据，并将其放入到属性中缓存起来，可供下游过滤器直接通过属性获取以解析好指定格式的请求数据
            return ServerRequest.create(mutatedExchange, messageReaders).bodyToMono(MULTI_PART)
                    .doOnNext(gatewayContext::setMultiPartParams).then(chain.filter(mutatedExchange));
        });
    }

    /**缓存json数据
     * 2023/5/11 0011-13:58
     * @author pengfulin
    */
    private Mono<Void> readBody(ServerWebExchange exchange, GatewayFilterChain chain, GatewayContext gatewayContext) {
        return getRequestBody(exchange).flatMap(dataBuffer -> {
            //包装请求并放入到新创建的ServerWebExchange
            ServerWebExchange mutatedExchange = exchange.mutate().request(getServerHttpRequestDecorator(dataBuffer,exchange)).build();
            //从新的ServerWebExchange获取请求体中的数据，并将其放入到属性中缓存起来，可供下游过滤器直接通过属性获取以解析好指定格式的请求数据
            return ServerRequest.create(mutatedExchange, messageReaders)
                    .bodyToMono(String.class)
                    .doOnNext(gatewayContext::setJsonBody).then(chain.filter(mutatedExchange));
        });
    }

    /**获取请求体数据
     * 2023/5/11 0011-14:29
     * @author pengfulin
    */
    private Mono<DataBuffer> getRequestBody(ServerWebExchange exchange){
        DefaultDataBuffer defaultDataBuffer = new DefaultDataBufferFactory().allocateBuffer(0);
        return Flux.from(exchange.getRequest().getBody().defaultIfEmpty(defaultDataBuffer))
                .collectList().map(list -> list.get(0).factory().join(list)).doOnDiscard(PooledDataBuffer.class, DataBufferUtils::release);
    }


    /**包装请求
     * <p>重写getBody方法，将原始的请求数据提取出来后即可缓存到包装请求中，并将包装的请求放入
     * 过滤器链中传递，以便下游过滤器可多次读取请求中的缓存数据
     * </p>
     * 2023/5/11 0011-14:14
     * @author pengfulin
    */
    private ServerHttpRequestDecorator getServerHttpRequestDecorator(DataBuffer dataBuffer,ServerWebExchange exchange){
        byte[] bytes = new byte[dataBuffer.readableByteCount()];
        dataBuffer.read(bytes);
        DataBufferUtils.release(dataBuffer);
        return new ServerHttpRequestDecorator(exchange.getRequest()) {
            @Override
            public Flux<DataBuffer> getBody() {
                return Flux.defer(() -> {
                    DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                    DataBufferUtils.retain(buffer);
                    return Mono.just(buffer);
                });
            }
        };
    }

    @Override
    public int getOrder() {   //优先级最低
        return Ordered.HIGHEST_PRECEDENCE;
    }
}