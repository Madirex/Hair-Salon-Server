package com.madirex.hairsalonserver.repository;

import com.madirex.hairsalonserver.model.Login;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Page<Login> findByUser_Id(Pageable pageable, String userId);
}
