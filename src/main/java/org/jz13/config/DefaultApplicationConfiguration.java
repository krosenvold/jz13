package org.jz13.config;

import org.jz13.DefaultTestService;
import org.jz13.TestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Kristian Rosenvold
 */
@Import(value = {
    CustomerWebServiceConfiguration.class,
    ResellerWebServiceConfiguration.class,
    WicketBasedAdminGuiConfiguration.class
})
@Configuration

public class DefaultApplicationConfiguration
{

    @Bean
    public TestService testService(){
        return new DefaultTestService();
    }

}
