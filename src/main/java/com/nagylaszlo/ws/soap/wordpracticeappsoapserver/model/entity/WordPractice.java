package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
}
