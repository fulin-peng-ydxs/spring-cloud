package gateway.exception.handler;

import gateway.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice
public class ExceptionHandle {

	/**
	 * 通用异常捕获处理
	 */
	@ExceptionHandler(value = Exception.class)
    public Mono<ResponseEntity<ResponseDto<String>>> exceptionHandle(Exception e)
	{
		log.error("系统异常",e);
        return Mono.just(new ResponseEntity<>(
				ResponseDto.failure( "系统出错了，请稍后重试"),
				HttpStatus.INTERNAL_SERVER_ERROR
		));
	}
}
