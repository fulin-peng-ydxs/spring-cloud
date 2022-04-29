package start.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import start.ServiceApi;

/**
 * @author PengFuLin
 * @description hystrixi调用服务
 * @date 2022/4/28 1:43
 */
@Component
//作为feign客户端
@FeignClient(value = "client-cluster",
        fallback = HystrixServiceFallBack.class)
//HystrixServiceFallBack：当服务端没有降级策略或者说非正常返回（请求应该是非200时）的时候会启用
public interface HystrixService extends ServiceApi {
}
