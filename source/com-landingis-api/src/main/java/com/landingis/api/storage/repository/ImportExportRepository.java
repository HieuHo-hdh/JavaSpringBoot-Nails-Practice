package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.ImportExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ImportExportRepository extends JpaRepository<ImportExport, Long>, JpaSpecificationExecutor<ImportExport> {

    @Query(value =
            "select sum(money)from nails_import_export where kind = :kind and created_date BETWEEN :FromDate and :ToDate", nativeQuery = true)
    Double getImportExportFromDateToDate(@Param("kind") Integer kind, @Param("FromDate") Date FromDate, @Param("ToDate") Date ToDate);

    @Query(value =
            "select sum(money)from nails_import_export where kind = :kind and created_date <= :ToDate", nativeQuery = true)
    Double getImportExportToDate(@Param("kind") Integer kind, @Param("ToDate") Date ToDate);

    @Query(value =
            "select sum(money)from nails_import_export where kind = :kind and created_date >= :FromDate", nativeQuery = true)
    Double getImportExportFromDate(@Param("kind") Integer kind, @Param("FromDate") Date FromDate);

    @Query(value = "SELECT SUM(money)FROM nails_import_export where kind = :kind", nativeQuery = true)
    Double getImportExport(@Param("kind") Integer kind);


}