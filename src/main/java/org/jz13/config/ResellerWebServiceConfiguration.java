package org.jz13.config;

import org.jz13.CustomerWebserviceEndpoint;
import org.jz13.ResellerWebserviceEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

/**
 * @author Kristian Rosenvold
 */
@Import(CommonWebServiceConfiguration.class)
public class ResellerWebServiceConfiguration
{
    @Bean
    public SimpleXsdSchema ResellerSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ResellerSchema.xsd"));
    }

    @Bean
    public SimpleWsdl11Definition resellerService() {
        return new SimpleWsdl11Definition(new ClassPathResource("resellerService.wsdl"));
    }

    @Bean
    public ResellerWebserviceEndpoint resellerWebserviceEndpoint(){
        return new ResellerWebserviceEndpoint();
    }
}
