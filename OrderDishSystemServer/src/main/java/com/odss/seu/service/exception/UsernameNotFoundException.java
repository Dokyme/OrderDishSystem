package com.odss.seu.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE, reason = "用户名不存在")
public class UsernameNotFoundException extends RuntimeException {

}
