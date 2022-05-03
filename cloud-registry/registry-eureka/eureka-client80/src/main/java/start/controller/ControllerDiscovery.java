package start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author PengFuLin
 * @description: 服务发现Api演示
 * @date 2022/4/17 20:27
 */
@Controller
public class ControllerDiscovery {


    @Autowired
    //注入服务发现客户端
    private DiscoveryClient discoveryClient;


    @GetMapping("discovery")
    @ResponseBody
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("client-cluster");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+
                    instance.getPort()+"\t"+instance.getUri());
        }
        instances = discoveryClient.getInstances("client-80");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId()+"\t"+instance.getHost()+"\t"+
                    instance.getPort()+"\t"+instance.getUri());
        }
        return "访问成功！";
    }
}
