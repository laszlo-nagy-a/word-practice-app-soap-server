package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.controller.inteface.DictionaryWS;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse.DictionaryEntryResponse;
import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request.DictionaryEntryRequest;

import java.util.List;
// TODO: IMPLEMENT METHODS
public class DictionaryWSImpl implements DictionaryWS {
    @Override
    public DictionaryEntryResponse addDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest) {
        return null;
    }

    @Override
    public List<DictionaryEntry> getDictionaryEntries() {
        return List.of();
    }

    @Override
    public DictionaryEntryResponse getDictionaryEntry(Long dictionaryEntryid) {
        return null;
    }

    @Override
    public DictionaryEntryResponse updateDictionaryEntry(DictionaryEntryRequest dictionaryEntryRequest) {
        return null;
    }

    @Override
    public boolean deleteDictionaryEntry(Long dictionaryEntryid) {
        return false;
    }
}
