package org.jz13.config;

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
public class CommonWebServiceConfiguration
{
        @Bean
        public SimpleXsdSchema CommonTypes() {
            return new SimpleXsdSchema(new ClassPathResource("CommonTypes.xsd"));
        }

        @Bean
        public PayloadValidatingInterceptor validatingInterceptor() {
            PayloadValidatingInterceptor payloadValidatingInterceptor = new PayloadValidatingInterceptor();
            Resource[] schemas = new Resource[] {
                new ClassPathResource("CommonTypes.xsd")
            };
            payloadValidatingInterceptor.setSchemas(schemas);
            payloadValidatingInterceptor.setValidateRequest(true);
            payloadValidatingInterceptor.setValidateResponse(true);
            return payloadValidatingInterceptor;
        }
}
