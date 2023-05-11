package gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author PengFuLin
 * @description 自定义全局路由过滤器
 * @date 2022/5/2 18:32
 */
@Component
public class CustomFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("前置处理");
        Mono<Void> filter = chain.filter(exchange);
        System.out.println("后置处理");
        return filter;
    }
    //设置优先级
    @Override
    public int getOrder() {
        return 0;
    }
}
