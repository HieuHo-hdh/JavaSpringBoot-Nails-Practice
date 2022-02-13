package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>, JpaSpecificationExecutor<Collaborator> {
    
}
