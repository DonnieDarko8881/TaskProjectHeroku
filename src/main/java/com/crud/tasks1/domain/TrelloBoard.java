package com.crud.tasks1.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class TrelloBoard {
    private String id;
    private String name;
    private List<TrelloList> lists;
}
