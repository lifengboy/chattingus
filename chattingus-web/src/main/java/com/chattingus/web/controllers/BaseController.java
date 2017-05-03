package com.chattingus.web.controllers;

import com.chattingus.services.SessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 002116110445 on 2017/4/18.
 */
@Controller
public abstract class BaseController {

    private Logger logger = Logger.getLogger(BaseController.class);

    @Autowired
    protected SessionService sessionService;

    @ModelAttribute
    protected void setHttpServlet(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        // session管理
        sessionService.setHttpServlet(session);
    }

}
