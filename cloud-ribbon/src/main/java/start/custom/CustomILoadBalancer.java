package start.custom;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author PengFuLin
 * @description 自定义负载均衡器
 * @date 2022/4/25 23:52
 */
@Component
public class CustomILoadBalancer{
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    /**
     * @Author PengFuLin
     * @Description  获取应访问服务的下标
     * @Date 23:58 2022/4/25
     **/
    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*******第几次访问，次数next: "+next);
        return next;
    }
    /**
     * @Author PengFuLin
     * @Description  获取一个服务
     * @Date 23:58 2022/4/25
     **/
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {  //得到服务器的列表
        int index = getAndIncrement() % serviceInstances.size(); //得到服务器的下标位置
        return serviceInstances.get(index);
    }
}

