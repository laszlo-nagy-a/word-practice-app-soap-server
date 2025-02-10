package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.TopicResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.TopicRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.TopicRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicResponse create(TopicRequest topicRequest) {
        if(Optional.ofNullable(topicRequest).isEmpty()) {
            throw new IllegalArgumentException("The TopicRequest cannot be null");
        }

        if (StringUtils.isBlank(topicRequest.getName())) {
            throw new IllegalArgumentException("The Topic must contain the name");
        }

        if(isExist(topicRequest)) {
            throw new IllegalArgumentException("The topic already exists");
        }

        Topic topic = new Topic();
        topic.setName(topicRequest.getName());
        Topic savedTopic = topicRepository.save(topic);
        TopicResponse topicResponse = new TopicResponse(savedTopic.getId(), savedTopic.getName());

        return topicResponse;
    }

    public List<TopicResponse> getAll() {
        List<Topic> allTopic = topicRepository.findAll();

        if(allTopic == null || allTopic.isEmpty()) {
            throw new IllegalArgumentException("The topic list is empty");
        }

        List<TopicResponse> topicResponseList = new ArrayList<>();

        allTopic.forEach(
                topic ->
                topicResponseList.add(new TopicResponse(topic.getId(), topic.getName())
                )
        );

        return topicResponseList;
    }
    // TODO: type exception handling marshalling
    public TopicResponse get(Long topicId) {
        if(Optional.ofNullable(topicId).isEmpty()) {
            throw new IllegalArgumentException("The topicId cannot be null");
        }

        Optional<Topic> topicFound = topicRepository.findById(topicId);

        if(topicFound.isEmpty()) {
            throw new IllegalArgumentException("The topic is not found");
        }

        Topic topic = topicFound.get();

        TopicResponse response = new TopicResponse(topic.getId(), topic.getName());

        return response;
    }
    // TODO: type exception handling marshalling
    public TopicResponse update(TopicRequest topicRequest) {
        if(Optional.ofNullable(topicRequest).isEmpty()) {
            throw new IllegalArgumentException("The topic request must not be null");
        }

        if(StringUtils.isBlank(topicRequest.getName())) {
            throw new IllegalArgumentException("The topic name must not be null or emtpy");
        }

        Optional<Topic> foundTopic = topicRepository.findById(topicRequest.getId());

        if(foundTopic.isEmpty()) {
            throw new IllegalArgumentException("The topic does not exist");
        }

        Topic topic = new Topic(
                topicRequest.getId(),
                topicRequest.getName(),
                foundTopic.get().getDictionaryEntryList()
        );

        Topic savedTopic = topicRepository.save(topic);

        return new TopicResponse(savedTopic.getId(), savedTopic.getName());
    }

    public boolean delete(Long topicId) {
        if(Optional.ofNullable(topicId).isEmpty()) {
            throw new IllegalArgumentException("The topic id must not be null");
        }

        if(topicRepository.findById(topicId).isPresent()) {
            topicRepository.deleteById(topicId);
            return true;
        }

        return false;
    }

    private Boolean isExist(TopicRequest topicRequest) {
        return topicRepository.findByName(topicRequest.getName()).isPresent();
    }
}
