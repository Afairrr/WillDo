package exceptions;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangBing
 * @date 2021/3/27 10:44
 */
@Getter
abstract class BaseException extends RuntimeException{
    private final ErrorCode errorCode;
    private final transient HashMap<String,Object> data=new HashMap<>();

    BaseException(ErrorCode errorCode, Map<String,Object> data) {
        this.errorCode = errorCode;
        if(!ObjectUtils.isEmpty(data)){
            this.data.putAll(data);
        }
    }

    BaseException(ErrorCode errorCode, Map<String, Object> data, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }
}
