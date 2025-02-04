package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<DictionaryEntry> dictionaryEntryList;
}







