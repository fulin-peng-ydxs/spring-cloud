package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/5/10 23:43
 */
@SpringBootApplication(
        exclude = DataSourceAutoConfiguration.class)
public class StorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StorageApplication.class,args);
    }
}
