package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.TopicRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface TopicWS {

    @WebResult TopicResponse addTopic(@WebParam TopicRequest topicRequest);
    @WebResult List<TopicResponse> getTopics();
    @WebResult TopicResponse getOneTopic(@WebParam(name = "topicId") Long topicId);
    @WebResult TopicResponse updateTopic(@WebParam TopicRequest topicRequest);
    @WebResult(name = "deleteResult") boolean deleteTopic(@WebParam(name = "topicId") Long topicId);

}
