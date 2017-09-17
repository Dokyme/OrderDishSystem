//package com.odss.seu.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Component
//public class SessionServiceImpl implements SessionService {
//
//    private StringRedisTemplate stringRedisTemplate;
//    private Set<String> keySet;
//
//    @Autowired
//    public SessionServiceImpl(StringRedisTemplate stringRedisTemplate) {
//        this.stringRedisTemplate = stringRedisTemplate;
//        keySet = new HashSet<>();
//    }
//
//    @Override
//    public void setAttribute(String token, String key, Object value) {
//        if (!keySet.contains(key))
//            keySet.add(key);
//        stringRedisTemplate.opsForHash().put(key, token, value);
//    }
//
//    @Override
//    public Object getAttribute(String token, String key) {
//        try {
//            if (!keySet.contains(key))
//                return null;
//            if(stringRedisTemplate.opsForHash().size(key)==0)
//                return null;
//            if (!stringRedisTemplate.opsForHash().hasKey(key, token))
//                return null;
//            return stringRedisTemplate.opsForHash().get(key, token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    @Override
//    public void remoteAttribute(String token, String key) {
//        try {
//            stringRedisTemplate.opsForHash().delete(key, token);
//            if (stringRedisTemplate.opsForHash().size(key) == 0)
//                keySet.remove(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void invalidate(String token) {
//        try {
//            for (String key : keySet) {
//                stringRedisTemplate.opsForHash().delete(key, token);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
