package org.jz13;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.core.request.mapper.BookmarkableMapper;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WicketServlet;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author Kristian Rosenvold
 */
public class MyHomePageTest
{

    private static Server server;

    public static class TestWicketApplication extends WebApplication
    {
        private final ApplicationContext applicationContext;

        public TestWicketApplication(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Class<? extends WebPage> getHomePage() {
            return MyHomePage.class;
        }

        @Override
        public void init() {
            getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext));
        }
    }


    @Configuration
    public static class TestContext  {
        @Bean
        public TestService testService(){
            return new DefaultTestService();
        }

        @Bean
        public TestWicketApplication wicketApplication(ApplicationContext applicationContext){
            return new TestWicketApplication(applicationContext);
        }
    }



    @Test
    public void testIt() throws Exception {
        startWithWicket();
        server.stop();
        server.join();
    }

    private static void startWithWicket() throws Exception {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register( TestContext.class );

        server = new Server(8089);
        ServletContextHandler sch = new ServletContextHandler( server, "/");
        sch.setClassLoader(Thread.currentThread().getContextClassLoader());

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener( applicationContext );
        sch.addEventListener( contextLoaderListener );


        WicketServlet wicketServlet = new WicketServlet();
        ServletHolder servlet = new ServletHolder( wicketServlet );
        servlet.setInitParameter( WicketFilter.APP_FACT_PARAM, org.apache.wicket.spring.SpringWebApplicationFactory.class.getName() );
        servlet.setInitParameter( ContextParamWebApplicationFactory.APP_CLASS_PARAM, TestWicketApplication.class.getName());
        servlet.setInitParameter( WicketFilter.FILTER_MAPPING_PARAM, "/*");
        servlet.setInitParameter( "wicket.configuration", RuntimeConfigurationType.DEVELOPMENT.name() );
        sch.addServlet( servlet, "/*" );
        server.start();
    }


}
