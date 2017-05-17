package com.chattingus.commons;

import com.chattingus.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by 002116110445 on 2017/5/13.
 */
@Component
public class StartSetAllOffLine {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void startSet(){
        userService.setAllUserOffLine();
    }

}
