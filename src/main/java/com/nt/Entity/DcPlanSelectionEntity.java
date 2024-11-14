package com.nt.Entity;


import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DcPlanSelectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planSelectionId;
	
	private String selPlan;
	
	@OneToOne
	@JoinColumn(name="caseNum")
	private ApplicationEntity caseNum;
	
	

	public Integer getPlanSelectionId() {
		return planSelectionId;
	}

	public void setPlanSelectionId(Integer planSelectionId) {
		this.planSelectionId = planSelectionId;
	}

	public ApplicationEntity getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(ApplicationEntity caseNum) {
		this.caseNum = caseNum;
	}

	public String getSelPlan() {
		return selPlan;
	}

	public void setSelPlan(String selPlan) {
		this.selPlan = selPlan;
	}

	
	

	
	
	
	
}
