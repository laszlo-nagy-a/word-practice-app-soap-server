package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WordPracticeResponse {
    private Long wordPracticeId;
    private Long dictionaryEntryId;
    private Long totalWordCount;
}
