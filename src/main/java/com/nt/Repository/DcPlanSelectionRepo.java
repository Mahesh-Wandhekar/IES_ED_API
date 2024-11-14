package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.Param;

import com.nt.Entity.DcPlanSelectionEntity;

public interface DcPlanSelectionRepo extends JpaRepository<DcPlanSelectionEntity, Integer> {

	@Query("from DcPlanSelectionEntity where caseNum=:caseNum")
	public DcPlanSelectionEntity getPlanName(Integer caseNum);

	
}
