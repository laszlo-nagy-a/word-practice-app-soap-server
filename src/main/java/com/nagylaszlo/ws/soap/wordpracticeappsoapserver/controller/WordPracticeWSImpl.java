package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface.WordPracticeWS;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryAnswer;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.WordPracticeRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryQuestion;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.WordPracticeResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.WordPracticeService;
import org.springframework.stereotype.Service;

@Service
public class WordPracticeWSImpl implements WordPracticeWS {

    private final WordPracticeService wordPracticeService;

    public WordPracticeWSImpl(WordPracticeService wordPracticeService) {
        this.wordPracticeService = wordPracticeService;
    }

    @Override
    public WordPracticeResponse practiceIni(WordPracticeRequest wordPracticeRequest) {
        return wordPracticeService.practiceIni(wordPracticeRequest);
    }

    @Override
    public DictionaryEntryQuestion attempt(DictionaryEntryAnswer answer) {
        return wordPracticeService.attempt(answer);
    }
}
