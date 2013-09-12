package org.jz13;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;
import org.jz13.config.CustomerWebServiceConfiguration;
import org.jz13.config.ResellerWebServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Kristian Rosenvold
 */
public class CustomerServiceTest
{
    @Test
    public void simpleServiceAlive()
        throws Exception
    {
        long start = System.currentTimeMillis();
        Server server = new Server( 8089 );
        MessageDispatcherServlet springWsServlet =
            createMessageDispatcherServlet( ResellerWebServiceConfiguration.class );
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        contextHandler.addServlet( new ServletHolder( springWsServlet ), "/jz/*" );
        server.start();
        System.out.println( "System.currentTimeMillis() - start = " + (System.currentTimeMillis() - start) );

        server.stop();
        server.join();
    }

    @Configuration
    public static class TestConfig {
        @Bean
        public TestService testService(){
            return new DefaultTestService();
        }
    }

    @Test
    public void customerServiceAlive()
        throws Exception
    {


        long start = System.currentTimeMillis();

        Server server = new Server( 8089 );
        MessageDispatcherServlet springWsServlet =
            createMessageDispatcherServlet( TestConfig.class, CustomerWebServiceConfiguration.class );
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        contextHandler.addServlet( new ServletHolder( springWsServlet ), "/jz/*" );
        server.start();
        System.out.println( "System.currentTimeMillis() - start = " + (System.currentTimeMillis() - start) );

        server.stop();
        server.join();
    }

    @Test
    public void secondTime()
        throws Exception
    {
        customerServiceAlive();
    }

    public static MessageDispatcherServlet createMessageDispatcherServlet(Class... contextConfigLocation) {
        String configClasses = asCommaSeparatedString( contextConfigLocation );
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        messageDispatcherServlet.setContextConfigLocation( configClasses );
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return messageDispatcherServlet;
    }

    private static String asCommaSeparatedString( Class[] contextConfigLocation )
    {
        StringBuilder items = new StringBuilder();
        for (Class aClass : contextConfigLocation) {
            items.append( aClass.getName());
            items.append(",");
        }
        return StringUtils.removeEnd( items.toString(), "," );
    }

}
