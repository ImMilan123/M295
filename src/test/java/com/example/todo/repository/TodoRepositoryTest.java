package com.example.todo.repository;

import com.example.todo.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository repository;

    @Test
    void shouldSaveTodoToDatabase() {
        Todo todo = new Todo();
        todo.setTitle("JUnit Test");
        todo.setDescription("Write tests");
        todo.setCompleted(false);

        Todo saved = repository.save(todo);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("JUnit Test");
    }
}
