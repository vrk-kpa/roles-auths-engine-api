package fi.vm.kapa.rova.logging;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Provider
@PreMatching
public class LoggingClientRequestFilter implements ClientRequestFilter { // implements ContainerResponseFilter { //

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			String requestId = (String) attrs.getAttribute(Logger.REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
			if (requestId != null) {
				requestContext.getHeaders().add(Logger.REQUEST_ID, requestId);
				System.out.println(requestId + " was written into request headers");
			} else {
				System.out.println("No requestId was found for request headers");
			}
		} else {
			System.out.println("No RequestContextHolder was found.");
		}
	}
	
//	@Override
//	public void filter(ContainerRequestContext requestContext,
//			ContainerResponseContext responseContext) throws IOException {
//		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
//		if (attrs != null) {
//			String requestId = (String) attrs.getAttribute("requestID", RequestAttributes.SCOPE_REQUEST);
//			if (requestId != null) {
//				requestContext.getHeaders().add("requestID", requestId);
//			}
//		}
//		// TODO Auto-generated method stub
//		
//	}

}
