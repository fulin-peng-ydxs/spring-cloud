package gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/5/2 16:24
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWay0001 {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GateWay0001.class, args);
        GatewayProperties bean = applicationContext.getBean(GatewayProperties.class);
        List<RouteDefinition> routes = bean.getRoutes();
        for (RouteDefinition route : routes) {
            System.out.println(route.toString());
        }
    }
}
