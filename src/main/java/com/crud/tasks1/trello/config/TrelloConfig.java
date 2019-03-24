package com.crud.tasks1.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TrelloConfig {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;

    @Value("${trello.app.key}")
    private String trelloAppKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.user}")
    private String trelloUser;

    public String getTrelloApiEndpoint() {
        return trelloApiEndpoint;
    }

    public String getTrelloAppKey() {
        return trelloAppKey;
    }

    public String getTrelloToken() {
        return trelloToken;
    }

    public String getTrelloUser() {
        return trelloUser;
    }
}
