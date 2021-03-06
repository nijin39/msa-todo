package com.proserve.msa.todo.query.ui;

import com.proserve.msa.todo.common.controller.BaseController;
import com.proserve.msa.todo.common.dto.ResourceWithUrl;
import com.proserve.msa.todo.query.application.TodoUpdateService;
import com.proserve.msa.todo.query.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by popikyardo on 23.03.16.
 */
@RestController
@RequestMapping(value = "/todos")
public class TodoHateoasController extends BaseController {

    @Autowired
    private TodoUpdateService queryService;

    @RequestMapping(value = "/{todo-id}", method = GET)
    public CompletableFuture<ResourceWithUrl> getTodo(@PathVariable("todo-id") String id) {
        return queryService.findById(id).thenApply(this::toResource);
    }

    public ResourceWithUrl toResource(Todo todo) {
        ResourceWithUrl<Todo> result = new ResourceWithUrl<>(todo);
        if (todo != null) {
            result.setUrl(linkTo(methodOn(TodoHateoasController.class).getTodo(todo.getId())).withSelfRel().getHref());
        }
        return result;
    }
}
