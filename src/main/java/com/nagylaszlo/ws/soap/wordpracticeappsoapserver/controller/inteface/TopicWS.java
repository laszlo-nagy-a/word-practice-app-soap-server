package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;


import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.TopicRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface TopicWS {

    public @WebResult TopicResponse addTopic(@WebParam TopicRequest topicRequest);
    public @WebResult List<TopicResponse> getTopics();
    public @WebResult TopicResponse getOneTopic(@WebParam(name = "topicId") Long topicId);
    public @WebResult TopicResponse updateTopic(@WebParam TopicRequest topicRequest);
    public @WebResult(name = "deleteResult") boolean deleteTopic(@WebParam(name = "topicId") Long topicId);
}
