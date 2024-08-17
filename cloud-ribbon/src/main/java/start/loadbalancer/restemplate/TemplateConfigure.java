package start.loadbalancer.restemplate;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author PengFuLin
 * @description: 环境配置类
 * @date 2022/4/17 20:01
 */
@Configuration
public class TemplateConfigure {
    @Bean
    @LoadBalanced //开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
