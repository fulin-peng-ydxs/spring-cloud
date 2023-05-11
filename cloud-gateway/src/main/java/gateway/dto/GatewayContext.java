package gateway.dto;
 
import lombok.Data;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;
 
@Data
public class GatewayContext {
 
    public static final String CACHE_GATEWAY_CONTEXT = "cacheGatewayContext";
 
    /**
     * cache json body
     */
    private String jsonBody;
 
    /**
     *--multipart/form表单参数
     */
    private MultiValueMap<String, Part> multiPartParams;
}