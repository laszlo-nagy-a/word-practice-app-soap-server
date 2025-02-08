package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.WordPracticeRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response.WordPracticeResponse;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService
public interface WordPracticeWS {
    @WebResult(name = "gameId")WordPracticeResponse practiceIni(WordPracticeRequest topicId);
}
