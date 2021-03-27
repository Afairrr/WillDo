package exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangBing
 * @date 2021/3/27 11:04
 */
@Data
public class ErrorResponse {
    private int code;
    private int status;
    private String msg;
    private String path;
    private Instant timestamp;
    private final HashMap<String,Object> map=new HashMap<>();

    public ErrorResponse(BaseException ex,String path) {
        this(ex.getErrorCode().getCode(),ex.getErrorCode().getStatus().value(),ex.getErrorCode().getMessage(),path,ex.getData());
    }

    public ErrorResponse(ErrorCode errorCode,String path) {
        this(errorCode.getCode(),errorCode.getStatus().value(),errorCode.getMessage(),path,null);
    }

    public ErrorResponse(ErrorCode errorCode,String path,Map<String, Object> errorData) {
        this(errorCode.getCode(),errorCode.getStatus().value(),errorCode.getMessage(),path
        ,errorData);
    }

    public ErrorResponse(int code, int status, String msg, String path, Map<String, Object> map) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.path = path;
        this.timestamp = Instant.now();
        if(!ObjectUtils.isEmpty(map)){
            this.map.putAll(map);
        }
    }
}
