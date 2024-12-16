package com.challenge.forochallenge.service;

import com.challenge.forochallenge.persistence.dto.message.request.MessageRequest;
import com.challenge.forochallenge.persistence.dto.message.request.UpdateMessageRequest;
import com.challenge.forochallenge.persistence.dto.message.response.MessageResponse;
import com.challenge.forochallenge.utils.DeleteResponse;
import java.util.List;

public interface IMessageService {

  List<MessageResponse> allMessages();

  List<MessageResponse> allMessagesByUser(Long userId);

  List<MessageResponse> allMessagesByTopic(Long topicId);

  MessageResponse getMessageById(Long messageId);

  MessageResponse createMessage(MessageRequest request, Long userId);

  MessageResponse updateMessage(UpdateMessageRequest request, Long messageId, Long userId);

  DeleteResponse deleteMessage(Long userId, Long messageId);
}
