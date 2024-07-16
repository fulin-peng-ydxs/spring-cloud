package gateway.model;


import gateway.conf.cookie.CookieProperties;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * cookie配置类
 *
 * @author pengshuaifeng
 * 2024/7/16
 */
@Data
public class CookieConfig {
    public CookieConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public CookieConfig(String name, String value, CookieProperties properties) {
        this.name = name;
        this.value = value;
        this.properties = properties;
    }

    private String name;

    private String value;

    private CookieProperties properties=CookieProperties.DEFAULT_INSTANCE;
}
