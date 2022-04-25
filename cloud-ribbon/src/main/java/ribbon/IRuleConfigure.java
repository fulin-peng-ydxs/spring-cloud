package ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author PengFuLin
 * @description Ribbon的规则配置类
 * @date 2022/4/25 22:53
 */
@Configuration
public class IRuleConfigure {
    @Bean
    public IRule myRule(){
        //定义为随机规则
//        return new RandomRule();
        //定义为轮询规则
        return new RoundRobinRule();
    }
}
