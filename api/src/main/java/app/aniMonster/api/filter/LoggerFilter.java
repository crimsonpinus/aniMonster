package app.aniMonster.api.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var request = new ContentCachingRequestWrapper( (HttpServletRequest) servletRequest );
        var response = new ContentCachingResponseWrapper( (HttpServletResponse) servletResponse );

        filterChain.doFilter(request, response);

        //request 정보
        var headerNames = request.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey ->{
            var headerValue = request.getHeader(headerKey);

            //authorization-token : ??? , user-agent : ???
            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(": ")
                    .append(headerValue)
                    .append("] ");
        });

        var requestBody = new String(request.getContentAsByteArray());
        var uri = request.getRequestURI();
        var method = request.getMethod();

        log.info(
                "\n>>>>>>> uri : {}, \n\t\tmethod : {} , \n\t\theader : {} , \n\t\tbody : {}",
                uri, method, headerValues, requestBody
        );

        //response info
        var responseHeaderValues = new StringBuilder();

        response.getHeaderNames().forEach(headerKey -> {
            var headerValue = request.getHeader(headerKey);
            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ").
                    append(headerValue)
                    .append("] ");
        });

        var responseBody = new String(response.getContentAsByteArray());

        log.info("\n<<<<<<< uri : {}, \n\t\tmethod : {} , \n\t\theader : {} , \n\t\tbody : {}"
                , uri, method, responseHeaderValues, responseBody);

        //response 사용완료  빈 res
        //초기화 하여 원래 상태 복구
        response.copyBodyToResponse();
    }
}
