package nacos.client.model.response;

import lombok.Getter;

/**
 * 响应状态类型
 * 2023/12/20 21:32
 * @author pengshuaifeng
 */
@Getter
public enum ResponseStatus {

    SUCCESS("success","请求成功"),

//    ERROR("error","系统异常，请重试"),
    ERROR(null,"系统异常，请重试"),

//    BUSINESS_FAILURE("BUSINESS_FAILURE","系统业务异常，请联系管理员");
    BUSINESS_FAILURE(null,"系统业务异常，请联系管理员");

    private final String status;

    private final String message;

    ResponseStatus(String status,String message){
        this.status=status;
        this.message=message;
    }
}
