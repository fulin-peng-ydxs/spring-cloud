package start.service;

import org.springframework.stereotype.Component;

/**
 * @author PengFuLin
 * @description 客户端服务降级
 * @date 2022/4/29 19:11
 */
@Component
public class HystrixServiceFallBack implements HystrixService{

    @Override
    public String service1() {
        return "客户端调用服务1降级";
    }

    @Override
    public String service2() {
        return "客户端调用服务2降级";
    }

    @Override
    public String service3(int i) {
        return "客户端调用服务3降级";
    }
}
