package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.WordPractice;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.WordPracticeRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response.WordPracticeResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.DictionaryEntryRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.TopicRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository.WordPracticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WordPracticeService {

    private final TopicRepository topicRepository;
    private final WordPracticeRepository wordPracticeRepository;
    private final DictionaryEntryRepository dictionaryEntryRepository;

    public WordPracticeService(TopicRepository topicRepository, WordPracticeRepository wordPracticeRepository, DictionaryEntryRepository dictionaryEntryRepository) {
        this.topicRepository = topicRepository;
        this.wordPracticeRepository = wordPracticeRepository;
        this.dictionaryEntryRepository = dictionaryEntryRepository;
    }

    @Transactional
    public WordPracticeResponse practiceIni(WordPracticeRequest wordPracticeRequest) {
        Optional<Topic> topicFound = topicRepository.findById(wordPracticeRequest.getTopicId());

        if (topicFound.isEmpty()) {
            throw new RuntimeException("The practice can not start: Topic not found");
        }

        WordPractice wordPractice = new WordPractice();

        Topic topic = topicFound.get();

        wordPractice.setTopic(topic);
        wordPractice.setTopicWordCount(
                (long) topic.getDictionaryEntryList().size()
        );
        wordPractice.setStartTime(LocalDateTime.now());
        wordPractice.setGoodAnswers(0L);

        WordPractice savedWordPractice = wordPracticeRepository.save(wordPractice);
        List<DictionaryEntry> dictionaryEntryList = dictionaryEntryRepository.findByTopicId(topic.getId());

        WordPracticeResponse wordPracticeResponse = new WordPracticeResponse();
        wordPracticeResponse.setWordPracticeId(savedWordPractice.getId());
        wordPracticeResponse.setTotalWordCount((long)dictionaryEntryList.size());

        return wordPracticeResponse;
    }
}
