package com.seb.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WsConfig {

    @Bean
    public ServletRegistrationBean messageDispatchServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/validation/*");
    }

    @Bean(name = "iban")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ibanSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("IbanPort");
        definition.setTargetNamespace("http://ibans.com/iban-validation");
        definition.setLocationUri("/validation");
        definition.setSchema(ibanSchema);
        return definition;
    }

    @Bean
    public XsdSchema ibanValidationSchema() {
        return new SimpleXsdSchema(new ClassPathResource("iban.xsd"));
    }
}
