package org.jz13;

import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.inject.Inject;

/**
 * @author Kristian Rosenvold
 */
public class ResellerWebserviceEndpoint
{
    private static final String NAMESPACE_URI = "http://jz13.org/resellerService";

    @Inject
    public ResellerWebserviceEndpoint() {
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "method1")
    @ResponsePayload
    public String method1(@RequestPayload String request)
        throws Exception {
        return request.toUpperCase();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "method2")
    @ResponsePayload
    public String method2(@RequestPayload String request)
        throws Exception {
        return request.toUpperCase();
    }
}

