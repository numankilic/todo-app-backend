package com.ownprojects.todoappbackend.web;

import com.ownprojects.todoappbackend.domain.TodoItem;
import com.ownprojects.todoappbackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// http://localhost:8080
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems(){
        List<TodoItem> todoItems = todoService.fetchAllTodoItems();
        return ResponseEntity.ok(todoItems);
    }

    @PutMapping("/api/todoItems/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable(value = "id") Integer id, @RequestBody TodoItem todoItem){
        TodoItem updatedTodoItem = todoService.updateTodoItem(id, todoItem);
        return ResponseEntity.ok(updatedTodoItem);
    }

    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewTodoItem(){
        TodoItem todoItem =  todoService.createTodoItem();
        return ResponseEntity.ok(todoItem);
    }

    @DeleteMapping("/api/todoItems/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable(value = "id")Integer id){
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok("ok");
    }
}
