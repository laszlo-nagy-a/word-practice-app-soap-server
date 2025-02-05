package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface.DictionaryWS;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.DictionaryEntryRequest;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.service.DictionaryEntryService;
import org.springframework.stereotype.Service;

import java.util.List;
// TODO: IMPLEMENT METHODS
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
    public DictionaryEntryResponse getOneDictionaryEntry(Long dictionaryEntryid) {
        return dictionaryEntryService.get(dictionaryEntryid);
    }

    @Override
    public DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest) {
        return dictionaryEntryService.update(dictionaryEntryRequest);
    }

    @Override
    public boolean deleteDictionaryEntry(Long dictionaryEntryid) {
        return dictionaryEntryService.delete(dictionaryEntryid);
    }
}
