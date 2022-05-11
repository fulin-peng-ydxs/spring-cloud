package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import start.api.StorageServiceApi;
import start.model.CommonResult;
import start.model.Storage;
import start.service.StorageService;

/**
 * @author PengFuLin
 * @description 控制器
 * @date 2022/5/11 0:04
 */
@RestController
public class StoragesController implements StorageServiceApi {

    @Autowired
    private StorageService storageService;

    @Override
    public CommonResult<Storage> decrease(Long productId, Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult<>(200,"扣减库存成功！",null);
    }
}
