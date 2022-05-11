package start.api;

import start.model.Account;
import start.model.CommonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author PengFuLin
 * @Description  账户服务api
 * @Date 14:47 2022/5/10
 **/
public interface AccountServiceApi {

    @PostMapping(value = "/account/decrease")
    CommonResult<Account> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
