package com.example.todo.controllers;

import com.example.todo.entities.Todo;
import com.example.todo.repositories.TodoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoRepository repo;

    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    // GET method to retrieve all todos
    @GetMapping
    public List<Todo> list() {
        return repo.findAll();
    }

    // POST method to create a new todo
    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        return repo.save(todo);
    }
}
