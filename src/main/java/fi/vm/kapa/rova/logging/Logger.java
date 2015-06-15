package fi.vm.kapa.rova.logging;

import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class Logger { // extends org.slf4j.impl.SimpleLogger implements org.slf4j.Logger {
	
	private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private org.slf4j.Logger slf4jLogger;
	private String component;
	private Random random;
	
	private Logger() {
	}

	@SuppressWarnings("rawtypes")
	public static Logger getLogger(Class cls, String component) {
		Logger logger = new Logger();
		logger.slf4jLogger = LoggerFactory.getLogger(cls);
		logger.component = component;
		logger.random = new Random(System.currentTimeMillis());
		return logger;
	}

	public void debug(String msg) {
		slf4jLogger.debug(createMessage(msg));
		
	}

	public void info(String msg) {
		slf4jLogger.info(createMessage(msg));
		
	}

	public void warn(String msg) {
		slf4jLogger.warn(createMessage(msg));
		
	}

	public void error(String msg) {
		slf4jLogger.error(createMessage(msg));
		
	}
	
	private String createMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append(component);
		builder.append(" ");
		String requestId = (String) RequestContextHolder.getRequestAttributes().getAttribute("requestID", RequestAttributes.SCOPE_REQUEST);
		if (requestId == null) {
			StringBuilder sb = new StringBuilder(15);
			for( int i = 0; i < 15; i++) {
				sb.append(ALPHANUMERICS.charAt(random.nextInt(ALPHANUMERICS.length())));
			}
			requestId = sb.toString();
		}
		builder.append(requestId);
		builder.append(" ");
		builder.append(msg);
		return builder.toString();
	}

}
