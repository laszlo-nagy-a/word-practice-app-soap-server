package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.repository;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository <Topic, Long> {
    Optional<Topic> findByName(String name);
}
