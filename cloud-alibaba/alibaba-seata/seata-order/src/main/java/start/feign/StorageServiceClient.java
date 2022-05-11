package start.feign;

import start.api.StorageServiceApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author PengFuLin
 * @description 库存服务客户端
 * @date 2022/5/10 14:48
 */
@Component
@FeignClient("seata-storage-service")
public interface StorageServiceClient extends StorageServiceApi {
}
