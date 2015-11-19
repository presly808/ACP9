package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * Created by serhii on 19.11.15.
 */
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();

        System.out.println("HEADERS");
        while(headerNames.hasMoreElements()){
            String name = headerNames.nextElement();
            System.out.printf("%s=%s\n", name, req.getHeader(name));
        }

        System.out.println("COOKIES");
        Arrays.stream(req.getCookies()).forEach(
                cookie ->
                        System.out.printf("%s,%s\n",
                                cookie.getName(), cookie.getValue()));

        System.out.printf("reqURL %s, method %s, path %s, query %s\n",
                req.getRequestURI(), req.getMethod(), req.getPathInfo(), req.getQueryString());



        PrintWriter pw = resp.getWriter();
        pw.println("<body><div><h1>Hello servlet</h1></div></body>");
        pw.flush();
    }
}
