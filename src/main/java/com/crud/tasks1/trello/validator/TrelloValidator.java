package com.crud.tasks1.trello.validator;


import com.crud.tasks1.domain.TrelloBoard;
import com.crud.tasks1.domain.TrelloCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TrelloValidator {
    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public void validateCard(final TrelloCard trelloCard) {
        if(trelloCard.getName().contains("test")){
            LOGGER.info("Somone is testing my application!");
        } else{
            LOGGER.info("Seems that my application is used in proper way.");
        }
    }

    public List<TrelloBoard> validadteTrelloBoards(final List<TrelloBoard> trelloBoards){
        LOGGER.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards have been filtred. Current list size: " + filteredBoards.size());
        return  filteredBoards;
    }
}
