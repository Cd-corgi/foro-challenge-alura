package com.challenge.forochallenge.persistence.dto.topico.response;

import com.challenge.forochallenge.persistence.dto.message.response.MessageResponse;
import com.challenge.forochallenge.persistence.dto.user.response.UserResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.List;

public record TopicResponse(
    Long id,
    String title,
    String curso,
    @JsonProperty("created_at")
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate createdAt,
    UserResponse user,
    List<MessageResponse> messages
) {

}
