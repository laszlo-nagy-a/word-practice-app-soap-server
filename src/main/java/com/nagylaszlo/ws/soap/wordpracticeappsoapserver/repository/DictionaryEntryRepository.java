package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.DictionaryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DictionaryEntryRepository extends JpaRepository<DictionaryEntry, Long> {
    List<DictionaryEntry> findByTopicId(Long topicId);
    Optional<DictionaryEntry> findByTopicIdAndWord(Long topicId, String word);
}
