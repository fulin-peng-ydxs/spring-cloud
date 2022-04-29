package start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@RequestMapping("/service")
public interface ServiceApi {
//    @GetMapping("/feign/service1")
    @GetMapping("/service1")
    @ResponseBody
    String service1();

//    @GetMapping("/feign/service2")
    @GetMapping("/service2")
    @ResponseBody
    String service2();

    @GetMapping("/service3")
    @ResponseBody
    String service3(@RequestParam("demo") int i);
}
