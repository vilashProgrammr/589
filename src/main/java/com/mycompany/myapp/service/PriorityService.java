package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Priority;
import com.mycompany.myapp.repository.PriorityRepository;
import com.mycompany.myapp.service.dto.PriorityDTO;
import com.mycompany.myapp.service.mapper.PriorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Priority.
 */
@Service
@Transactional
public class PriorityService {

    private final Logger log = LoggerFactory.getLogger(PriorityService.class);

    private final PriorityRepository priorityRepository;

    private final PriorityMapper priorityMapper;

    public PriorityService(PriorityRepository priorityRepository, PriorityMapper priorityMapper) {
        this.priorityRepository = priorityRepository;
        this.priorityMapper = priorityMapper;
    }

    /**
     * Save a priority.
     *
     * @param priorityDTO the entity to save
     * @return the persisted entity
     */
    public PriorityDTO save(PriorityDTO priorityDTO) {
        log.debug("Request to save Priority : {}", priorityDTO);
        Priority priority = priorityMapper.toEntity(priorityDTO);
        priority = priorityRepository.save(priority);
        return priorityMapper.toDto(priority);
    }

    /**
     * Get all the priorities.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PriorityDTO> findAll() {
        log.debug("Request to get all Priorities");
        return priorityRepository.findAll().stream()
            .map(priorityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one priority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public PriorityDTO findOne(Long id) {
        log.debug("Request to get Priority : {}", id);
        Priority priority = priorityRepository.findOne(id);
        return priorityMapper.toDto(priority);
    }

    /**
     * Delete the priority by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Priority : {}", id);
        priorityRepository.delete(id);
    }
}
