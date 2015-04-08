package fi.vm.kapa.rova.rest.validation;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class ValidationClientRequestFilter implements ClientRequestFilter {

	private ValidationUtil validationUtil;
	
	public ValidationClientRequestFilter(String apiKey) {
		validationUtil = new ValidationUtil(apiKey);
	}
	
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		if (!validationUtil.handleClientRequestContext(requestContext)) {
			throw new IOException("unable to handle request security");
		}
	}

}
