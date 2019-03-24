package com.crud.tasks1.trello.client;


import com.crud.tasks1.domain.CreatedTrelloCard.CreatedTrelloCard;
import com.crud.tasks1.domain.CreatedTrelloCard.badgesDto.BadgesDto;
import com.crud.tasks1.domain.TrelloBoardDto;
import com.crud.tasks1.domain.TrelloCardDto;
import com.crud.tasks1.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Mock
    private BadgesDto badgesDto;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUser()).thenReturn("donniedarko5");
    }


    @Test
    public void shouldCreateCard() throws URISyntaxException {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test Description",
                "top",
                "test_id"

        );

        URI url = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com",
                badgesDto
        );

        when(restTemplate.postForObject(url, null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
        assertEquals(badgesDto, newCard.getBadges());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/donniedarko5/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);

        //when
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //then
        assertEquals(new ArrayList<>(),fetchedTrelloBoards);
        assertEquals(0,fetchedTrelloBoards.size());
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {

        //Given

        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/donniedarko5/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();


        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

}