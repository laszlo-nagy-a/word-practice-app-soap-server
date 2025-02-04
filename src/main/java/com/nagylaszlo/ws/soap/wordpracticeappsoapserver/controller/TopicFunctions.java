package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;


import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.TopicRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface TopicFunctions {

    public @WebResult TopicResponse addTopic(@WebParam TopicRequest topicRequest);
    public @WebResult List<TopicResponse> getTopics();
    public @WebResult TopicResponse getOneTopic(@WebParam Long topicId);
    public @WebResult TopicResponse updateTopic(@WebParam TopicRequest topicRequest);
    public @WebResult boolean deleteTopic(@WebParam Long topicId);
}
