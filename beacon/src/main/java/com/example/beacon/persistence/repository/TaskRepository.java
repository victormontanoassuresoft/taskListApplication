package com.example.beacon.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.beacon.persistence.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
