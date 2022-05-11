package start.controller;

import org.springframework.web.bind.annotation.RestController;
import start.api.AccountServiceApi;
import start.model.Account;
import start.model.CommonResult;
import start.service.AccountService;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author PengFuLin
 * @description 控制器
 * @date 2022/5/10 23:56
 */
@RestController
public class AccountController implements AccountServiceApi {

    @Resource
    AccountService accountService;

    @Override
    public CommonResult<Account> decrease(Long userId, BigDecimal money) {
        accountService.decrease(userId,money);
        return new CommonResult<>(200,"扣减账户余额成功！",null);
    }
}
