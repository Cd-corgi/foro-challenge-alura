package com.challenge.forochallenge.persistence.repository;

import com.challenge.forochallenge.persistence.entity.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByTopicId(Long topicId);

  List<Message> findAllByUserId(Long userId);
}
