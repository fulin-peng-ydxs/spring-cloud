package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author PengFuLin
 * @description: 服务启动类
 * @date 2022/4/17 23:50
 */
@SpringBootApplication
public class ZookeeperServer8002 {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServer8002.class, args);
    }
}
