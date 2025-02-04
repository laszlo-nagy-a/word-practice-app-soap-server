package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository <Topic, Long> {
}
