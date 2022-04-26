package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import start.feign.FeignService;

/**
 * @author PengFuLin
 * @description feign客户端测试
 * @date 2022/4/26 22:28
 */
@Controller
public class FeignController {

    @Autowired
    FeignService feignService;

    @GetMapping("/feign1")
    @ResponseBody
    public String feign1(){
        return feignService.service1();
    }

    @GetMapping("/feign2")
    @ResponseBody
    public String feign2(){
        return feignService.service2();
    }
}
