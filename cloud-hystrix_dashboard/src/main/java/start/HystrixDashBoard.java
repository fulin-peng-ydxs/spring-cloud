package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/4/29 20:20
 */
@SpringBootApplication
//开启监控
@EnableHystrixDashboard
public class HystrixDashBoard {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoard.class, args);
    }
}
