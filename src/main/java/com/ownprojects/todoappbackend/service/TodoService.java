package com.ownprojects.todoappbackend.service;

import com.ownprojects.todoappbackend.domain.TodoItem;
import com.ownprojects.todoappbackend.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<TodoItem> fetchAllTodoItems() {
        return todoRepository.fetchAllTodoItems();
    }


    public TodoItem updateTodoItem(Integer id, TodoItem todoItem) {
        Optional<TodoItem> todoOpt = todoRepository.fetchAllTodoItems()
                .stream()
                .filter(item -> item.getId().equals(id))
                .findAny();
        if (todoOpt.isPresent()) {
            TodoItem item = todoOpt.get();
            item.setDone(todoItem.getDone());
            item.setTask(todoItem.getTask());
            return item;
        }
        return null;
    }

    public TodoItem createTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setDone(false);

        todoItem = todoRepository.save(todoItem);
        todoItem.setTask("Task #" + todoItem.getId());
        return todoItem;
    }


    public void deleteTodoItem(Integer id) {
        todoRepository.delete(id);
    }
}
