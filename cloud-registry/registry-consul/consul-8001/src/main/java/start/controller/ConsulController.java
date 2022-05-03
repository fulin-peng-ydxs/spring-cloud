package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author PengFuLin
 * @description: 控制器
 * @date 2022/4/18 22:18
 */
@Controller
public class ConsulController {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consul/8001")
    @ResponseBody
    public Object consul(){
        List<String> services = discoveryClient.getServices();
        ResponseEntity<String> forEntity = restTemplate.getForEntity( "http://client-8002"+
                "/consul/8002", String.class);
        return forEntity.getBody();
    }
}
