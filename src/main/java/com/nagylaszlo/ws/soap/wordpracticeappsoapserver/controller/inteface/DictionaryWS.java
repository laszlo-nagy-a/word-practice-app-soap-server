package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.DictionaryEntryRequest;
import jakarta.jws.WebParam;

import java.util.List;

public interface DictionaryWS {
    public DictionaryEntryResponse addDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    public List<DictionaryEntry> getDictionaryEntries();
    public DictionaryEntryResponse getDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);
    public DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest);
    public boolean deleteDictionaryEntry(@WebParam(name = "dictionaryEntryId") Long dictionaryEntryid);

}
