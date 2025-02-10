package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryAnswer;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.WordPracticeRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryQuestion;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.WordPracticeResponse;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService
public interface WordPracticeWS {
    @WebResult(name = "gameId")WordPracticeResponse practiceIni(WordPracticeRequest topicId);
    DictionaryEntryQuestion attempt(DictionaryEntryAnswer answer);
}