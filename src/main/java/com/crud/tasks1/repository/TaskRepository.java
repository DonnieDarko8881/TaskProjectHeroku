package com.crud.tasks1.repository;

import com.crud.tasks1.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    Optional<Task> findById(Long taskId);

    @Override
    Task save(Task task);

    @Override
    void delete(Long taskId);

    @Override
    long count();

}
