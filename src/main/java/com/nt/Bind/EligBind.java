package com.nt.Bind;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class EligBind {

	private Integer caseNum;
	private String planName;
	private String planStatus;
	private LocalDate eligstartDate;
	private LocalDate eligEndDate;
	private Double benefitAmt;
	private String denialReason;
	
	
	public Integer getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	
	
	
	
	public LocalDate getEligstartDate() {
		return eligstartDate;
	}
	public void setEligstartDate(LocalDate eligstartDate) {
		this.eligstartDate = eligstartDate;
	}
	public LocalDate getEligEndDate() {
		return eligEndDate;
	}
	public void setEligEndDate(LocalDate eligEndDate) {
		this.eligEndDate = eligEndDate;
	}
	public Double getBenefitAmt() {
		return benefitAmt;
	}
	public void setBenefitAmt(Double benefitAmt) {
		this.benefitAmt = benefitAmt;
	}
	public String getDenialReason() {
		return denialReason;
	}
	public void setDenialReason(String denialReason) {
		this.denialReason = denialReason;
	}
	
	
	
	
}
