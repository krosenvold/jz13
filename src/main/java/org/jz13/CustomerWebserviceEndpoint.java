package org.jz13;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.inject.Inject;

/**
 * @author Kristian Rosenvold
 */
public class CustomerWebserviceEndpoint
{
    private static final String NAMESPACE_URI = "http://jz13.org/customerService";

    private final TestService testService;

    @Inject
    public CustomerWebserviceEndpoint(TestService testService) {
        this.testService = testService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "method1")
    @ResponsePayload
    public String method1(@RequestPayload String request)
        throws Exception {
        return testService.reverse( request );
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "method2")
    @ResponsePayload
    public String method2(@RequestPayload String request)
        throws Exception {
        return request.toUpperCase();
    }
}

