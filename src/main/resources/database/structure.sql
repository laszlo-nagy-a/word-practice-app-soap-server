CREATE DATABASE word_practice CHARACTER SET utf8mb4;

CREATE TABLE topic(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL
);

CREATE TABLE dictionary_entry(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    word varchar(255) NOT NULL,
    translation varchar(255) NOT NULL,
    topic_id BIGINT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);

CREATE TABLE word_practice(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    topic_id BIGINT NOT NULL,
    topic_word_count BIGINT,
    good_answers BIGINT,
    start_time DATETIME,
    FOREIGN KEY (topic_id) REFERENCES topic(id)
);