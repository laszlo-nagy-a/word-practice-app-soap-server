package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.response;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TopicResponse")
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
