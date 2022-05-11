package start.service;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import start.dao.OrderDao;
import start.feign.AccountServiceClient;
import start.feign.StorageServiceClient;
import start.model.Order;

/**
 * @author PengFuLin
 * @description 订单服务
 * @date 2022/5/10 14:52
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageServiceClient storageServiceClient;

    @Autowired
    private AccountServiceClient accountServiceClient;


    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     * @param order 订单对象
     */
    //使用GlobalTransactional注解（TM）发起一个全局事务，其属性和@Transaction类似
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order){
        log.info("----->开始新建订单");
        //新建订单
        orderDao.create(order);
        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageServiceClient.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");
        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountServiceClient.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");
        //修改订单状态，从零到1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");
        log.info("----->下订单结束了");
    }
}
