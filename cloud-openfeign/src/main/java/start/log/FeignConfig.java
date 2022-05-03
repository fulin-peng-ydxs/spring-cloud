package start.log;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author PengFuLin
 * @Description  设置feign客户端服务调用的输出日志类型
 * @Date 0:43 2022/4/27
 **/
@Configuration
public class FeignConfig {
    //配置feign的日志输出类型
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}


