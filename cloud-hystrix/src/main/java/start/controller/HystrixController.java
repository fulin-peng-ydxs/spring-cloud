package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import start.service.HystrixService;

/**
 * @author PengFuLin
 * @description 控制器
 * @date 2022/4/28 1:34
 */
@Controller
public class HystrixController{

    @Autowired
    HystrixService hystrixService;

    @GetMapping("hystrix1")
    @ResponseBody
    public String hystrix1(){
        return hystrixService.service1();
    }

    @GetMapping("hystrix2")
    @ResponseBody
    public String hystrix2(){
        return hystrixService.service2();
    }


    @GetMapping("hystrix3")
    @ResponseBody
    public String hystrix3(@RequestParam("demo") int i){
        return hystrixService.service3(i);
    }
}


