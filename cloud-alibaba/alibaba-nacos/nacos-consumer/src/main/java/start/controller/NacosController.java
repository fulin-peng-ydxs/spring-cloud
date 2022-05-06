package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
/**
 * @author PengFuLin
 * @description 测试器
 * @date 2022/5/5 22:52
 */
@Controller
@RefreshScope
public class NacosController {

    @Value("${config}")
    private String config;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("nacos/ribbon")
    @ResponseBody
    public String ribbon(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://nacos-provider/ribbon", String.class);
        return forEntity.getBody();
    }

    @GetMapping("nacos/configInfo")
    @ResponseBody
    public String configInfo(){
        return config;
    }
}
