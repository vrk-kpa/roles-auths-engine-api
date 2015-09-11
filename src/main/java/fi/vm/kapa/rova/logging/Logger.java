package fi.vm.kapa.rova.logging;

import org.slf4j.LoggerFactory;

public class Logger {
    private org.slf4j.Logger slf4jLogger; // actual logger inside this wrapper

    public static final String REQUEST_ID = "ReqID";

    private Logger() {
    }

    public static Logger getLogger(Class cls) {
        Logger logger = new Logger();
        logger.slf4jLogger = LoggerFactory.getLogger(cls);
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

    public void error(String msg, Exception e) {
        slf4jLogger.error(msg, e);
    }

    public static String maskHetuEnding(String hetu) {
        if (hetu == null) {
            return hetu;
        }
        return hetu.replaceAll("(\\d{6})(-|A|\\+)\\d{3}.(?![A-Z0-9])", "$1$2XXXX");
    }

    private String createMessage(String msg) {
        return maskHetuEnding(msg);
    }
}
