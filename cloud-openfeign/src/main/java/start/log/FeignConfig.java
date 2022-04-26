package start.log;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignConfig {
    //配置feign的日志输出类型
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}


