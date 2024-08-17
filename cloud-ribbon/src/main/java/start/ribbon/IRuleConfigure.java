package start.ribbon;


import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**  ribbon的自定义配置类
 * @author pengshuaifeng
 * 2024/8/17
 */
//为指定的服务节点加入指定的负载均衡策略
@RibbonClient(value = "client-cluster",configuration = ribbon.IRuleConfigure.class)
public class IRuleConfigure { }
