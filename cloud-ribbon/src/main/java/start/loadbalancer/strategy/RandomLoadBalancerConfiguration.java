package start.loadbalancer.strategy;

import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *   随机负载均衡器配置类：适用版本Spring Cloud 2020.0.3～
 * 2024/8/17 19:20
 * @author pengshuaifeng
 */
@Configuration
public class RandomLoadBalancerConfiguration {

//    @Bean
//    public ReactorServiceInstanceLoadBalancer randomLoadBalancer(Environment environment,
//                                                                 LoadBalancerClientFactory loadBalancerClientFactory) {
//        String serviceId = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(serviceId, ServiceInstanceListSupplier.class), serviceId);
//    }

}