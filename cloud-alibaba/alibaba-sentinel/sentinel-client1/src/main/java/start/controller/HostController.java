package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @author PengFuLin
 * @description 热点流控演示
 * @date 2022/5/8 23:38
 */
@Controller
public class HostController {

    @GetMapping("/testHotKey")
    @ResponseBody
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2) {
        return "testHotKey";
    }
}
