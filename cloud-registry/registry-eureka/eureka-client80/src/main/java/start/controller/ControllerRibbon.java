package start.controller;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author PengFuLin
 * @description: 轮询测试控制层
 * @date 2022/4/17 19:07
 */
@Controller
public class ControllerRibbon {

    @Autowired
    private RestTemplate restTemplate;



    private final String SERVICE_NAME="http://client-cluster";

    @GetMapping("/ribbon")
    @ResponseBody
    public String ribbon(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity(SERVICE_NAME + "/ribbon", String.class);
        return forEntity.getBody();
    }
}
