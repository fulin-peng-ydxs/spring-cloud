package start.controller;

import org.springframework.stereotype.Controller;
import start.feign.FeignService;

/**
 * @author PengFuLin
 * @description Feign客户端调用的控制器实现
 * @date 2022/4/26 22:25
 */
@Controller
public class ControllerFeign implements FeignService {

    @Override
    public String service1() {
        return "您正在访问Feign客户端调用的第一个服务8001";
    }

    @Override
    public String service2() {
        return "您正在访问Feign客户端调用的第二个服务8001";
    }
}
