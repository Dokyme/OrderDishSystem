package com.odss.seu.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "验证码错误")
public class CaptchaWrongException extends RuntimeException {
}
