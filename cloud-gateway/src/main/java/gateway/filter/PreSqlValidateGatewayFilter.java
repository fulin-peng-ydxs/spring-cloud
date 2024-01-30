package gateway.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import gateway.dto.GatewayContext;
import gateway.dto.ResponseDto;
import gateway.util.JsonUtils;
import gateway.util.WebServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.regex.Pattern;

/**sql注入攻击过滤器
 * @author peng_fu_lin
 * 2023-05-10 13:36
 */
@Component
public class PreSqlValidateGatewayFilter  extends AbstractGatewayFilterFactory<PreSqlValidateGatewayFilter.Config> implements Ordered {

    private static final Logger logger = LoggerFactory.getLogger(PreSqlValidateGatewayFilter.class);

    private static final Pattern SQL_INJECTION_REGEX = Pattern.compile("^[a-zA-Z,]*$");

    public PreSqlValidateGatewayFilter()
    {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            GatewayContext cacheGatewayContext = (GatewayContext)exchange.getAttributes().get("cacheGatewayContext");
            if(cacheGatewayContext!=null){
                logger.debug("进行SQL注入攻击过滤....");
                Map<String,Object> map = JsonUtils.getMap(cacheGatewayContext.getJsonBody(), new TypeReference<Map<String,Object>>() {});
                Object sortName = map.get("sortName");
                Object sortOrder = map.get("sortOrder");
                if (sqlInjectionAttack(sortOrder)||sqlInjectionAttack(sortName)) {
                    //SQL注入攻击
                    logger.warn("检测到sql注入攻击或存在sql注入风险，已拦截请求：attackParam：{}、attackAddress：{}",
                            cacheGatewayContext.getJsonBody(), exchange.getRequest().getRemoteAddress());
                    return WebServerUtils.responseToJson(
                            ResponseDto.failure("非法参数",null),
                            exchange.getResponse(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
                }
            }
            return chain.filter(exchange);
        };
    }

    @Override
    public int getOrder() {
        return -1;
    }

    /**校验是否存在sql注入
     * 2023/5/10 0010-13:59
     * @author pengfulin
    */
    private boolean sqlInjectionAttack(Object param){
        if(param instanceof String){
            return !SQL_INJECTION_REGEX.matcher((String)param).find();
        }
        return  false;
    }

    public static class Config {
        //Put the configuration properties for your filter here
    }
}