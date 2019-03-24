package com.crud.tasks1.service;

import com.crud.tasks1.config.AdminConfig;

import com.crud.tasks1.domain.CreatedTrelloCard.CreatedTrelloCard;
import com.crud.tasks1.domain.Mail;


import com.crud.tasks1.domain.TrelloBoardDto;
import com.crud.tasks1.domain.TrelloCardDto;
import com.crud.tasks1.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    private final static String SUBJECT = "Tasks: New Trello Card";

    private TrelloClient trelloClient;
    private SimpleEmailService emailService;
    private AdminConfig adminConfig;

    @Autowired
    public TrelloService(TrelloClient trelloClient, SimpleEmailService emailService, AdminConfig adminConfig) {
        this.trelloClient = trelloClient;
        this.emailService = emailService;
        this.adminConfig = adminConfig;
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(card ->emailService.send(new Mail(
                adminConfig.getAdminMail(), null, SUBJECT,
                "New Card: " + card.getName() + " has been created on your Trello account")));
        return newCard;
    }
}
