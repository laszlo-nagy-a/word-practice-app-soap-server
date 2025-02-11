package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.EntityNotFoundException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.IncorrectInputException;
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

    public TopicResponse create(TopicRequest topicRequest) throws IncorrectInputException {
        this.checkInputIsValid(topicRequest);
        this.entityIsExist(topicRequest);

        Topic topic = new Topic();
        topic.setName(topicRequest.getName());
        Topic savedTopic = topicRepository.save(topic);

        return new TopicResponse(savedTopic.getId(), savedTopic.getName());
    }

    public List<TopicResponse> getAll() throws EntityNotFoundException {
        List<Topic> allTopic = topicRepository.findAll();

        if(allTopic.isEmpty()) {
            throw new EntityNotFoundException("The Topic list is empty! Add Topics to list with TopicWSService.");
        }

        List<TopicResponse> topicResponseList = new ArrayList<>();
        allTopic.forEach(
                topic ->
                topicResponseList.add(new TopicResponse(topic.getId(), topic.getName())
                )
        );

        return topicResponseList;
    }

    public TopicResponse get(Long topicId) throws EntityNotFoundException, IncorrectInputException {
        this.checkInputIsValid(topicId);

        Optional<Topic> topicFound = topicRepository.findById(topicId);
        if(topicFound.isEmpty()) {
            throw new EntityNotFoundException("Topic not found with id: " + topicId + ".");
        }

        Topic topic = topicFound.get();

        return new TopicResponse(topic.getId(), topic.getName());
    }

    public TopicResponse update(TopicRequest topicRequest) throws EntityNotFoundException, IncorrectInputException {
        this.checkInputIsValid(topicRequest);

        Optional<Topic> foundTopic = topicRepository.findById(topicRequest.getId());

        if(foundTopic.isEmpty()) {
            throw new EntityNotFoundException("The Topic which meant to be updated does not exist!");
        }

        Topic topic = new Topic(
                topicRequest.getId(),
                topicRequest.getName(),
                foundTopic.get().getDictionaryEntryList()
        );

        Topic savedTopic = topicRepository.save(topic);

        return new TopicResponse(savedTopic.getId(), savedTopic.getName());
    }

    public boolean delete(Long topicId) throws IncorrectInputException {
        this.checkInputIsValid(topicId);

        if(topicRepository.findById(topicId).isPresent()) {
            topicRepository.deleteById(topicId);
            return true;
        }

        return false;
    }

    private void entityIsExist(TopicRequest topicRequest) throws IncorrectInputException {
        if(topicRepository.findByName(topicRequest.getName()).isPresent()) {
            throw new IncorrectInputException("The Topic name already exists: " + topicRequest.getName() + ". try with different one!");
        }
    }

    private void checkInputIsValid(Object object) throws IncorrectInputException {
        if(Optional.ofNullable(object).isEmpty()) {
            throw new IncorrectInputException("The given entity cannot be null! The given entity with null: " + object.getClass().getName() + ".");
        }

        if(object instanceof TopicRequest topicRequest) {
            if (StringUtils.isBlank(topicRequest.getName())) {
                throw new IncorrectInputException("The TopicRequest must contain the name!");
            }
        }
    }
}
