package start.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.List;
/**
 * @author PengFuLin
 * @description: 自定义负载均衡控制器
 * @date 2022/4/17 19:07
 */
@Controller
@RequestMapping("/custom")
public class ControllerCustomRibbon {

    @Autowired
    private CustomILoadBalancer customILoadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ribbon")
    @ResponseBody
    public String ribbon(){
        String SERVICE_NAME = "client-cluster";
        List<ServiceInstance> instances = discoveryClient.getInstances(SERVICE_NAME);
        if (instances == null || instances.isEmpty()){
            return null;
        }
        ServiceInstance serviceInstance = customILoadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri + "/ribbon", String.class);
        return forEntity.getBody();
    }
}
