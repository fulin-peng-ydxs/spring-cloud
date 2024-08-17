package start.loadbalancer.strategy.custom;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.Random;

public class CustomLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private final ServiceInstanceListSupplier serviceInstanceListSupplier;

    public CustomLoadBalancer(ServiceInstanceListSupplier serviceInstanceListSupplier, String serviceId) {
        this.serviceInstanceListSupplier = serviceInstanceListSupplier;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        return serviceInstanceListSupplier.get().next().map(instances -> {
            // 自定义逻辑，比如随机选择一个实例
            if (instances.isEmpty()) {
                return new EmptyResponse();
            }
            // 自定义选择逻辑（这里是随机选择）
            ServiceInstance instance = instances.get(new Random().nextInt(instances.size()));
            return new DefaultResponse(instance);
        });
    }
}

