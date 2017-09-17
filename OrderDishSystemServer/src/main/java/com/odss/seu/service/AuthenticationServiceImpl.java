//package com.odss.seu.service;
//
//import com.odss.seu.service.exception.RequestForbiddenException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthenticationServiceImpl implements AuthenticationService {
//
//    private SessionService sessionService;
//    private final String KEY = "IDENTITY_HASH_KEY";
//
//    @Autowired
//    public AuthenticationServiceImpl(SessionService sessionService) {
//        this.sessionService = sessionService;
//    }
//
//    @Override
//    public void permitsAccessFrom(UserType[] userTypes, String token) {
//        Object object = sessionService.getAttribute(token, KEY);
//        if (object == null || object.equals(""))
//            throw new RequestForbiddenException();
//        for (UserType userType : userTypes) {
//            if (object.equals(userType.toString())) {
//                return;
//            }
//        }
//        throw new RequestForbiddenException();
//    }
//
//    @Override
//    public void permitsAccessFrom(UserType userType, String token) {
//        permitsAccessFrom(new UserType[]{userType}, token);
//    }
//}
