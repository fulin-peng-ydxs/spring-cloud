package start.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
//作为feign客户端
@FeignClient("client-cluster")
@RequestMapping("/feign")
public interface FeignService {
//    @GetMapping("/feign/service1")
    @GetMapping("/service1")
    @ResponseBody
    String service1();

//    @GetMapping("/feign/service2")
    @GetMapping("/service2")
    @ResponseBody
    String service2();
}
