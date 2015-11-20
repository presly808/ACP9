package ua.artcode.eshop.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.eshop.model.User;
import ua.artcode.eshop.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// domain/appName/register
// domain/appName/css/
// domain/appName/WEB-INF/
@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends BaseServlet {

    public static final String REGISTER_JSP = "/WEB-INF/pages/register.jsp";
    public static final String USER_INFO_JSP = "/WEB-INF/pages/user-info.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");
        String pass = req.getParameter("pass");



        User user = userService.register(email, fullname, phone, pass);

        req.setAttribute("myUser",user);

        req.getRequestDispatcher(USER_INFO_JSP).forward(req,resp);

    }
}
