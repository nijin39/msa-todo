package com.proserve.msa.todo.query.domain;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository<Todo, String> {

}
