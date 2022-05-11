package start.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import start.dao.AccountDao;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author PengFuLin
 * @description 账户服务
 * @date 2022/5/10 23:55
 */
@Service
@Slf4j
public class AccountService {

    @Resource
    AccountDao accountDao;

    /**
     * 扣减账户余额
     */
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service中扣减账户余额开始");
        accountDao.decrease(userId,money);
        try { TimeUnit.SECONDS.sleep(20); }
        catch (InterruptedException e)
        { e.printStackTrace(); }
        log.info("------->account-service中扣减账户余额结束");
    }
}
