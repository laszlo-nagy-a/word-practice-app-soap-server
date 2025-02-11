package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.EntityNotFoundException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.IncorrectInputException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.TopicRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface TopicWS {

    @WebResult TopicResponse addTopic(@WebParam TopicRequest topicRequest) throws IncorrectInputException;
    @WebResult List<TopicResponse> getTopics() throws EntityNotFoundException;
    @WebResult TopicResponse getOneTopic(@WebParam(name = "topicId") Long topicId) throws EntityNotFoundException, IncorrectInputException;
    @WebResult TopicResponse updateTopic(@WebParam TopicRequest topicRequest) throws EntityNotFoundException, IncorrectInputException;
    @WebResult(name = "deleteResult") boolean deleteTopic(@WebParam(name = "topicId") Long topicId) throws IncorrectInputException ;

}
