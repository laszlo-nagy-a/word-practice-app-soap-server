package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.exception.IncorrectInputException;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.DictionaryEntryRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.TopicRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryEntryService {

    private final DictionaryEntryRepository dictionaryEntryRepository;
    private final TopicRepository topicRepository;

    public DictionaryEntryService(DictionaryEntryRepository dictionaryEntryRepository, TopicRepository topicRepository) {
        this.dictionaryEntryRepository = dictionaryEntryRepository;
        this.topicRepository = topicRepository;
    }

    public DictionaryEntryResponse create(DictionaryEntryRequest dictionaryEntryRequest) {
        if(Optional.ofNullable(dictionaryEntryRequest).isEmpty()) {
            throw new IllegalArgumentException("The DictionaryEntryRequest cannot be null");
        }

        if(StringUtils.isBlank(dictionaryEntryRequest.getWord()) || StringUtils.isBlank(dictionaryEntryRequest.getTranslation())) {
            throw new IllegalArgumentException("The DictionaryEntryRequest must contain word and translation");
        }

        if(Optional.ofNullable(dictionaryEntryRequest.getTopicId()).isEmpty()) {
            throw new IllegalArgumentException("The DictionaryEntryRequest must contain topicId");
        }

        // get all dictionaryEntries from the topic
        List<DictionaryEntry> allDictionaryEntriesFromTopic = dictionaryEntryRepository.findByTopicId(dictionaryEntryRequest.getTopicId());

        // ensures the word does not exist in the topic
        allDictionaryEntriesFromTopic.forEach(dictionaryEntry -> {
            if(dictionaryEntry.getWord().equals(dictionaryEntryRequest.getWord())){
                throw new IllegalArgumentException("The DictionaryEntry word already exists in this Topic");
            }
        });


        // intiate the dictionaryEntry and save to db
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        dictionaryEntry.setWord(dictionaryEntryRequest.getWord());
        dictionaryEntry.setTranslation(dictionaryEntryRequest.getTranslation());

        Optional<Topic> topic = topicRepository.findById(dictionaryEntryRequest.getTopicId());

        if(topic.isEmpty()) {
            throw new IllegalArgumentException("The Topic does not exist");
        }

        dictionaryEntry.setTopic(topic.get());
        DictionaryEntry savedDictionary = dictionaryEntryRepository.save(dictionaryEntry);

        // create the response object
        DictionaryEntryResponse dictionaryEntryResponse = new DictionaryEntryResponse();
        dictionaryEntryResponse.setDictionaryEntryId(savedDictionary.getId());
        dictionaryEntryResponse.setTopicId(savedDictionary.getTopic().getId());
        dictionaryEntryResponse.setWord(savedDictionary.getWord());
        dictionaryEntryResponse.setTranslation(savedDictionary.getTranslation());

        return dictionaryEntryResponse;
    }

    public List<DictionaryEntryResponse> getAll() {
        List<DictionaryEntry> allDictionaryEntries = dictionaryEntryRepository.findAll();

        if(allDictionaryEntries.isEmpty() || allDictionaryEntries == null) {
            throw new IllegalArgumentException("The dictionaryEnty list is empty");
        }

        List<DictionaryEntryResponse> dictionaryEntryResponses = new ArrayList<>();

        allDictionaryEntries.forEach(dictionaryEntry -> {
            dictionaryEntryResponses.add(
                    new DictionaryEntryResponse(
                        dictionaryEntry.getTopic().getId(),
                        dictionaryEntry.getId(),
                        dictionaryEntry.getWord(),
                        dictionaryEntry.getTranslation()
            ));
        });

        return dictionaryEntryResponses;
    }

    public DictionaryEntryResponse get(Long dictionaryEntryid) {
        if(Optional.ofNullable(dictionaryEntryid).isEmpty()) {
            throw new IllegalArgumentException("The dictionaryEntryid cannot be null");
        }

        Optional<DictionaryEntry> dictionaryEntryFound = dictionaryEntryRepository.findById(dictionaryEntryid);

        if(dictionaryEntryFound.isEmpty()) {
            throw new IllegalArgumentException("The dictionaryEntryid does not exist");
        }

        DictionaryEntry dictionaryEntry = dictionaryEntryFound.get();

        DictionaryEntryResponse dictionaryEntryResponse = new DictionaryEntryResponse(
                dictionaryEntry.getTopic().getId(),
                dictionaryEntryid,
                dictionaryEntry.getWord(),
                dictionaryEntry.getTranslation()
        );

        return dictionaryEntryResponse;
    }

    public DictionaryEntryResponse update(DictionaryEntryRequest dictionaryEntryRequest) {
        if(Optional.ofNullable(dictionaryEntryRequest).isEmpty()) {
            throw new IllegalArgumentException("The DictionaryEntryRequest cannot be null");
        }

        if(Optional.ofNullable(dictionaryEntryRequest.getTopicId()).isEmpty()) {
            throw new IllegalArgumentException("The DictionaryEntryRequest must contain topicId");
        }

        if(StringUtils.isBlank(dictionaryEntryRequest.getWord()) || StringUtils.isBlank(dictionaryEntryRequest.getTranslation())) {
            throw new IllegalArgumentException("The DictionaryEntryRequest must contain word and translation");
        }

        Optional<DictionaryEntry> foundDictionaryEntry = dictionaryEntryRepository.findById(dictionaryEntryRequest.getDictionaryEntryId());

        if(foundDictionaryEntry.isEmpty()) {
            throw new IllegalArgumentException("The DictionaryEntry does not exist");
        }

        Optional<Topic> topic = topicRepository.findById(dictionaryEntryRequest.getTopicId());

        if(topic.isEmpty()) {
            throw new IllegalArgumentException("The Topic does not exist");
        }

        DictionaryEntry dictionaryEntry = new DictionaryEntry(
                dictionaryEntryRequest.getDictionaryEntryId(),
                dictionaryEntryRequest.getWord(),
                dictionaryEntryRequest.getTranslation(),
                topic.get()
        );

        DictionaryEntry savedDictionaryEntry = dictionaryEntryRepository.save(dictionaryEntry);

        return new DictionaryEntryResponse(
                savedDictionaryEntry.getTopic().getId(),
                savedDictionaryEntry.getId(),
                savedDictionaryEntry.getWord(),
                savedDictionaryEntry.getTranslation()
        );
    }

    public boolean delete(Long dictionaryEntryid) {
        if(Optional.ofNullable(dictionaryEntryid).isEmpty()) {
            throw new IllegalArgumentException("The dictionaryEntryid cannot be null");
        }

        if(dictionaryEntryRepository.findById(dictionaryEntryid).isPresent()) {
            dictionaryEntryRepository.deleteById(dictionaryEntryid);
            return true;
        }

        return false;
    }

    @Transactional
    public List<DictionaryEntryResponse> getAllDictionaryEntriesWithinTopic(Long topicId) {
        if(Optional.ofNullable(topicId).isEmpty()) {
            throw new IllegalArgumentException("The topicId cannot be null");
        }

        List<DictionaryEntry> dictionaryEntryList = dictionaryEntryRepository.findByTopicId(topicId);

        if(dictionaryEntryList == null || dictionaryEntryList.isEmpty()) {
            throw new IllegalArgumentException("In the topic there is no DictionaryWord entry");
        }

        List<DictionaryEntryResponse> dictionaryEntryResponseList = new ArrayList<>();
        dictionaryEntryList.forEach(
                dictionaryEntry -> {
                    dictionaryEntryResponseList.add(
                            new DictionaryEntryResponse(
                                dictionaryEntry.getTopic().getId(),
                                dictionaryEntry.getId(),
                                dictionaryEntry.getWord(),
                                dictionaryEntry.getTranslation()
                            )
                    );
                }
        );

        return dictionaryEntryResponseList;
    }
}
