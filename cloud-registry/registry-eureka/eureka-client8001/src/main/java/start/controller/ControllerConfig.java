package start.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author PengFuLin
 * @description 服务配置控制器
 * @date 2022/5/3 8:23
 */
@Controller
//标记为刷新领域
@RefreshScope
public class ControllerConfig {

    @Value("${config}")
    private String config;

    @GetMapping("/getConfigInfo")
    @ResponseBody
    public String info(){
       return config;
    }
}

