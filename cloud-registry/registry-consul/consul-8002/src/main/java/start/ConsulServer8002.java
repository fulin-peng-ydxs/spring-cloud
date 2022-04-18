package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author PengFuLin
 * @description: 启动类
 * @date 2022/4/18 22:05
 */
@SpringBootApplication
public class ConsulServer8002 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulServer8002.class, args);
    }
}
