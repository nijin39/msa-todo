package com.proserve.msa.todo.config;

import com.proserve.msa.todo.query.application.TodoUpdateServiceImpl;
import com.proserve.msa.todo.query.application.TodoViewEventSubscriber;
import com.proserve.msa.todo.query.domain.TodoRepository;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@EnableEventHandlers
public class TodoViewBackendConfiguration {

    @Bean
    public TodoViewEventSubscriber todoViewEventSubscriber(TodoUpdateServiceImpl queryService) {
        return new TodoViewEventSubscriber(queryService);
    }

    @Bean
    public TodoUpdateServiceImpl queryService(TodoRepository repository) {
        return new TodoUpdateServiceImpl(repository);
    }

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new MappingJackson2HttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
}


