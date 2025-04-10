package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void shouldReturnTodoList() throws Exception {
        Todo sample = new Todo(1L, "Test", "Testing", false);
        when(todoRepository.findAll()).thenReturn(Collections.singletonList(sample));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test"));
    }
}
