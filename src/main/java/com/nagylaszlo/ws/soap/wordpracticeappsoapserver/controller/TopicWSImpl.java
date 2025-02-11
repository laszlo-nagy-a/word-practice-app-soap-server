package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface.TopicWS;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.EntityNotFoundException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.IncorrectInputException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.TopicRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicWSImpl implements TopicWS {

    private final TopicService topicService;

    public TopicWSImpl(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public TopicResponse addTopic(TopicRequest topicRequest) throws IncorrectInputException {
        return topicService.create(topicRequest);
    }

    @Override
    public List<TopicResponse> getTopics() throws EntityNotFoundException {
        return topicService.getAll();
    }

    @Override
    public TopicResponse getOneTopic(Long topicId) throws EntityNotFoundException, IncorrectInputException {
        return topicService.get(topicId);
    }

    @Override
    public TopicResponse updateTopic(TopicRequest topicRequest) throws EntityNotFoundException, IncorrectInputException {
        return topicService.update(topicRequest);
    }

    @Override
    public boolean deleteTopic(Long topicId) throws IncorrectInputException {
        return topicService.delete(topicId);
    }
}
