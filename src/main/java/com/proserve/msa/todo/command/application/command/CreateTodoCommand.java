package com.proserve.msa.todo.command.application.command;


import com.proserve.msa.todo.query.ui.dto.TodoInfo;

public class CreateTodoCommand implements TodoCommand {

    private TodoInfo todo;

    public CreateTodoCommand(TodoInfo todo) {
        this.todo = todo;
    }

    public TodoInfo getTodo() {
        return todo;
    }
}
