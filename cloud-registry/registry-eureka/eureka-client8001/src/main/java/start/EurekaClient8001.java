package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author PengFuLin
 * @description: eureka服务启动类
 * @date 2022/4/17 17:53
 */
@SpringBootApplication
//开启客户端
@EnableEurekaClient
public class EurekaClient8001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClient8001.class,args);
    }
}

