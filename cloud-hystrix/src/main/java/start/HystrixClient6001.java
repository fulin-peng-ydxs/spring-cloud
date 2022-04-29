package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/4/28 1:40
 */
@SpringBootApplication
//开启FeignClient客户端
@EnableFeignClients
public class HystrixClient6001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixClient6001.class, args);
    }
}
