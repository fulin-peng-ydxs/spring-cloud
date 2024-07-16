package gateway.conf.cookie;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "java.servlet.cookie")
public class CookieProperties {

    //是否仅https中传输
    private boolean secure=false;

    //仅在 http/https 请求中被传输，而不能被客户端脚本（如 JavaScript）访问。
    //带有 HttpOnly 属性的 Cookie 无法通过 document.cookie 在 JavaScript 中访问
    private boolean httpOnly=true;

    //作用域
    private String path="/";

    public static final CookieProperties DEFAULT_INSTANCE=new CookieProperties();
}