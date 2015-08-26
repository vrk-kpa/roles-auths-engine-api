package fi.vm.kapa.rova.rest.identification;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Provider
@PreMatching
public class RequestHeaderCopier implements ClientRequestFilter {

    public static final String XROAD_REQUEST_IDENTIFIER = "X-XRoad-request-id";
    public static final String XROAD_END_USER = "X-XRoad-orig-userId";

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        filter(XROAD_REQUEST_IDENTIFIER, requestContext);
        filter(XROAD_END_USER, requestContext);
    }

    private void filter(String headerName, ClientRequestContext requestContext) {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            String value = (String) attrs.getAttribute(headerName,
                    RequestAttributes.SCOPE_REQUEST);

            if (value == null) {
                HttpServletRequest httpRequest = ((ServletRequestAttributes) attrs)
                        .getRequest();
                value = httpRequest.getHeader(headerName);

            }
            if (value != null) {
                replaceHeaderValue(headerName, requestContext, value);
            }
        }
    }

    private void replaceHeaderValue(String headerName,
            ClientRequestContext requestContext, String value) {
        requestContext.getHeaders().remove(headerName);
        requestContext.getHeaders().add(headerName, value);
    }

}