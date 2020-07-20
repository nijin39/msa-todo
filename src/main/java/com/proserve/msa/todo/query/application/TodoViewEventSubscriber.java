package com.proserve.msa.todo.query.application;


import com.proserve.msa.todo.query.domain.Todo;
import com.proserve.msa.todo.query.domain.event.TodoCreatedEvent;
import com.proserve.msa.todo.query.domain.event.TodoDeletedEvent;
import com.proserve.msa.todo.query.domain.event.TodoUpdatedEvent;
import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

@EventSubscriber(id = "todoQuerySideEventHandlers")
public class TodoViewEventSubscriber {

    private TodoUpdateServiceImpl todoQueryServiceImpl;

    public TodoViewEventSubscriber(TodoUpdateServiceImpl todoQueryServiceImpl) {
        this.todoQueryServiceImpl = todoQueryServiceImpl;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<TodoCreatedEvent> de) {
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());
        todoQueryServiceImpl.save(todo);
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<TodoDeletedEvent> de) {
        todoQueryServiceImpl.remove(de.getEntityId());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<TodoUpdatedEvent> de) {
        Todo todo = new Todo(de.getEvent().getTodo());
        todo.setId(de.getEntityId());
        todoQueryServiceImpl.save(todo);
    }
}
