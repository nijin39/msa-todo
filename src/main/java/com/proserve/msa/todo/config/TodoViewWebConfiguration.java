package com.proserve.msa.todo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import({TodoViewBackendConfiguration.class})
@ComponentScan({"com.proserve.msa.todo.common",
        "com.proserve.msa.todo.query.ui"})
public class TodoViewWebConfiguration implements WebMvcConfigurer {
}
