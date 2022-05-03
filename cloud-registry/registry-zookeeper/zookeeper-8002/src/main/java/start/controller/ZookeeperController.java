package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author PengFuLin
 * @description: 控制类
 * @date 2022/4/18 1:08
 */
@Controller
public class ZookeeperController {


    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/zookeeper/8002")
    @ResponseBody
    public Object zookeeper(){
        ResponseEntity<String> forEntity = restTemplate.getForEntity( "http://client-8001"+
                "/zookeeper/8001", String.class);
        return forEntity.getBody();
    }
}
