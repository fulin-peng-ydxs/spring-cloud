package start.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import start.dao.StorageDao;

import javax.annotation.Resource;

/**
 * @author PengFuLin
 * @description 库存服务
 * @date 2022/5/10 23:36
 */
@Service
@Slf4j
public class StorageService {

    @Resource
    private StorageDao storageDao;

    // 扣减库存
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}
