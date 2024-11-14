package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.nt.Entity.DcIncome;

public interface DcIncomeRepo extends JpaRepository<DcIncome, Integer>{

	@Query("from DcIncome where caseNum=:caseNum")
	public DcIncome getIncome(Integer caseNum);
}
