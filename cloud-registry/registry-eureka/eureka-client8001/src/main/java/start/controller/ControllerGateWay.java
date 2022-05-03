package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description gateway请求测试
 * @date 2022/5/2 16:29
 */
@Controller
public class ControllerGateWay {

    @GetMapping("gateway/8001")
    @ResponseBody
    public String test(){
        return "正在访问8001服务器";
    }
}
