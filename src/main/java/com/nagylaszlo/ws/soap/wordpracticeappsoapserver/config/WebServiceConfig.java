package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.config;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.DictionaryWSImpl;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.TopicWSImpl;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.WordPracticeWSImpl;
import org.apache.cxf.Bus;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {

    private final TopicWSImpl topicController;
    private final DictionaryWSImpl dictionaryController;
    private final WordPracticeWSImpl wordPracticeController;

    public WebServiceConfig(TopicWSImpl topicController, DictionaryWSImpl dictionaryController, WordPracticeWSImpl wordController) {
        this.topicController = topicController;
        this.dictionaryController = dictionaryController;
        this.wordPracticeController = wordController;
    }

    @Autowired
    private Bus bus;

    @Bean
    public Endpoint topicEndpoint(TopicWSImpl topicController) {
        EndpointImpl endpoint = new EndpointImpl(bus, topicController);
        endpoint.publish("/topicws");

        return endpoint;
    }

    @Bean
    public Endpoint dictionaryEntryEndpoint(DictionaryWSImpl dictionaryController) {
        EndpointImpl endpoint = new EndpointImpl(bus, dictionaryController);
        endpoint.publish("/dictionaryentryws");

        return endpoint;
    }

    @Bean
    public Endpoint wordPracticeEndpoint(WordPracticeWSImpl wordPracticeController) {
        EndpointImpl endpoint = new EndpointImpl(bus, wordPracticeController);
        endpoint.publish("/wordpracticews");

        return endpoint;
    }
}
