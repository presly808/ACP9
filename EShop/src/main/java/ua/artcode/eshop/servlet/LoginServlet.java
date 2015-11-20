package ua.artcode.eshop.servlet;

import ua.artcode.eshop.exception.NoUserFoundException;
import ua.artcode.eshop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends BaseServlet {

    public static final String LOGIN_JSP = "/WEB-INF/pages/login.jsp";
    public static final String USER_INFO_JSP = "/WEB-INF/pages/user-info.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("pass");

        try {
            User found = userService.findByEmail(email);


            if(found.getPass().equals(pass)){
                String accessKey = UUID.randomUUID().toString();
                resp.addCookie(new Cookie("accessKey", accessKey));
                userSessionMap.put(accessKey,found);
            }

        } catch (NoUserFoundException e) {
            resp.sendRedirect("error.jsp");
        }

        resp.sendRedirect("index.jsp");
    }
}
