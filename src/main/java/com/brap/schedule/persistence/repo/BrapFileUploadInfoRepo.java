package com.brap.schedule.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brap.persistence.entity.BrapFileUploadInfo;

@Repository
public interface BrapFileUploadInfoRepo extends JpaRepository<BrapFileUploadInfo, String> {

	List<BrapFileUploadInfo> findByJobName(String jobName);
}
