package com.proserve.msa.todo.query.domain.event;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "net.chrisrichardson.eventstore.examples.todolist.todoservice.backend.domain.TodoAggregate")
public interface TodoEvent extends Event {
}
