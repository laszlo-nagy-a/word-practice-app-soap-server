package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryEntry {

    public DictionaryEntry(String word) {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String translation;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToMany(
            mappedBy = "dictionaryEntryList",
            fetch = FetchType.LAZY
    )
    private Set<WordPractice> wordPracticeList;

    public DictionaryEntry(Long id, String word, String translation, Topic topic) {
            this.id = id;
            this.word = word;
            this.translation = translation;
            this.topic = topic;
    }

}
