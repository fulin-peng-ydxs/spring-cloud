package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/4/26 22:51
 */
@SpringBootApplication
//开启FeignClient客户端
@EnableFeignClients
public class FeignClient9001 {
    public static void main(String[] args) {
        SpringApplication.run(FeignClient9001.class, args);
    }
}
