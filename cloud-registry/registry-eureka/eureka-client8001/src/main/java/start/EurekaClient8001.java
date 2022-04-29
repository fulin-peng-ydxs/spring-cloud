package start;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author PengFuLin
 * @description: eureka服务启动类
 * @date 2022/4/17 17:53
 */
@SpringBootApplication
//开启客户端
@EnableEurekaClient
//开启hystrix降级
@EnableCircuitBreaker
public class EurekaClient8001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClient8001.class,args);

    }
    //此次版本的需要指定对服务监控所访问的路径：
    //否则出现：Unable to connect to Command Metric Stream
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

