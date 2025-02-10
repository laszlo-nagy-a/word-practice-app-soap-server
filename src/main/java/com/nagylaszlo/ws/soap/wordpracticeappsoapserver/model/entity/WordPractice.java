package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class WordPractice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;
    private Long topicWordCount;
    private Long goodAnswers;
    private LocalDateTime startTime;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "word_practice_word",
            joinColumns =
                @JoinColumn(
                        name = "word_practice_id",
                        nullable = false
                ),
            inverseJoinColumns = @JoinColumn(
                    name = "dictionary_entry_id",
                    nullable = false
            )
    )
    private Set<DictionaryEntry> dictionaryEntryList;
    private Long currentDictionaryEntryId;
}
