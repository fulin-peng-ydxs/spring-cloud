package gateway.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class JsonUtil {
    private static ObjectMapper mapper;

    public JsonUtil() {
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        JsonUtil.mapper = mapper;
    }

    public static <T> T toList(TypeReference<T> typeReference, String json) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转换异常",var3);
        }
    }

    public static <T> T toBean(TypeReference<T> typeReference, String json) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转换异常",var3);
        }
    }

    public static <T> T toBean(Class<T> c, String json) {
        try {
            return mapper.readValue(json, c);
        } catch (Exception var3) {
            throw new RuntimeException("JSON 转换异常",var3);
        }
    }

    public static String toJson(Object pojo) {
        try {
            return mapper.writeValueAsString(pojo);
        } catch (IOException var2) {
            throw new RuntimeException("JSON 转换异常",var2);
        }
    }
}
