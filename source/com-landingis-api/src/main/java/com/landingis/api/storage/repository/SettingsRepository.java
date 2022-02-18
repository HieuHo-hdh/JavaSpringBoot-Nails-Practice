package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SettingsRepository  extends JpaRepository<Settings, Long>, JpaSpecificationExecutor<Settings> {
    public Settings findFirstByKey(String name);
}
