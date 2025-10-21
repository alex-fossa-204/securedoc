package com.getarrayz.securedoc.utils;

import com.getarrayz.securedoc.domain.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtils {

    public static Response getResponse(HttpServletRequest httpServletRequest, Map<?, ?> data, String message, HttpStatus httpStatus) {
        return new Response(
                LocalDateTime.now().toString(),
                httpStatus.value(),
                httpServletRequest.getRequestURI(),
                HttpStatus.valueOf(httpStatus.value()),
                message,
                EMPTY,
                data
        );
    }

}
