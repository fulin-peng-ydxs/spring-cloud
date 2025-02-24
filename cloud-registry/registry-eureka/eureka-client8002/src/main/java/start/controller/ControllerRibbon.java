package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description: 负载均衡控制器
 * @date 2022/4/17 19:07
 */
@Controller
public class ControllerRibbon {

    @GetMapping("/ribbon")
    @ResponseBody
    public String ribbon(){
        return "您正在访问8002服务";
    }
}
