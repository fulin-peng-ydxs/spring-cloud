package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/5/3 0:26
 */
@SpringBootApplication
//启动配置服务端
@EnableConfigServer
public class Config3344 {
    public static void main(String[] args) {
        SpringApplication.run(Config3344.class, args);
    }
}
