package com.odss.seu.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "非法的请求")
public class WrongRequestException extends RuntimeException {
}
