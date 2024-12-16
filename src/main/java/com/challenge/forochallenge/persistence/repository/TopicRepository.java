package com.challenge.forochallenge.persistence.repository;

import com.challenge.forochallenge.persistence.entity.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {

  List<Topic> findAllByUserId(Long userId);
}
