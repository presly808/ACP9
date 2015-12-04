package com.artcode.myproject.servlet;

import com.artcode.myproject.model.User;
import com.artcode.myproject.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

@ManagedBean
public class Login {

    public static final String ERROR_LOGIN_JSP = "/pages/error-login";
    public static final String USER_INFO_JSP = "/pages/user-info";
    private UserService userService;
    private String username;
    private String password;
    private String message;


    public Login() {
        ApplicationContext applicationContext = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
        userService = applicationContext.getBean(UserService.class);
    }

    public String login() {
        User user;
        try {
            user = userService.getUserByUsername(username);
            if (!user.getPassword().equals(password)) {
                message = "incorrect password for " + username;
                return ERROR_LOGIN_JSP;
            }
        } catch (NoResultException e) {
            message = "there is no user with username " + username;
            return ERROR_LOGIN_JSP;
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", user);
        return USER_INFO_JSP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
