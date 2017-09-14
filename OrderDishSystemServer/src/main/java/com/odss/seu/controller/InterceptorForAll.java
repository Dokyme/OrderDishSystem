package com.odss.seu.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorForAll extends HandlerInterceptorAdapter {

    class URIMethodPair {

        private String URI;
        private String method;

        public URIMethodPair(HttpServletRequest re  quest) {
            this.URI = request.getRequestURI();
            this.method = request.getMethod();
        }

        public boolean isValid(String identity) {
            if (URI.contains("/identity"))
                return true;
            else if (identity.equals("manager"))
                return isAdminValid();
            else if (identity.equals("waiter"))
                return isWaiterValid();
            else
                return isCookValid();
        }

        private boolean isAdminValid() {
            if (method.equals("GET")) {
                return URI.contains("/checkout") || URI.contains("/statistics") || URI.contains("/order") || URI.contains("/dish/") || URI.contains("/user/");
            } else if (method.equals("POST")) {
                return URI.contains("/checkout/") || URI.contains("/dish/") || URI.contains("/user/") || URI.contains("/user") || URI.contains("/dish");
            } else if (method.equals("PUT")) {
                return URI.contains("/dish") || URI.contains("/user");
            } else if (method.equals("DELETE")) {
                return URI.contains("/checkout/") || URI.contains("/dish/") || URI.contains("/user/");
            } else {
                return false;
            }
        }

        private boolean isCookValid() {
            if (method.equals("GET")) {
                return URI.contains("/cooking");
            } else if (method.equals("POST")) {
                return URI.contains("/serving/");
            } else if (method.equals("PUT")) {
                return URI.contains("/cooking/") || URI.contains("/serving/");
            } else if (method.equals("DELETE")) {
                return URI.contains("/cooking/");
            } else {
                return false;
            }
        }

        private boolean isWaiterValid() {
            if (method.equals("GET")) {
                return URI.contains("/dish") || URI.contains("/table");
            } else if (method.equals("POST")) {
                return URI.contains("/serving/") || URI.contains("/table/");
            } else if (method.equals("PUT")) {
                return URI.contains("/order");
            } else if (method.equals("DELETE")) {
                return URI.contains("/table/");
            } else {
                return false;
            }
        }

        private boolean isGuestValid(String identity) {
            if (method.equals("GET"))
                return URI.contains("/order/");
            else if (method.equals("PUT"))
                return URI.contains("/order") || URI.contains("/evaluation/");
            else
                return false;
        }
    }

    //    在controller执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        URIMethodPair pair = new URIMethodPair(request);
        Object identity = request.getSession().getAttribute("name");
        if (identity != null) {
            return pair.isValid(identity.toString());
        } else {
            return true;
        }
    }
}