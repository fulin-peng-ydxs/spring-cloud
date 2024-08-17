package start.loadbalancer.strategy;


import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

/**
 *  负载均衡器配置类
 * 2024/8/17 19:47
 * @author pengshuaifeng
 */
//为指定的服务节点加入指定的负载均衡策略
@LoadBalancerClient(name = "client-cluster", configuration = RandomLoadBalancerConfiguration.class)
public class BalancerConfigure {
}
