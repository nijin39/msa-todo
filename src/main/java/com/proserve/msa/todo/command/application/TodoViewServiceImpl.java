package com.proserve.msa.todo.command.application;


import com.proserve.msa.todo.query.application.TodoUpdateService;
import com.proserve.msa.todo.query.domain.Todo;
import com.proserve.msa.todo.query.domain.TodoRepository;
import io.eventuate.CompletableFutureUtil;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class TodoViewServiceImpl implements TodoUpdateService {

    private TodoRepository repository;

    public TodoViewServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> getAll() {
        return repository.findAll();
    }

    @Override
    public CompletableFuture<Todo> findById(String todoId) {
        Todo res = repository.findById(todoId).get();
        if (res != null) {
            return CompletableFuture.completedFuture(res);
        }
        return CompletableFutureUtil.failedFuture(new EntityNotFoundException("No todo found for given id"));
    }

}
