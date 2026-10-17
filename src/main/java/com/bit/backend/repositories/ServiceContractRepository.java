package com.bit.backend.repositories;

import com.bit.backend.entities.ServiceContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceContractRepository extends JpaRepository<ServiceContractEntity,Integer> {
}
