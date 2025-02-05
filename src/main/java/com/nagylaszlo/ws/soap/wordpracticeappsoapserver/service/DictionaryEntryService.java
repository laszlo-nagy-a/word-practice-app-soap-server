package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.DictionaryEntryRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.DictionaryEntryRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.TopicRepository;
import io.micrometer.common.util.StringUtils;
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




    /*
    public DictionaryEntryResponse getOneDictionaryEntry(Long dictionaryEntryid) {
        if(Optional.ofNullable(dictionaryEntryid).isEmpty()) {
            throw new IllegalArgumentException("The dictionaryEntryId cannot be null");
        }

        Optional dictionaryEntryFound = dictionaryEntryRepository.findById(dictionaryEntryid);


    }

     */
}
