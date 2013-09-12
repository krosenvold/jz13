package org.jz13;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Test;
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
public class SmallestPossibleServletTest
{
    long start = System.currentTimeMillis();

    @Test
    public void startHelloWorldServlet()
        throws Exception
    {

        Server server = new Server( 8089 );
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        contextHandler.addServlet( new ServletHolder( new HelloWorldServlet() ), "/jz/*" );
        server.start();
        System.out.println( "Started servlet in " + (System.currentTimeMillis() - start) );
        server.stop();
        server.join();
    }

    @Test
    public void startHelloWorldASecondTime()
        throws Exception
    {
        startHelloWorldServlet();
    }

    public class HelloWorldServlet extends HttpServlet
    {
        protected void doGet(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse)
            throws ServletException, IOException
        {
            httpServletResponse.setContentType("text/plain");
            PrintWriter out = httpServletResponse.getWriter();
            out.println( "Hello World!" );
            out.close();
        }
    }
}
