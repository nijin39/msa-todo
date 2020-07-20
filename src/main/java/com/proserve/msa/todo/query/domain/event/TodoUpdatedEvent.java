package com.proserve.msa.todo.query.domain.event;

import com.proserve.msa.todo.query.ui.dto.TodoInfo;

public class TodoUpdatedEvent implements TodoEvent {

    private TodoInfo todo;

    private TodoUpdatedEvent() {
    }

    public TodoUpdatedEvent(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }

    public void setTodo(TodoInfo todo) {
        this.todo = todo;
    }
}