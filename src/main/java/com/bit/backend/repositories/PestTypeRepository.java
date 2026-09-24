package com.bit.backend.repositories;

import com.bit.backend.entities.PestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PestTypeRepository extends JpaRepository<PestTypeEntity, Integer> {
}
