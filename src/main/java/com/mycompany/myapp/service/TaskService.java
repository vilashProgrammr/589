package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Priority;
import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.repository.PriorityRepository;
import com.mycompany.myapp.repository.TaskRepository;
import com.mycompany.myapp.service.dto.TaskDTO;
import com.mycompany.myapp.service.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Task.
 */
@Service
@Transactional
public class TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;
    
    private final PriorityRepository priorityRepository;
    
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.priorityRepository = priorityRepository;
    }
    
    
    public List<TaskDTO> findAllByPriority(String priorityName) {
    	Priority priority = this.priorityRepository.findByName(priorityName);
    	return taskRepository.findAllByPriorityId(priority.getId()).stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
    	
    }
    /**
     * Save a task.
     *
     * @param taskDTO the entity to save
     * @return the persisted entity
     */
    public TaskDTO save(TaskDTO taskDTO) {
   
    	return taskDTO;
    }

    /**
     * Get all the tasks.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.debug("Request to get all Tasks");
        List<Task> taskList = this.taskRepository.findAll();
        List<TaskDTO> listToSend = new ArrayList<TaskDTO>();
        for(Task task: taskList) {
        	TaskDTO taskDTO = new TaskDTO();
        	taskDTO.setDate(task.getDate());
        	taskDTO.setDescription(task.getDescription());
        	taskDTO.setId(task.getId());
        	
        	Priority priority = this.priorityRepository.findOne(task.getPriorityId());
        	taskDTO.setPriority(priority.getName());
        	taskDTO.setPriorityId(task.getPriorityId());
        	
        	listToSend.add(taskDTO);
        }
        return listToSend;
    }

    /**
     * Get one task by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public TaskDTO findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        Task task = taskRepository.findOne(id);
        return taskMapper.toDto(task);
    }

    /**
     * Delete the task by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.delete(id);
    }
}
//Save task solution
//copy below code to pass this solution
/*
log.debug("Request to save Task : {}", taskDTO);
Task task = taskMapper.toEntity(taskDTO);
Priority priority = priorityRepository.findByName(taskDTO.getPriority());
task.setPriorityId(priority.getId());
task = taskRepository.save(task);
TaskDTO taskDTOToReturn = taskMapper.toDto(task);
taskDTOToReturn.setPriority(priority.getName());
return taskDTOToReturn;
*/
