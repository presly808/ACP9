package ua.artcode.eshop.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitSpringContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        String springLocation = servletContext.getInitParameter("springLocation");
        ApplicationContext context = new ClassPathXmlApplicationContext(springLocation);

        servletContext.setAttribute("springContext", context);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
