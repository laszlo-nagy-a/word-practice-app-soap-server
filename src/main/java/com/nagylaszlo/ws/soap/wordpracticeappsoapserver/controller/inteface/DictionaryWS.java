package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.DictionaryEntryRequest;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;
@WebService
public interface DictionaryWS {
    public DictionaryEntryResponse addDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    public List<DictionaryEntryResponse> getDictionaryEntries();
    public DictionaryEntryResponse getOneDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);
    public DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    public boolean deleteDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);

}
