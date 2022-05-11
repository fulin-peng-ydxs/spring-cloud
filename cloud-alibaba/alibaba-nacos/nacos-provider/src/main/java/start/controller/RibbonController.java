package start.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.environment.EnvironmentManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

/**
 * @author PengFuLin
 * @description 负载均衡测试
 * @date 2022/5/5 22:56
 */
@Controller
public class RibbonController {
    @Value("${server.port}")
    private String port;

    @GetMapping("ribbon")
    @ResponseBody
    public String ribbon(){
        Map<String, String> getenv = System.getenv();
        System.out.println(getenv.entrySet());
        return "正在调用"+port+"端口的服务";
    }
}
