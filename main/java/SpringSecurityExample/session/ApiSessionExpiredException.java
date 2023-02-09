package SpringSecurityExample.session;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT)
public class ApiSessionExpiredException extends HttpClientErrorException {
    public ApiSessionExpiredException(){
        super(HttpStatus.REQUEST_TIMEOUT,"세션이 만료되었습니다.");
    }
}
