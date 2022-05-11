package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.stream.SendDemo;
/**
 * @author PengFuLin
 * @description Stream控制器
 * @date 2022/5/4 12:14
 */
@Controller
public class StreamController {

    @Autowired
    private SendDemo sendDemo;

    @GetMapping("stream")
    @ResponseBody
    public String stream(){
        return sendDemo.send();
    }
}
