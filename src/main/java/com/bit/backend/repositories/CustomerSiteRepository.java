package com.bit.backend.repositories;

import com.bit.backend.entities.CustomerSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerSiteRepository extends JpaRepository<CustomerSiteEntity, Integer> {
}
