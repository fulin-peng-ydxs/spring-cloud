package start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author PengFuLin
 * @description: 服务启动类
 * @date 2022/4/17 23:50
 */
@SpringBootApplication
public class ZookeeperServer8001 {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServer8001.class, args);
    }
}
