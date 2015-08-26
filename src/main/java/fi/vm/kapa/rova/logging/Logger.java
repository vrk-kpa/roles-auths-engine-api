package fi.vm.kapa.rova.logging;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Logger {

    private static final String ALPHANUMERICS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // new ReqID is randomized from these chars 
    private org.slf4j.Logger slf4jLogger; // actual logger inside this wrapper
    private String component; // like "soap-service"/"engine"/"vtj-client"
    private Random random;

    public static final String REQUEST_ID = "ReqID";
    public static final String NO_REQUEST_ID = "no_request"; // will be shown as ReqID if logging outside request scope
    public static final String ENGINE = "Engine";
    public static final String SOAP_SERVICE = "Soap-service";
    public static final String VTJ_CLIENT = "Vtj-client";
    public static final String VIRRE_CLIENT = "Virre-client";
    public static final String TEST_CLIENT = "Test-client";
    public static final String ADMIN_UI = "Admin-UI";

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

    public void warning(String msg) {
        slf4jLogger.warn(createMessage(msg));

    }

    public void error(String msg) {
        slf4jLogger.error(createMessage(msg));

    }

    public static String maskHetuEnding(String hetu) {
        if (hetu == null) {
            return hetu;
        }
        return hetu.replaceAll("(\\d{6})(-|A|\\+)\\d{3}.(?![A-Z0-9])", "$1$2XXXX");
    }

    private String createMessage(String msg) {
        msg = maskHetuEnding(msg);
        StringBuilder builder = new StringBuilder();
        builder.append(component);
        builder.append(" ");

        builder.append(fetchRequestId());
        builder.append(" ");

        builder.append(msg);
        return builder.toString();
    }

    private String fetchRequestId() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            String requestId = (String) attrs.getAttribute(REQUEST_ID, RequestAttributes.SCOPE_REQUEST);
            if (requestId == null) {
                HttpServletRequest httpRequest = ((ServletRequestAttributes) attrs).getRequest();
                requestId = httpRequest.getHeader(REQUEST_ID);

                if (requestId == null) {
                    StringBuilder sb = new StringBuilder(15);
                    for (int i = 0; i < 15; i++) {
                        sb.append(ALPHANUMERICS.charAt(random.nextInt(ALPHANUMERICS.length())));
                    }
                    requestId = sb.toString();
                }

                attrs.setAttribute(REQUEST_ID, requestId, RequestAttributes.SCOPE_REQUEST);
            }

            return requestId;
        } else {
            return NO_REQUEST_ID;
        }
    }

}
