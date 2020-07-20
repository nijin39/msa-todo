package com.proserve.msa.todo.command.application.command;


import com.proserve.msa.todo.query.ui.dto.TodoInfo;

public class UpdateTodoCommand implements TodoCommand {
    private String id;
    private TodoInfo todo;

    public UpdateTodoCommand(String id, TodoInfo todo) {
        this.id = id;
        this.todo = todo;
    }

    public String getId() {
        return id;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
