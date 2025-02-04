package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.reponse;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TopicResponse {
    private Long topicId;
    private String name;

    @Override
    public String toString() {
        return "TopicResponse{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                '}';
    }
}
