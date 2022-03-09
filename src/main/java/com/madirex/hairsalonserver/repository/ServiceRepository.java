package com.madirex.hairsalonserver.repository;

import com.madirex.hairsalonserver.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, String> {
    List<Service> findByNameContainsIgnoreCase(Optional<String> name);
}
