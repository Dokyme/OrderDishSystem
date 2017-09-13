package com.odss.seu.controller;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InterceptorForAll implements HandlerInterceptor {

    //   在视图出现之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {

    }

    //    在视图完成之前
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    //    在controller执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//      直接在这个里面将所有的限制完成好了
//        登录界面——都可以通过
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        HttpSession session = request.getSession();
//        点菜界面——manager不能访问，服务员可以访问，后厨不能访问
        if (request.getRequestURI().contains("order")) {
            if (session.getAttribute("user") == "manager") {
                return false;
            } else if (session.getAttribute("user") == "waiter") {
                return true;
            } else if (session.getAttribute("user") == "cook") {
                return false;
            } else
                return false;
        }

//        菜品管理界面——管理员可以访问，服务员不能访问，后厨可以访问
        if (request.getRequestURI().contains("dishmanage")) {
            if (session.getAttribute("user") == "manager") {
                return true;
            } else if (session.getAttribute("user") == "waiter") {
                return false;
            } else if (session.getAttribute("user") == "cook") {
                return true;
            } else
                return false;
        }

//        员工管理界面——管理员可以访问，服务员不能访问，后厨不能访问
        if (request.getRequestURI().contains("manage")) {
            if (session.getAttribute("user") == "manager") {
                return true;
            } else if (session.getAttribute("user") == "waiter") {
                return false;
            } else if (session.getAttribute("user") == "cook") {
                return false;
            } else
                return false;
        }

//        经营数据管理——管理员可以访问，服务员不能访问，后厨不能访问
        if (request.getRequestURI().contains("datamanage.action")) {
            if (session.getAttribute("user") == "manager") {
                return true;
            } else if (session.getAttribute("user") == "waiter") {
                return false;
            } else if (session.getAttribute("user") == "cook") {
                return false;
            } else
                return false;
        }

//        座位管理界面——管理员不能访问，服务员可以访问，后厨不能访问
        if (request.getRequestURI().contains("seatmanage")) {
            if (session.getAttribute("user") == "manager") {
                return false;
            } else if (session.getAttribute("user") == "waiter") {
                return true;
            } else if (session.getAttribute("user") == "cook") {
                return false;
            } else
                return false;
        }

//        账单支付界面——管理员可以访问，服务员可以访问，后厨不能访问
        if (request.getRequestURI().contains("true")) {
            if (session.getAttribute("user") == "manager") {
                return true;
            } else if (session.getAttribute("user") == "waiter") {
                return true;
            } else if (session.getAttribute("user") == "cook") {
                return false;
            } else
                return false;
        } else return false;
    }
}

//

//
//        HttpSession session=request.getSession();
//        String user=(String)session.getAttribute("type");
////        对于不可访问的界面拦截，如果访问设置的界面，就会调用拦截器，如果是管理员，就无法访问该页，如果是其它身份，就属于else，可以通过该拦截器
//        if(user=="manager")
//        {
//            request.getRequestDispatcher("/login.jsp").forward(request,arg1);
//            return false;
//        }else
//        {
//
//            return true;
//        }


//        String requestURI = request.getRequestURI();
//        if(requestURI.indexOf("editClientIfo.action")>0){}
//        希望能判断当前网页的代码，之后如果有需要可以调用

//        private String mappingURL;
//
//        public void setMappingURL(String mappingURL) {
//        this.mappingURL = mappingURL;
//    }
//        上面这一部分代码可能能实现当用户权限受到拦截，会直接返回上一个界面



