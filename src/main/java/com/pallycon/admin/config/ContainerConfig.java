package com.pallycon.admin.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContainerConfig {

    @Value("${tomcat.ajp.protocol}")
    String ajpProtocol;

    @Value("${tomcat.ajp.port}")
    int ajpPort;

    @Value("${tomcat.ajp.enabled}")
    boolean tomcatAjpEnabled;

    @Bean
    public ServletWebServerFactory containerCustomizer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector ajpConnector = null;
        AjpNioProtocol protocol;
        if (tomcatAjpEnabled) {
            ajpConnector = new Connector(ajpProtocol);
            protocol = (AjpNioProtocol)ajpConnector.getProtocolHandler();
            ajpConnector.setPort(ajpPort);
            ajpConnector.setSecure(false);
            ajpConnector.setAllowTrace(false);
//            ajpConnector.setScheme("http");
            protocol.setKeepAliveTimeout(15);
            protocol.setTcpNoDelay(true);
            protocol.setConnectionTimeout(100);
        }
        return ajpConnector;
    }
}
