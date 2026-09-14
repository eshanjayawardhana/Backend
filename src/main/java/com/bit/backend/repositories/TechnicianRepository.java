package com.bit.backend.repositories;

import com.bit.backend.entities.TechnicianEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Integer> {
}
