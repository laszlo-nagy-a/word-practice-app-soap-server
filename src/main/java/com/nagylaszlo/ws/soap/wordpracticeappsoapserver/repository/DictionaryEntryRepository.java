package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {
    //@Query("SELECT d FROM DictionaryEntry d WHERE Topic.id = :topicId")
    List<DictionaryEntry> findByTopicId(Long topicId);
}
