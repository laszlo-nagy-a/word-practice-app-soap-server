package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.TopicRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicResponse create(TopicRequest topicRequest) {
        if(topicRequest == null) {
            throw new IllegalArgumentException("The topic request must not be null");
        }

        if (topicRequest.getName() == null) {
            throw new IllegalArgumentException("The topic name must not be null");
        }


        Topic topic = new Topic();
        topic.setName(topicRequest.getName());

        Topic savedTopic = topicRepository.save(topic);
        TopicResponse topicResponse = new TopicResponse();

        topicResponse.setName(savedTopic.getName());
        topicResponse.setTopicId(savedTopic.getId());

        return topicResponse;
    }

    //TODO: create get all with JPA
    public List<TopicResponse> getAll() {
        return null;
    }

    // TODO: create get one with JPA
    public TopicResponse get(Long topicId) {
        return null;
    }

    // TODO: update topic with JPA
    public TopicResponse update(TopicRequest topicRequest) {
        if(topicRequest == null) {
            throw new IllegalArgumentException("The topic request must not be null");
        }

        if(topicRequest.getName() == null) {
            throw new IllegalArgumentException("The topic name must not be null");
        }

        if(topicRequest.getId() == null) {
            throw new IllegalArgumentException("The topic id must not be null");
        }

        return null;
    }
}
