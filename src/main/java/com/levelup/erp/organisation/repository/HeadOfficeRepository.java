package com.levelup.erp.organisation.repository;

import com.levelup.erp.organisation.models.HeadOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadOfficeRepository extends JpaRepository<HeadOffice, Long> {

}
