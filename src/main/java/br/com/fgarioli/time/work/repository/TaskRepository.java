/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fgarioli.time.work.repository;

import br.com.fgarioli.time.work.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fernando
 */
public interface TaskRepository  extends JpaRepository<Task, Integer> {
    
}
