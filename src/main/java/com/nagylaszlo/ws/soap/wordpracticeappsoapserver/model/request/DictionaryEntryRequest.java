package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request;
//TODO: IMPLEMENT THE CLASS

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DictionaryEntryRequest {
    private Long topicId;
    private String word;
    private String translation;
}
