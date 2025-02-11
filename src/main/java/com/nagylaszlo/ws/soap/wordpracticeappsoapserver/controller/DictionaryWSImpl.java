package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface.DictionaryWS;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.view.request.DictionaryEntryRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.DictionaryEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DictionaryWSImpl implements DictionaryWS {

    private final DictionaryEntryService dictionaryEntryService;

    public DictionaryWSImpl(DictionaryEntryService dictionaryEntryService) {
        this.dictionaryEntryService = dictionaryEntryService;
    }

    @Override
    public DictionaryEntryResponse addDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest) {
        return dictionaryEntryService.create(dictionaryEntryRequest);
    }

    @Override
    public List<DictionaryEntryResponse> getDictionaryEntries() {
        return dictionaryEntryService.getAll();
    }

    @Override
    public DictionaryEntryResponse getOneDictionaryEntry(Long dictionaryEntryId) {
        return dictionaryEntryService.get(dictionaryEntryId);
    }

    @Override
    public List<DictionaryEntryResponse> getAllDictionaryEntriesWithinTopic(Long topicId) {
        return dictionaryEntryService.getAllDictionaryEntriesWithinTopic(topicId);
    }

    @Override
    public DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest) {
        return dictionaryEntryService.update(dictionaryEntryRequest);
    }

    @Override
    public boolean deleteDictionaryEntry(Long dictionaryEntryId) {
        return dictionaryEntryService.delete(dictionaryEntryId);
    }
}
