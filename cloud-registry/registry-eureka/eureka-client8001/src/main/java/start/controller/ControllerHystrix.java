package start.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Controller;
import start.ServiceApi;
import start.ServiceApiFallBack;
/**
 * @author PengFuLin
 * @description Hystrix控制器
 * @date 2022/4/28 0:57
 */
@Controller
public class ControllerHystrix extends ServiceApiFallBack implements ServiceApi {

    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "100")})
    public String service1() {
        try {
            Thread.sleep(6000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "正常服务1返回";
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public String service2() {
        int i=1/0;
        return "正常服务2返回";
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackCircuitBreaker",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            //开启策略
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String service3(int i) {
        if(i==1){
            return "服务3正常返回!";
        }else{
            int b=1/0;
            return "错误";
        }
    }

    public String fallbackCircuitBreaker(int i){
        return "断路器降级策略触发"+i;
    }

    public String fallback(){
        return "单独的降级策略";
    }
}
