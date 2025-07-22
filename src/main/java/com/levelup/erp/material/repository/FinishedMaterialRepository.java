package com.levelup.erp.material.repository;

import com.levelup.erp.material.models.FinishedMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedMaterialRepository extends JpaRepository<FinishedMaterial, Long> {

}
