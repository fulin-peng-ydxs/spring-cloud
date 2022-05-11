package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import start.api.OrderServiceApi;
import start.model.CommonResult;
import start.model.Order;
import start.service.OrderService;

/**
 * @author PengFuLin
 * @description 订单控制器
 * @date 2022/5/10 14:27
 */
@RestController
public class OrderController implements OrderServiceApi {

    @Autowired
    private OrderService orderService;

    @Override
    public CommonResult<Order> create(Order order) {
        orderService.create(order);
        return new CommonResult<>(200,"创建订单成功",order);
    }
}
