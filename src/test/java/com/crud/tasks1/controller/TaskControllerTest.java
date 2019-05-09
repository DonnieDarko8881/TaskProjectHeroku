package com.crud.tasks1.controller;

import com.crud.tasks1.domain.Task;
import com.crud.tasks1.domain.TaskDto;
import com.crud.tasks1.mapper.TaskMapper;
import com.crud.tasks1.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private Gson gson;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    public void fetchEmptyTasksList() throws Exception {
        //Given
        List<TaskDto> taskDtos = Collections.emptyList();
        when(mapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtos);
        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void fetchTaskList() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(1L, "Test", "Test Content"));

        when(mapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtos);

        //When&Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Test")))
                .andExpect(jsonPath("$[0].content", is("Test Content")));
    }


    @Test
    public void shouldReturnTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test", "Test Content");
        TaskDto taskDto = new TaskDto(1L, "Test", "Test Content");

        when(service.getTaskById(anyLong())).thenReturn(Optional.of(task));
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        //When&Then
        mockMvc.perform(get("/v1/task/getTask").param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test")))
                .andExpect(jsonPath("$.content", is("Test Content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test Content");
        //When&Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(gson.toJson(taskDto)))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(any());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test Content");
        TaskDto taskDtoUpdated = new TaskDto(1L, "Test update", "Content Updated");

        when(mapper.mapToTaskDto(service.saveTask(mapper.mapToTask(taskDto)))).thenReturn(taskDtoUpdated);

        //When&Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(gson.toJson(taskDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Test update")))
                .andExpect(jsonPath("$.content", is("Content Updated")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test Content");

        //When&Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(gson.toJson(taskDto)))
                .andExpect(status().isOk());
        verify(service, times(1)).saveTask(any());
    }
}