package com.proserve.msa.todo.query.application;


import com.proserve.msa.todo.query.domain.Todo;
import com.proserve.msa.todo.query.domain.TodoRepository;
import io.eventuate.CompletableFutureUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

@Service
public class TodoUpdateServiceImpl implements TodoUpdateService {

    private TodoRepository repository;

    public TodoUpdateServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public List<Todo> getAll() {
        return repository.findAll();
    }

    public void remove(String id) {
        repository.deleteById(id);
    }

    public void removeAll() {
        repository.deleteAll();
    }

    public CompletableFuture<Todo> findById(String todoId) {
        Todo res = repository.findById(todoId).get();
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new NoSuchElementException("No todo with given id found"));
    }

}
