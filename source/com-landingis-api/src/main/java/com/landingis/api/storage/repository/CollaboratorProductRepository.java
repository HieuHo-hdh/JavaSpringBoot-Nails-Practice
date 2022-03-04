package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.CollaboratorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollaboratorProductRepository extends JpaRepository<CollaboratorProduct, Long>, JpaSpecificationExecutor<CollaboratorProduct> {

}
