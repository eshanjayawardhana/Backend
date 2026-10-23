package com.bit.backend.repositories;

import com.bit.backend.entities.ServiceTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceTypeRepository extends JpaRepository<ServiceTypeEntity,Integer> {
}
