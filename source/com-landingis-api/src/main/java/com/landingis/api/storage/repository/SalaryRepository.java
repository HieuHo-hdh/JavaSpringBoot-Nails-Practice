package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SalaryRepository extends JpaRepository<Salary, Long>, JpaSpecificationExecutor<Salary> {

}
