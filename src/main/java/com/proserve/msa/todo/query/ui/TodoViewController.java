package com.proserve.msa.todo.query.ui;

import com.proserve.msa.todo.common.controller.BaseController;
import com.proserve.msa.todo.common.dto.ResourceWithUrl;
import com.proserve.msa.todo.query.application.TodoUpdateServiceImpl;
import com.proserve.msa.todo.query.domain.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/todos")
public class TodoViewController extends BaseController {

    @Autowired
    private TodoUpdateServiceImpl queryService;

    @RequestMapping(method = GET)
    public HttpEntity<Collection<ResourceWithUrl>> listAll() {
        List<ResourceWithUrl> resourceWithUrls = queryService.getAll().stream().map(this::toResource).collect(Collectors.toList());
        return new ResponseEntity<>(resourceWithUrls, OK);
    }

    public ResourceWithUrl toResource(Todo todo) {
        ResourceWithUrl<Todo> result = new ResourceWithUrl<>(todo);
        if (todo != null) {
            result.setUrl(linkTo(methodOn(TodoHateoasController.class).getTodo(todo.getId())).withSelfRel().getHref());
        }
        return result;
    }
}
