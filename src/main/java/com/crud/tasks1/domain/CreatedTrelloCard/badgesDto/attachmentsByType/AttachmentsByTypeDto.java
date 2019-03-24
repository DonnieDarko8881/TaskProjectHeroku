package com.crud.tasks1.domain.CreatedTrelloCard.badgesDto.attachmentsByType;

import com.crud.tasks1.domain.CreatedTrelloCard.badgesDto.attachmentsByType.trelloDto.TrelloDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AttachmentsByTypeDto {

    @JsonProperty("trello")
    private TrelloDto trello;
}
