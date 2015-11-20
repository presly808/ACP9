package ua.artcode.eshop.filter;

import ua.artcode.eshop.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    private Map<String, User> userSessionMap;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userSessionMap = (Map<String, User>) filterConfig.getServletContext().getAttribute("userSessionMap");
    }

    @Override// Chain of responsibility patter
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        Cookie found = Arrays.stream(httpServletRequest.getCookies())
                .filter((cookie -> cookie.getName().equals("accessKey")))
                .findFirst().orElse(null);

        if (found != null) {
            String accessKey = found.getValue();
            httpServletRequest.setAttribute("currentUser", userSessionMap.get(accessKey));
        }

        chain.doFilter(httpServletRequest,response);
    }

    @Override
    public void destroy() {

    }
}
