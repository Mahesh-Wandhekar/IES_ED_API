package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nt.Entity.DcChildren;


public interface DcChildrenRepo extends JpaRepository<DcChildren, Integer>{

	
	@Query("from DcChildren where caseNum=:caseNum")
	public DcChildren getKids( Integer caseNum);
}
