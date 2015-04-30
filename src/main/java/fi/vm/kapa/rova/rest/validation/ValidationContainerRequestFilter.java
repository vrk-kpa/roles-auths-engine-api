package fi.vm.kapa.rova.rest.validation;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class ValidationContainerRequestFilter implements ContainerRequestFilter {

	private ValidationUtil validationUtil;
	
	public ValidationContainerRequestFilter(String apiKey, int requestAliveSeconds, String pathPrefix) {
		validationUtil = new ValidationUtil(apiKey, requestAliveSeconds, pathPrefix);
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		if (!validationUtil.handleContainerRequestContext(requestContext)) {
			throw new IOException("Request validation failed (hash).");
		}
	}

}
