package com.chattingus.web.interceptors;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 002116110445 on 2017/4/30.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{
    private Logger logger = Logger.getLogger(SessionInterceptor.class);

    public static final String SESSION_USER = "chattingusUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute(SESSION_USER) == null){
            logger.info("session为空，跳转到登录页面");
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
            return false;
        }
        if (request.isRequestedSessionIdFromURL()) {
            session.invalidate();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
