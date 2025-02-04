package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.request;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class TopicRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
}
