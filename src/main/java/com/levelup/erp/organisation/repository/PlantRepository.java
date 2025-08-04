package com.levelup.erp.organisation.repository;

import com.levelup.erp.organisation.models.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
}
