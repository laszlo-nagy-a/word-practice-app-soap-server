package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.config;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.TopicController;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.TopicService;
import org.apache.cxf.Bus;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    private final TopicController topicController;

    public WebServiceConfig(TopicController topicController) {
        this.topicController = topicController;
    }

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint endpoint(TopicController topicController) {
        EndpointImpl endpoint = new EndpointImpl(bus, topicController);
        endpoint.publish("/wordpracticeapp");

        return endpoint;
    }
}
