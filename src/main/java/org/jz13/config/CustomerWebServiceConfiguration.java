package org.jz13.config;

import org.jz13.CustomerWebserviceEndpoint;
import org.jz13.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

import javax.inject.Inject;

/**
 * @author Kristian Rosenvold
 */
@Import(CommonWebServiceConfiguration.class)
public class CustomerWebServiceConfiguration
{
    private final TestService testService;

    @Inject
    public CustomerWebServiceConfiguration(TestService testService){
        this.testService = testService;
    }

    @Bean
    public SimpleXsdSchema CustomerSchema() {
        return new SimpleXsdSchema(new ClassPathResource("CustomerSchema.xsd"));
    }

    @Bean
    public SimpleWsdl11Definition customerService() {
        return new SimpleWsdl11Definition(new ClassPathResource("customerService.wsdl"));
    }

    @Bean
    public CustomerWebserviceEndpoint customerWebserviceEndpoint(){
        return new CustomerWebserviceEndpoint(testService);
    }
}
