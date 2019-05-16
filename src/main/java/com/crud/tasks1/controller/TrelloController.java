package com.crud.tasks1.controller;

import com.crud.tasks1.domain.CreatedTrelloCard.CreatedTrelloCardDto;
import com.crud.tasks1.domain.TrelloBoardDto;
import com.crud.tasks1.domain.TrelloCardDto;
import com.crud.tasks1.service.TrelloService;
import com.crud.tasks1.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    private TrelloFacade trelloFacade;

    @Autowired
    public TrelloController(TrelloFacade trelloFacade) {
        this.trelloFacade = trelloFacade;
    }

    @PostMapping(value = "/cards")
    public CreatedTrelloCardDto createNewCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createdCard(trelloCardDto);
    }

    @GetMapping(value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }
}