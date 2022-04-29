package start;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
/**
 * @author PengFuLin
 * @description 服务的降级策略
 * @date 2022/4/28 21:03
 */
//指定每个降级命令默认采用的fallback方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class ServiceApiFallBack{
    public String defaultFallback(){
        return "默认的降级统一降级策略";
    }
}
