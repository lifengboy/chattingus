package com.chattingus.services;

import com.chattingus.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * 管理session的Service
 *
 * @author lifeng
 */
@Service
public class SessionService {

    private static final String SESSION_USER = "chattingusUser";

//    private ThreadLocal<HttpSession> sessionLocal = new ThreadLocal<HttpSession>();
    private HttpSession session;

    @ModelAttribute
    public void setHttpServlet(HttpSession session) {
        this.session = session;
    }

    /**
     * 向session中设置用户
     *
     * @param user
     */
    public void setUser(User user) {
        set(SESSION_USER, user);
    }

    /**
     * 获取session的用户
     */
    public User getUser() {
        return (User)get(SESSION_USER);
    }


    /**
     * 向session中删除用户
     */
    public void deleteUser() {
        delete(SESSION_USER);
    }


    /**
     * 设置session的通用接口，不对外提供
     *
     * @return
     */
    private Object set(String key, Object value) {
        getHttpSession().setAttribute(key, value);
        Object o = getHttpSession().getAttribute(key);
        if (o.equals(value)) {
            return o;
        }
        return null;
    }

    /**
     * 获取session的通用接口，不对外提供
     *
     * @return
     */
    private Object get(String key) {
        return getHttpSession().getAttribute(key);
    }

    /**
     * 跟新session的通用接口，不对外提供
     *
     * @param key
     * @param value
     * @return
     */
    private Object update(String key, Object value) {
        getHttpSession().setAttribute(key, value);
        Object o = getHttpSession().getAttribute(key);
        if (o.equals(value)) {
            return o;
        }
        return null;
    }

    /**
     * 删除session的通用接口，不对外提供
     *
     * @param key
     */
    private void delete(String key) {
        if (null != getHttpSession().getAttribute(key)) {
            getHttpSession().removeAttribute(key);
        }
    }

    /**
     * 获取session对象，不对外提供
     *
     * @return
     */
    private HttpSession getHttpSession() {
        return session;
    }

}
