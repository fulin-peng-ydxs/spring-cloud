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
//启动服务发现和注册：默认自动会将系统进行注册和开启发现功能，不需要显示的用注解标记。
//autoRegister = false将不会自动将系统进行注册,但不影响服务的发现功能
//@EnableDiscoveryClient(autoRegister = false)
public class ConsulServer8001 {
    public static void main(String[] args) {
        SpringApplication.run(ConsulServer8001.class, args);
    }
}
