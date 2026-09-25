package com.bit.backend.repositories;

import com.bit.backend.entities.ServiceRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequestEntity,Integer> {
}
