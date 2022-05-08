package start.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.handler.BlockHandlerClass;

/**
 * @author PengFuLin
 * @description 注解@SentinelResource的使用
 * @date 2022/5/9 0:07
 */
@Controller
public class SentinelResourceController {

    @GetMapping("sentinelResource")
    @ResponseBody
    @SentinelResource(value = "resource",blockHandler = "blockHandler")
    public String sentinelResource(){
        return "sentinelResource";
    }

    public String blockHandler(BlockException exception){
        return "blockHandler";
    }

    @GetMapping("sentinelResource/fallback")
    @ResponseBody
    @SentinelResource(value = "fallback",fallback = "fallbackMethod")
    public String fallback(){
        int i=1/0;
        return "fallback";
    }

    public String fallbackMethod(){
        return "fallback";
    }



    @GetMapping("sentinelResource/custom")
    @ResponseBody
    @SentinelResource(value = "custom",
            blockHandler = "blockHandlerClass",
            blockHandlerClass = BlockHandlerClass.class
    )
    public String custom(){
        return "sentinelResource";
    }
}
