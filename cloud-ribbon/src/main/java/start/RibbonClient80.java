package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/4/25 22:44
 */

@SpringBootApplication
public class RibbonClient80 {

    public static void main(String[] args) {
        SpringApplication.run(RibbonClient80.class, args);
    }
}
