package fi.vm.kapa.rova.logging;

import ch.qos.logback.access.PatternLayoutEncoder;
import ch.qos.logback.access.jetty.RequestLogImpl;
import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.access.tomcat.LogbackValve;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import net.logstash.logback.appender.LogstashAccessTcpSocketAppender;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashAccessEncoder;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.layout.LogstashAccessLayout;
import net.logstash.logback.layout.LogstashLayout;
import org.apache.catalina.Valve;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Juha Korkalainen on 16.9.2015.
 */
@Component
public class LogbackConfigurator {

    @Value("${logstash.host:localhost}")
    private String logstashHost;

    @Value("${logstash.port:5000}")
    private int logstashPort;

    @Value("${service.name}")
    private String serviceName;

    @Value("${logstash.level:INFO}")
    private String logLevel;

    @Value(value = "${application.access-log:true}")
    protected Boolean accessLog;

    @PostConstruct
    public void initLogging() throws Exception {
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        LayoutWrappingEncoder enc = new LayoutWrappingEncoder();
        enc.setContext(lc);

        MaskingLogstashLayout layout = new MaskingLogstashLayout();
        layout.setCustomFields("{\"service\":\"" + serviceName + "\", \"type\": \"application_log\"}");
        layout.setContext(lc);
        layout.start();
        enc.setLayout(layout);

        LogstashTcpSocketAppender logStashAppender = new LogstashTcpSocketAppender();
        logStashAppender.setRemoteHost(logstashHost);
        logStashAppender.setPort(logstashPort);
        logStashAppender.setEncoder(enc);
        logStashAppender.setContext(lc);
        logStashAppender.setName("logstash_application");
        logStashAppender.start();

        // ROOT LOGGER To use logstash
        ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        logger.addAppender(logStashAppender);
        logger.setLevel(Level.toLevel(logLevel));
        logger.setAdditive(true); /* set to true if root should log too */

    }

    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return new EmbeddedServletContainerCustomizer() {
            @Override
            public void customize(ConfigurableEmbeddedServletContainer container) {
                if(container instanceof TomcatEmbeddedServletContainerFactory){
                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
                    LogbackValve logbackAccessValve = new LogbackValve();

                    logbackAccessValve.addAppender(getAccessLogAppender());
                    containerFactory.addContextValves(logbackAccessValve);
                }
            }
        };
    }

    private Appender<IAccessEvent> getAccessLogAppender() {
        LoggerContext lc = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        LayoutWrappingEncoder enc = new LayoutWrappingEncoder();
        enc.setContext(lc);

        MaskingLogstashAccessLayout layout = new MaskingLogstashAccessLayout();
        layout.setContext(lc);
        enc.setLayout(layout);
        layout.start();

        LogstashAccessTcpSocketAppender logStashAxsAppender = new LogstashAccessTcpSocketAppender();
        logStashAxsAppender.setRemoteHost(logstashHost);
        logStashAxsAppender.setPort(logstashPort);
        logStashAxsAppender.setEncoder(enc);
        logStashAxsAppender.setContext(lc);
        logStashAxsAppender.setName("logstash_access");

        logStashAxsAppender.start();
        return logStashAxsAppender;
    }
}