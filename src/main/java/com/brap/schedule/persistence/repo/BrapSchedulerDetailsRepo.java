package com.brap.schedule.persistence.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brap.persistence.entity.BrapSchedulerDetails;

@Repository
public interface BrapSchedulerDetailsRepo extends JpaRepository<BrapSchedulerDetails, String> {
	
	Optional<BrapSchedulerDetails> findByJobName(String jobName);
}
