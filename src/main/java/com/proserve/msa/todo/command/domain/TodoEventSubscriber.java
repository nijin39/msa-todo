package com.proserve.msa.todo.command.domain;

import com.proserve.msa.todo.command.application.command.DeleteTodoCommand;
import com.proserve.msa.todo.query.domain.event.TodoDeletionRequestedEvent;
import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import java.util.concurrent.CompletableFuture;


@EventSubscriber(id = "todoCommandSideEventHandlers")
public class TodoEventSubscriber {

    @EventHandlerMethod
    public CompletableFuture<EntityWithIdAndVersion<TodoAggregate>> deleteTodo(EventHandlerContext<TodoDeletionRequestedEvent> ctx) {
        String todoId = ctx.getEvent().getTodoId();

        return ctx.update(TodoAggregate.class, todoId, new DeleteTodoCommand());
    }

}
