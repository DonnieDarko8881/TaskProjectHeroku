package com.crud.tasks1.domain.CreatedTrelloCard.badgesDto.attachmentsByType.trelloDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrelloDto {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}
