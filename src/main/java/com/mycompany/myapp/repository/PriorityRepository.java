package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Priority;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Priority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
	Priority findByName(String name);
}
