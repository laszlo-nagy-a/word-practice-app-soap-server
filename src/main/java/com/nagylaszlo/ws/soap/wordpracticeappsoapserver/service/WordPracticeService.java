package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.WordPractice;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryAnswer;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.WordPracticeRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryQuestion;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.WordPracticeResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.DictionaryEntryRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.TopicRepository;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository.WordPracticeRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

        if(Optional.ofNullable(wordPracticeRequest).isEmpty()) {
            throw new IllegalArgumentException("wordPracticeRequest can not be empty or null");
        }

        Long topicId = wordPracticeRequest.getTopicId();

        if(Optional.ofNullable(topicId).isEmpty()) {
            throw new IllegalArgumentException("topicId can not be empty or null");
        }

        Optional<Topic> topicFound = topicRepository.findById(wordPracticeRequest.getTopicId());

        if (topicFound.isEmpty()) {
            throw new RuntimeException("topic not found with id:" + topicId);
        }

        // create dictionary entry list for the practice
        Topic topic = topicFound.get();

        if(topic.getDictionaryEntryList().isEmpty()) {
            throw new IllegalArgumentException("dictionaryEntryList can not be empty or null");
        }

        HashSet<DictionaryEntry> dictionaryEntries = new HashSet<>(topic.getDictionaryEntryList());

        // setting up the practice
        WordPractice wordPractice = new WordPractice();
        wordPractice.setTopic(topic);
        wordPractice.setTopicWordCount((long)dictionaryEntries.size());
        wordPractice.setStartTime(LocalDateTime.now());
        wordPractice.setGoodAnswers(0L);
        wordPractice.setDictionaryEntryList(new HashSet<>(dictionaryEntries));

        Long firstDictionaryEntryId = dictionaryEntries.iterator().next().getId();
        wordPractice.setCurrentDictionaryEntryId(firstDictionaryEntryId);

        // create the practice
        WordPractice savedWordPractice = wordPracticeRepository.save(wordPractice);

        WordPracticeResponse wordPracticeResponse = new WordPracticeResponse();
        wordPracticeResponse.setWordPracticeId(savedWordPractice.getId());
        wordPracticeResponse.setTotalWordCount((long)dictionaryEntries.size());

        // próba első példányra

        wordPracticeResponse.setDictionaryEntryId(firstDictionaryEntryId);

        return wordPracticeResponse;
    }

    @Transactional
    public DictionaryEntryQuestion attempt(DictionaryEntryAnswer answer) {

        if(Optional.ofNullable(answer).isEmpty()) {
            throw new RuntimeException("The answer can not be null");
        }

        Optional <WordPractice> wordPracticeFound = wordPracticeRepository.findById(answer.getWordPracticeId());

        if(wordPracticeFound.isEmpty()) {
            throw new RuntimeException("The word practice can not be null");
        }

        WordPractice wordPractice = wordPracticeFound.get();
        Long wordPracticeCurrentDictionaryEntryId = wordPractice.getCurrentDictionaryEntryId();

        if(Optional.ofNullable(wordPracticeCurrentDictionaryEntryId).isEmpty()) {
            throw new RuntimeException("The current practice ended, guessed all of the words!");
        }

        Long dictionaryEntryId = answer.getDictionaryEntryId();

        if(Optional.ofNullable(dictionaryEntryId).isEmpty()) {
            throw new RuntimeException("The dictionary entry id can not be null");
        }

        if(!wordPracticeCurrentDictionaryEntryId.equals(dictionaryEntryId)) {
            throw new RuntimeException("The questioned word was not that. Try with the correct DictionaryEntryId: " + wordPracticeCurrentDictionaryEntryId);
        }

        String solution = answer.getAnswer();

        if(StringUtils.isBlank(solution)) {
            throw new RuntimeException("The answer can not be empty");
        }

        //Create the answer for compare
        solution = solution.toLowerCase();
        Optional<DictionaryEntry> dictionaryEntryFound = dictionaryEntryRepository.findById(wordPractice.getCurrentDictionaryEntryId());

        if(dictionaryEntryFound.isEmpty()) {
            throw new RuntimeException("The word practice can not be empty");
        }

        DictionaryEntry dictionaryEntry = dictionaryEntryFound.get();

        Set<DictionaryEntry> dictionaryEntrySet = wordPractice.getDictionaryEntryList();

        if(dictionaryEntrySet.isEmpty()) {
            throw new RuntimeException("You tried all the words, practice ended");
        }

        String finalSolution = solution;

        if(finalSolution.equals(dictionaryEntry.getTranslation())) {
            wordPractice.setGoodAnswers(wordPractice.getGoodAnswers() + 1);
        }

        wordPractice.getDictionaryEntryList().remove(dictionaryEntry);

        if(dictionaryEntrySet.iterator().hasNext()) {
            wordPractice.setCurrentDictionaryEntryId(dictionaryEntrySet.iterator().next().getId());
        } else {
            wordPractice.setCurrentDictionaryEntryId(null);
        }

        WordPractice savedWordPractice = wordPracticeRepository.save(wordPractice);

        DictionaryEntryQuestion question = new DictionaryEntryQuestion();
        question.setWordPracticeId(wordPractice.getId());
        question.setDictionaryEntryId(savedWordPractice.getCurrentDictionaryEntryId());

        return question;
    }
}
