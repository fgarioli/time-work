/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.time.work.service;

import br.com.fgarioli.time.work.model.Task;
import br.com.fgarioli.time.work.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fernando
 */
@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task t) {
        return taskRepository.save(t);
    }

    public Task update(Task t) {
        findById(t);
        return taskRepository.save(t);
    }

    public void delete(Task t) {
        findById(t);
        taskRepository.delete(t);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Task t) {
        return taskRepository.findById(t.getIdTask());
    }

}
