package start.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description 流控规则演示
 * @date 2022/5/7 22:02
 */
@Controller
public class FlowControlController {

    @GetMapping("flow/demo1")
    @ResponseBody
    public String demo1() {
        return "流控demo1";
    }
    @GetMapping("flow/demo2")
    @ResponseBody
    public String demo2() {
        return "流控demo2";
    }
}
