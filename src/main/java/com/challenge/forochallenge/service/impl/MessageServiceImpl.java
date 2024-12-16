package com.challenge.forochallenge.service.impl;

import com.challenge.forochallenge.exceptions.MessageNotFoundException;
import com.challenge.forochallenge.exceptions.TopicNotFoundException;
import com.challenge.forochallenge.exceptions.UserNotFoundException;
import com.challenge.forochallenge.persistence.dto.message.request.MessageRequest;
import com.challenge.forochallenge.persistence.dto.message.request.UpdateMessageRequest;
import com.challenge.forochallenge.persistence.dto.message.response.MessageResponse;
import com.challenge.forochallenge.persistence.entity.Message;
import com.challenge.forochallenge.persistence.entity.Topic;
import com.challenge.forochallenge.persistence.entity.User;
import com.challenge.forochallenge.persistence.mapper.MessageMapper;
import com.challenge.forochallenge.persistence.repository.MessageRepository;
import com.challenge.forochallenge.persistence.repository.TopicRepository;
import com.challenge.forochallenge.persistence.repository.UserRepository;
import com.challenge.forochallenge.service.IMessageService;
import com.challenge.forochallenge.utils.DeleteResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {

  private final UserRepository userRepository;
  private final MessageRepository messageRepository;
  private final TopicRepository topicRepository;

  @Autowired
  public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository,
      TopicRepository topicRepository) {
    this.userRepository = userRepository;
    this.messageRepository = messageRepository;
    this.topicRepository = topicRepository;
  }


  @Override
  public List<MessageResponse> allMessages() {
    return MessageMapper.toMessageDtoList(messageRepository.findAll());
  }

  @Override
  public List<MessageResponse> allMessagesByUser(Long userId) {
    List<Message> messages = messageRepository.findAllByUserId(userId);

    if (messages.isEmpty()) {
      throw new MessageNotFoundException("No hay messages disponibles para este usuario");
    }

    return MessageMapper.toMessageDtoList(messages);
  }

  @Override
  public List<MessageResponse> allMessagesByTopic(Long topicId) {
    List<Message> messages = messageRepository.findAllByTopicId(topicId);

    if (messages.isEmpty()) {
      throw new MessageNotFoundException("No hay messages para este Topic");
    }

    return MessageMapper.toMessageDtoList(messages);
  }

  @Override
  public MessageResponse getMessageById(Long messageId) {
    return MessageMapper.toMessageDto(messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found")));
  }

  @Override
  @Transactional
  public MessageResponse createMessage(MessageRequest request, Long userId) {
    Topic topic = topicRepository.findById(request.topicId())
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Message message = new Message();
    message.setContent(request.content());
    message.setUser(user);
    message.setTopic(topic);
    return MessageMapper.toMessageDto(messageRepository.save(message));
  }

  @Override
  @Transactional
  public MessageResponse updateMessage(UpdateMessageRequest request, Long messageId, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Message messageExists = messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found"));

    if (!Objects.equals(userExists.getId(), messageExists.getUser().getId())) {
      throw new IllegalArgumentException("Este Message pertenece a otro usuario");
    }

    messageExists.setContent(request.content());
    return MessageMapper.toMessageDto(messageRepository.save(messageExists));
  }

  @Override
  @Transactional
  public DeleteResponse deleteMessage(Long userId, Long messageId) {
    userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Message message = messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found"));
    messageRepository.delete(message);
    return new DeleteResponse("Message eliminado");
  }
}
