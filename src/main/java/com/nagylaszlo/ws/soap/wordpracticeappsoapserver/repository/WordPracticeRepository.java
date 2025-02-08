package com.nagylaszlo.ws.soap.wordpracticeappsoapserver.repository;

import com.nagylaszlo.ws.soap.wordpracticeappsoapserver.model.entity.WordPractice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordPracticeRepository extends JpaRepository<WordPractice, Long> {
}
