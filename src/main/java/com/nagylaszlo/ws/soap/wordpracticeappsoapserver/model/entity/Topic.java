package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<DictionaryEntry> dictionaryEntryList;
    @OneToMany(mappedBy = "topic")
    private List<WordPractice> wordPracticeList;

    public Topic(Long id, String name, List<DictionaryEntry> dictionaryEntryList){
        this.id = id;
        this.name = name;
        this.dictionaryEntryList = dictionaryEntryList;
    }
}







