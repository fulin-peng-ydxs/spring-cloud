package start.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import start.ServiceApi;

/**
 * @author PengFuLin
 * @description feign客户端实现
 * @date 2022/4/27 23:25
 */
@Component
//作为feign客户端
@FeignClient(value = "client-cluster")
public interface FeignService extends ServiceApi {
    @Override
    String service1();
}



