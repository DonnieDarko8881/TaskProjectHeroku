package com.crud.tasks1.trello.facade;

import com.crud.tasks1.domain.CreatedTrelloCard.CreatedTrelloCardDto;
import com.crud.tasks1.domain.TrelloBoard;
import com.crud.tasks1.domain.TrelloBoardDto;
import com.crud.tasks1.domain.TrelloCard;
import com.crud.tasks1.domain.TrelloCardDto;
import com.crud.tasks1.mapper.TrelloMapper;
import com.crud.tasks1.service.TrelloService;
import com.crud.tasks1.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloFacade {
    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    private TrelloService trelloService;
    private TrelloMapper trelloMapper;
    private TrelloValidator trelloValidator;

    @Autowired
    public TrelloFacade(TrelloService trelloService, TrelloMapper trelloMapper, TrelloValidator trelloValidator) {
        this.trelloService = trelloService;
        this.trelloMapper = trelloMapper;
        this.trelloValidator = trelloValidator;
    }


    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validadteTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createdCard(final TrelloCardDto trelloCardDto){
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return  trelloService.createdTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }

}
