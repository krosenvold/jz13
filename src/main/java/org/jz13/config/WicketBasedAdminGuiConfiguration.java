package org.jz13.config;

import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.jz13.MyHomePage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.xml.xsd.SimpleXsdSchema;

/**
 * @author Kristian Rosenvold
 */
@Configuration
public class WicketBasedAdminGuiConfiguration
{
    @Bean
    public TestWicketApplication wicketApplication(ApplicationContext applicationContext){
        return new TestWicketApplication(applicationContext);
    }

    // Todo: Make real application :)
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
            getRootRequestMapperAsCompound().add(new MountedMapper("/MyHomePage", MyHomePage.class));
        }
    }

}
