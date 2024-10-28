package gateway.filter;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.Map;

/**
 * 设置请求头过滤器
 *
 * @author pengshuaifeng
 * 2024/10/28
 */
@Component
public class AddHeaderFilter  implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Map<String,String> headers = Collections.singletonMap("X-Request-Foo","bar");
        return chain.filter(setWithHeader(exchange, headers));
    }

    private ServerWebExchange  setWithHeader(ServerWebExchange exchange, Map<String,String> headers){
        ServerHttpRequest.Builder mutate = exchange.getRequest().mutate();
        headers.forEach(mutate::header);
        ServerHttpRequest req = mutate.build();
        return exchange.mutate().request(req).build();
    }

    //设置优先级
    @Override
    public int getOrder() {
        return 0;
    }

}
