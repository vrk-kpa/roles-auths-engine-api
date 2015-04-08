package fi.vm.kapa.rova.rest.validation;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class ValidationContainerRequestFilter implements ContainerRequestFilter {

	private ValidationUtil validationUtil;
	
	public ValidationContainerRequestFilter(String apiKey) {
		validationUtil = new ValidationUtil(apiKey);
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		System.out.println("validating request hash");
		if (!validationUtil.handleContainerRequestContext(requestContext)) {
			throw new IOException("Request validation failed (hash).");
		}
	}

}
