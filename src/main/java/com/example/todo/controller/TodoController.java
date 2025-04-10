package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@Tag(name = "Todos", description = "Manage ToDo tasks (CRUD)")
public class TodoController {

    private final TodoRepository repository;

    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @Operation(summary = "Get all todos")
    @ApiResponse(responseCode = "200", description = "Returns all todos")
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Create a new todo")
    @ApiResponse(responseCode = "201", description = "Todo created successfully")
    public Todo create(@Valid @RequestBody Todo todo) {
        return repository.save(todo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing todo")
    @ApiResponse(responseCode = "200", description = "Todo updated successfully")
    public Todo update(@PathVariable Long id, @Valid @RequestBody Todo todo) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(todo.getTitle());
            existing.setDescription(todo.getDescription());
            existing.setCompleted(todo.isCompleted());
            return repository.save(existing);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a todo")
    @ApiResponse(responseCode = "204", description = "Todo deleted successfully")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
