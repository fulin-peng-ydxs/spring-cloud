package start.loadbalancer.strategy.custom;

import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomLoadBalancerConfiguration {
    @Bean
    public ReactorServiceInstanceLoadBalancer customLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        // 获取服务名称（如果有指定客户端对指定服务使用此策略时有用）
        String serviceId = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new CustomLoadBalancer(loadBalancerClientFactory.getLazyProvider(serviceId, ServiceInstanceListSupplier.class).getIfAvailable(), serviceId);
    }
}