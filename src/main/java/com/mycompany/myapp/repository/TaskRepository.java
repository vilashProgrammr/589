package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Task;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Task entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findAllByPriorityId(Long priorityId);
}
