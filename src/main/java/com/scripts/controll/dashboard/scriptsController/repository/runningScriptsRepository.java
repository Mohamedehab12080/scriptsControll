package com.scripts.controll.dashboard.scriptsController.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scripts.controll.dashboard.scriptsController.model.runningScript;

@Repository
public interface runningScriptsRepository  extends JpaRepository<runningScript,Long>{

	Optional<runningScript> findByScriptName(String scriptName);
}
