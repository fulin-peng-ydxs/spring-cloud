package start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import ribbon.IRuleConfigure;
/**
 * @author PengFuLin
 * @description 启动类
 * @date 2022/4/25 22:44
 */

@SpringBootApplication
//为指定的服务节点加入指定的负载均衡策略
//@RibbonClient(value = "client-cluster",configuration = IRuleConfigure.class)
public class RibbonClient80 {

    public static void main(String[] args) {
        SpringApplication.run(RibbonClient80.class, args);
    }
}
