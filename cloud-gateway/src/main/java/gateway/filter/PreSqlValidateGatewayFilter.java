package gateway.filter;

import gateway.dto.GatewayContext;
import gateway.dto.ResponseDto;
import gateway.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.regex.Pattern;

/**sql注入攻击过滤器
 * @author peng_fu_lin
 * 2023-05-10 13:36
 */
@Component
public class PreSqlValidateGatewayFilter  extends AbstractGatewayFilterFactory<PreSqlValidateGatewayFilter.Config> implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(PreSqlValidateGatewayFilter.class);

    private static final Pattern SQL_INJECTION_REGEX = Pattern.compile("^[a-zA-Z,]*$");

    public PreSqlValidateGatewayFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            GatewayContext cacheGatewayContext = (GatewayContext)exchange.getAttributes().get("cacheGatewayContext");
            if(cacheGatewayContext!=null){
                logger.debug("进行SQL注入攻击过滤....");
                Map<String,Object> map = JsonUtil.toBean(Map.class, cacheGatewayContext.getJsonBody());
                Object sortName = map.get("sortName");
                Object sortOrder = map.get("sortOrder");
                if (sqlInjectionAttack(sortOrder)||sqlInjectionAttack(sortName)) {
                    //SQL注入攻击
                    logger.warn("检测到sql注入攻击或存在sql注入风险，已拦截请求：attackParam：{}、attackAddress：{}",
                            cacheGatewayContext.getJsonBody(), exchange.getRequest().getRemoteAddress());
                    return responseToClient(
                            ResponseDto.failure("非法参数",null),
                            exchange.getResponse(),
                            HttpStatus.UNAUTHORIZED
                    );
                }
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public int getOrder() {
        return -1;
    }


    /**设置请求响应
     * 2023/5/11 0011-11:11
     * @author pengfulin
    */
    private Mono<Void> responseToClient(ResponseDto<?> responseDto, ServerHttpResponse response, HttpStatus status)
    {
        //设置响应状态码
        response.setStatusCode(status);
        //设置响应headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        //设置响应body
        String respStr = JsonUtil.toJson(responseDto);
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(respStr.getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**校验是否存在sql注入
     * 2023/5/10 0010-13:59
     * @author pengfulin
    */
    private boolean sqlInjectionAttack(Object param){
        if(param instanceof String){
            return !SQL_INJECTION_REGEX.matcher((String)param).find();
        }
        return  false;
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}