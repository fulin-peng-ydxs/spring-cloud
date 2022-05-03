package run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author PengFuLin
 * @description: eureka服务启动类
 * @date 2022/4/17 17:41
 */
@SpringBootApplication
//开启服务端
@EnableEurekaServer
public class EurekaServer7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7001.class, args);
    }
}
