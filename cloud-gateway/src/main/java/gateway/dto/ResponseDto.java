package gateway.dto;


import lombok.Data;

/**
 * 
 * 请求响应对象
 */
@Data
public class ResponseDto<T> {

	//响应状态
	private Boolean status;
	//响应编码
	private Integer code;
	//响应消息
	private String msg;
	//响应数据
	private T body;

	public ResponseDto() {}

	public ResponseDto(Boolean status, Integer code, String msg, T body) {
		this.status = status;
		this.code = code;
		this.msg = msg;
		this.body = body;
	}

	/**成功响应
	 * 2023/5/10 0010-14:07
	 * @author pengfulin
	*/
	public static <T>  ResponseDto<T> success(T body){
		return new ResponseDto<>(true, 200, "请求成功", body);
	}

	public static ResponseDto<Object> success(){
		return new ResponseDto<>(true, 200, "请求成功",null);
	}

	/**失败响应
	 * 2023/5/10 0010-14:12
	 * @author pengfulin
	*/
	public static <T>  ResponseDto<T> failure(T body){
		return new ResponseDto<>(false, 500, "系统异常", body);
	}

	public static <T>  ResponseDto<T> failure(T body,String msg,Integer code){
		return new ResponseDto<>(false, code==null?500:code, msg, body);
	}

	public static ResponseDto<Object> failure(String msg,Integer code){
		return new ResponseDto<>(false, code==null?500:code, msg,null);
	}

	public static ResponseDto<Object> failure(){
		return new ResponseDto<>(false, 500, "系统异常",null);
	}
}
