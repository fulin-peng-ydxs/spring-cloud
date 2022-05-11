package start.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import start.model.CommonResult;
import start.model.Storage;

/**
 * @Author PengFuLin
 * @Description  库存服务api
 * @Date 14:43 2022/5/10
 **/
public interface StorageServiceApi {

    @PostMapping(value = "/storage/decrease")
    CommonResult<Storage> decrease(
            @RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
