package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.ImportExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImportExportRepository extends JpaRepository<ImportExport, Long>, JpaSpecificationExecutor<ImportExport> {

}