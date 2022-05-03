package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description: 控制器
 * @date 2022/4/18 22:18
 */
@Controller
public class ConsulController {

    @GetMapping("/consul/8002")
    @ResponseBody
    public Object consul(){
        return "您正在访问8002服务";
    }
}
