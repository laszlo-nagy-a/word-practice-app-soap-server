package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

import java.util.List;
@WebService
public interface DictionaryWS {
    DictionaryEntryResponse addDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    List<DictionaryEntryResponse> getDictionaryEntries();
    DictionaryEntryResponse getOneDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);
    DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    boolean deleteDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);
    @WebResult List<DictionaryEntryResponse> getAllDictionaryEntriesWithinTopic(@WebParam(name = "topicId") Long topicId);
}
