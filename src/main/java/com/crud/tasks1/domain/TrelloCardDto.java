package com.crud.tasks1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrelloCardDto {

    private String name;

    private String description;

    private String pos;

    private String listId;


}