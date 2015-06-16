package fi.vm.kapa.rova.logging;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@Component
//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Logger { // extends org.slf4j.impl.SimpleLogger implements org.slf4j.Logger {
	
	private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private org.slf4j.Logger slf4jLogger;
	private String component;
	private Random random;
	
	public static final String REQUEST_ID = "RequestID"; 
	
//	@SuppressWarnings("rawtypes")
//	public Logger(Class cls, String component) {
//		this.slf4jLogger = LoggerFactory.getLogger(cls);
//		this.component = component;
//		this.random = new Random(System.currentTimeMillis());
//	}
//
	private Logger() {
	}
//	
//	public void initialize(Class cls, String component) {
//		this.slf4jLogger = LoggerFactory.getLogger(cls);
//		this.component = component;
//	}

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

	public void warning(String msg) {
		slf4jLogger.warn(createMessage(msg));
		
	}

	public void error(String msg) {
		slf4jLogger.error(createMessage(msg));
		
	}
	
	private String createMessage(String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append(component);
		builder.append(" ");
		
		RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
		if (attrs != null) {
			String requestId = (String) attrs.getAttribute(REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
			if (requestId == null) {
				HttpServletRequest httpRequest = ((ServletRequestAttributes)attrs).getRequest();
				requestId = httpRequest.getHeader(REQUEST_ID);
				
				if (requestId == null) {
					StringBuilder sb = new StringBuilder(15);
					for( int i = 0; i < 15; i++) {
						sb.append(ALPHANUMERICS.charAt(random.nextInt(ALPHANUMERICS.length())));
					}
					requestId = sb.toString();
				}
				
				attrs.setAttribute(REQUEST_ID, requestId, RequestAttributes.SCOPE_REQUEST);
			} 
			builder.append(requestId);
			builder.append(" ");
		} else {
			builder.append("no_request ");
		}
		
		builder.append(msg);
		return builder.toString();
	}

}
