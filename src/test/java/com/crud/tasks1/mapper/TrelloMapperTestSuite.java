package com.crud.tasks1.mapper;

import com.crud.tasks1.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "toDo", false);
        trelloListDtoList.add(trelloListDto);
        TrelloBoardDto trelloBoard = new TrelloBoardDto("1", "test", trelloListDtoList);
        trelloBoardDtoList.add(trelloBoard);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertNotEquals(trelloBoardDtoList, trelloBoardList);
        assertEquals("test", trelloBoardList.get(0).getName());
        assertEquals("1", trelloBoardList.get(0).getId());
        assertEquals("toDo", trelloBoardList.get(0).getLists().get(0).getName());

    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        List<TrelloList> listOfTrelloList = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "toDo", false);
        listOfTrelloList.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("1", "test", listOfTrelloList);
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertNotEquals(trelloBoardDtoList, trelloBoardList);
        assertEquals("test", trelloBoardDtoList.get(0).getName());
        assertEquals("1", trelloBoardDtoList.get(0).getId());
        assertEquals("toDo", trelloBoardDtoList.get(0).getLists().get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> listOfTrelloListDto = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "toDo", false);
        listOfTrelloListDto.add(trelloListDto);
        //When
        List<TrelloList> listOfTrelloList = trelloMapper.mapToList(listOfTrelloListDto);
        //Then
        assertNotEquals(listOfTrelloListDto, listOfTrelloList);
        assertEquals("1", listOfTrelloList.get(0).getId());
        assertEquals("toDo", listOfTrelloList.get(0).getName());
        assertEquals(false, listOfTrelloList.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> listOfTrelloList = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "toDo", false);
        listOfTrelloList.add(trelloList);
        //When
        List<TrelloListDto> listOfTrelloListDto = trelloMapper.mapToListDto(listOfTrelloList);
        //Then
        assertNotEquals(listOfTrelloListDto, listOfTrelloList);
        assertEquals("1", listOfTrelloListDto.get(0).getId());
        assertEquals("toDo", listOfTrelloListDto.get(0).getName());
        assertEquals(false, listOfTrelloListDto.get(0).isClosed());
    }

    @Test
    public void TestMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Kill", "Kill Monster", "top", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertNotEquals(trelloCard, trelloCardDto);
        assertEquals("Kill", trelloCardDto.getName());
        assertEquals("Kill Monster", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Kill", "Kill Monster", "top", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertNotEquals(trelloCard, trelloCardDto);
        assertEquals("Kill", trelloCard.getName());
        assertEquals("Kill Monster", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }
}