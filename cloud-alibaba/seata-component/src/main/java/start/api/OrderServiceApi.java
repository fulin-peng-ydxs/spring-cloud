package start.api;

import org.springframework.web.bind.annotation.GetMapping;
import start.model.CommonResult;
import start.model.Order;

/**
 * @author PengFuLin
 * @description 订单服务api
 * @date 2022/5/10 14:24
 */
public interface OrderServiceApi {
    @GetMapping("/order/create")
    CommonResult<Order> create(Order order);
}
