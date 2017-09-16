package com.odss.seu.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "请求不被允许")
public class RequestForbiddenException extends RuntimeException {

}
