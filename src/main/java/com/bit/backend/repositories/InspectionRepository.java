package com.bit.backend.repositories;

import com.bit.backend.entities.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionRepository extends JpaRepository<InspectionEntity, Integer> {
}
