package com.crud.tasks1.controller;

import com.crud.tasks1.domain.CreatedTrelloCard.CreatedTrelloCard;
import com.crud.tasks1.domain.TrelloBoardDto;
import com.crud.tasks1.domain.TrelloCardDto;
import com.crud.tasks1.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    private TrelloService trelloService;

    @Autowired
    public TrelloController(TrelloService trelloService) {
        this.trelloService = trelloService;
    }

    @PostMapping(value = "createTrelloCard")
    public CreatedTrelloCard createNewCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloService.createdTrelloCard(trelloCardDto);
    }

    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }
}