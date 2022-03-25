package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.SalaryPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalaryPeriodRepository extends JpaRepository<SalaryPeriod, Long>, JpaSpecificationExecutor<SalaryPeriod> {

}