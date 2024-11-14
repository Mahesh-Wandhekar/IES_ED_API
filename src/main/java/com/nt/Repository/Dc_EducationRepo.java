package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.Entity.DcEducation;


public interface Dc_EducationRepo extends JpaRepository<DcEducation, Integer> {

	@Query("from DcEducation where caseNum=:caseNum")
	public DcEducation getEdu( Integer caseNum);
}
