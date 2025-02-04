package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.TopicRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicController implements TopicFunctions {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @Override
    public TopicResponse addTopic(TopicRequest topicRequest) {
        return topicService.create(topicRequest);
    }

    @Override
    public List<TopicResponse> getTopics() {
        return topicService.getAll();
    }

    @Override
    public TopicResponse getOneTopic(Long topicId) {
        return topicService.get(topicId);
    }

    @Override
    public TopicResponse updateTopic(TopicRequest topicRequest) {
        return topicService.update(topicRequest);
    }

    @Override
    public boolean deleteTopic(Long topicId) {
        return false;
    }
}
