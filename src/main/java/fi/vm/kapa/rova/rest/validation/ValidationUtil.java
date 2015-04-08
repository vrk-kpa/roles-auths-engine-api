package fi.vm.kapa.rova.rest.validation;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;

public class ValidationUtil {
	public final static String HASH_HEADER_NAME = "X-RoVa-Hash";
	public final static String TIMESTAMP_HEADER_NAME = "X-RoVa-timestamp";

	private final static String HMAC_ALGORITHM = "HmacSHA256";
	private String apiKey;
	
	/**
	 * Handle out bound client request
	 * @param context
	 * @return
	 */
	public boolean handleClientRequestContext(ClientRequestContext context) throws IOException {
		System.out.println(context.getUri());
		MultivaluedMap<String, Object> headers = context.getHeaders();
		String timestamp = "" + System.currentTimeMillis();
		String data = context.getUri().getPath();
		data = data + timestamp;
		String hash = generateHash(data);
		headers.putSingle(HASH_HEADER_NAME, hash);
		headers.putSingle(TIMESTAMP_HEADER_NAME, timestamp);
		System.out.println("OUT: " + data + " " + hash);
		return true;
	}

	/**
	 * Handle in bound client request
	 * @param context
	 * @return
	 */
	public boolean handleContainerRequestContext(ContainerRequestContext context) throws IOException {
		String timestamp = context.getHeaderString(TIMESTAMP_HEADER_NAME);
		String data = "/"+context.getUriInfo().getPath() + timestamp;
		String hash = context.getHeaderString(HASH_HEADER_NAME);
		System.out.println("IN: " + data + " " + hash);
		return matches(hash, data);
	}
	
	public ValidationUtil(String apiKey) {
		this.apiKey = apiKey;
	}
	
	private boolean matches(String hash, String data) throws IOException {
		return hash.equals(generateHash(data));
	}
	
	private String generateHash(String data) throws IOException {
		try {
			Mac mac = Mac.getInstance(HMAC_ALGORITHM);
			SecretKeySpec signingKey = new SecretKeySpec(apiKey.getBytes(), HMAC_ALGORITHM);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			String result = new String(Base64.getEncoder().encode(rawHmac));
			return result;
		} catch (Exception e) {

		}
		throw new IOException("Cannot create hash");
	}
}
