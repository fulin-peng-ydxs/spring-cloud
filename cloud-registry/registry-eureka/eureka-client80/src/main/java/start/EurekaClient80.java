package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author PengFuLin
 * @description: eureka服务启动类
 * @date 2022/4/17 17:53
 */
@SpringBootApplication
//开启客服端
@EnableEurekaClient
public class EurekaClient80 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClient80.class,args);
    }
}

