package ua.artcode.eshop.servlet;

import org.springframework.context.ApplicationContext;
import ua.artcode.eshop.model.User;
import ua.artcode.eshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.Map;

public abstract class BaseServlet extends HttpServlet {

    protected UserService userService;
    protected Map<String,User> userSessionMap;

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = (ApplicationContext) getServletContext().getAttribute("springContext");
        userService = applicationContext.getBean(UserService.class);
        userSessionMap = (Map<String, User>) getServletContext().getAttribute("userSessionMap");
    }
}
