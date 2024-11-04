package nacos.client.conf;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * nacos 客户端配置
 *
 * @author fulin-peng
 * 2024-11-02  16:38
 */
@Configuration
public class NacosClientConfigure {

    @Value("${nacos.client.serverAddr}")
    String serverAddr;

    @Value("${nacos.client.userName}")
    String userName;

    @Value("${nacos.client.passwd}")
    String passwd;

    @Bean
    public ConfigService getConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR,serverAddr);
        properties.put(PropertyKeyConst.USERNAME,userName);
        properties.put(PropertyKeyConst.PASSWORD,passwd);
        return NacosFactory.createConfigService(properties);
    }
}
