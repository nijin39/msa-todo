package com.proserve.msa.todo.command.domain;


import com.proserve.msa.todo.command.application.command.CreateTodoCommand;
import com.proserve.msa.todo.command.application.command.DeleteTodoCommand;
import com.proserve.msa.todo.command.application.command.TodoCommand;
import com.proserve.msa.todo.command.application.command.UpdateTodoCommand;
import com.proserve.msa.todo.query.ui.dto.TodoInfo;
import com.proserve.msa.todo.query.domain.event.TodoCreatedEvent;
import com.proserve.msa.todo.query.domain.event.TodoDeletedEvent;
import com.proserve.msa.todo.query.domain.event.TodoUpdatedEvent;
import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;

import java.util.Collections;
import java.util.List;

public class TodoAggregate extends ReflectiveMutableCommandProcessingAggregate<TodoAggregate, TodoCommand> {

    private TodoInfo todo;
    private boolean deleted;

    public List<Event> process(CreateTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoCreatedEvent(cmd.getTodo()));
    }

    public List<Event> process(UpdateTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoUpdatedEvent(cmd.getTodo()));
    }

    public List<Event> process(DeleteTodoCommand cmd) {
        if (this.deleted) {
            return Collections.emptyList();
        }
        return EventUtil.events(new TodoDeletedEvent());
    }


    public void apply(TodoCreatedEvent event) {
        this.todo = event.getTodo();
    }

    public void apply(TodoUpdatedEvent event) {
        this.todo = event.getTodo();
    }

    public void apply(TodoDeletedEvent event) {
        this.deleted = true;
    }

    public TodoInfo getTodo() {
        return todo;
    }

}


