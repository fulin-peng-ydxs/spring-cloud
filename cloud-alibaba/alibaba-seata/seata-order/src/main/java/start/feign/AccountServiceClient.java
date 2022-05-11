package start.feign;

import start.api.AccountServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author PengFuLin
 * @description 账户服务客户端
 * @date 2022/5/10 14:49
 */
@Component
@FeignClient("seata-account-service")
public interface AccountServiceClient extends AccountServiceApi {
}
