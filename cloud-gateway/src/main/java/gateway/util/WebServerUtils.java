package gateway.util;

import gateway.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Mono;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class WebServerUtils {

	/**获取请求参数
	 * 2023/6/7 0007-14:45
	 * @author pengfulin
	*/
	public static String getRequestParam(String key,ServerHttpRequest request){
		return getRequestParam(request).get(key);
	}

	/**
	 * 获取请求参数列表
	 * 2024/6/20 0020 16:35
	 * @author fulin-peng
	 */
	public static Map<String,String> getRequestParam(ServerHttpRequest request){
		return request.getQueryParams().entrySet().stream()
				.filter(entry -> !entry.getValue().isEmpty()) // 过滤空列表
				.collect(Collectors.toMap(
						Map.Entry::getKey,
						entry -> entry.getValue().get(0) // 获取列表中的第一个元素
				));
	}

	/**获取请求头
	 * 2023/6/29 0029-16:00
	 * @author pengfulin
	*/
	public static String getHeader(ServerHttpRequest request,String key) {
		HttpHeaders headers = request.getHeaders();
		List<String> head = headers.get(key);
		return head ==null||head.isEmpty()?null: head.get(0);
	}
	
	/**
	 * 获取cookie信息
	 * @author fulin peng
	 * 2023/7/28 0028 10:46
	 */
	public static List<HttpCookie> getCookies(ServerHttpRequest request, String key){
		return request.getCookies().get(key);
	}

	public static HttpCookie getCookie(ServerHttpRequest request, String key){
		List<HttpCookie> httpCookies = request.getCookies().get(key);
		if(httpCookies==null)
			return null;
		return httpCookies.get(0);
	}

	/**
	 * 写入cookie信息
	 * 2023/12/30 12:11
	 * @author pengshuaifeng
	 */

	/**
	 * 是否为Ajax请求
	 * 2023/12/18 23:46
	 * @author pengshuaifeng
	 */
	private Boolean isAjaxRequest(ServerHttpRequest req)
	{
		String value = getHeader(req, "x-requested-with");
		return value != null && value.equals("XMLHttpRequest");
	}

	/**请求重定向
	 * 2023/6/9 0009-19:02
	 * @author pengfulin
	 */
	public static Mono<Void> redirect(ServerHttpResponse response,String redirectUrl) {
		try {
			response.setStatusCode(HttpStatus.FOUND);
			response.getHeaders().setLocation(new URI(redirectUrl));
			return response.setComplete();
		} catch (Exception e) {
			throw new RuntimeException("请求重定向异常",e);
		}
	}

	/**
	 * 请求返回：json
	 * 2023/12/18 23:40
	 * @author pengshuaifeng
	 */
	public static Mono<Void> responseToJson(ResponseDto<?> responseDto, ServerHttpResponse response, HttpStatus status)
	{
		//设置响应状态码
		response.setStatusCode(status);
		//设置响应headers
		HttpHeaders httpHeaders = response.getHeaders();
		httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
		httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
		//设置响应body
		String respStr = JsonUtils.getString(responseDto);
		DataBuffer bodyDataBuffer = response.bufferFactory().wrap(respStr.getBytes());
		return response.writeWith(Mono.just(bodyDataBuffer));
	}

	/**
	 * 获取访问ip
	 * 2023/12/18 23:54
	 * @author pengshuaifeng
	 */
	public static String getRequestIp(ServerHttpRequest request){
		String header = getHeader(request, "X-Forwarded-For");
		header=header==null?getHeader(request, "X-Real-IP"):header;
		InetSocketAddress remoteAddress = request.getRemoteAddress();
		if(header==null && remoteAddress!=null){
			header= remoteAddress.getHostString();
		}
		return header;
	}
}
