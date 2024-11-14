package com.nt.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.Bind.EligBind;
import com.nt.Entity.ApplicationEntity;
import com.nt.Entity.CoNotices;
import com.nt.Entity.DcChildren;
import com.nt.Entity.DcEducation;
import com.nt.Entity.DcIncome;
import com.nt.Entity.DcPlanSelectionEntity;
import com.nt.Entity.EDDTLS;
import com.nt.Repository.CONoticesRepo;
import com.nt.Repository.DcChildrenRepo;

import com.nt.Repository.EDDTLSRepo;
import com.nt.Repository.IESApplicationRepo;

@Service
public class EligServiceImp implements EligService {

	@Autowired
	private IESApplicationRepo applicationRepo;

	@Autowired
	private DcChildrenRepo childrenRepo;

	@Autowired
	private EDDTLSRepo eddtlsRepo;

	@Autowired
	private CONoticesRepo noticesRepo;

	@Override
	public EligBind checkelig(Integer caseNum) {

		ApplicationEntity appentity = applicationRepo.findById(caseNum).get();
		DcPlanSelectionEntity planname = appentity.getPlanSelection();

		String plan = planname.getSelPlan();

		DcEducation education = appentity.getEducationDetails();
		DcIncome income = appentity.getIncomeDetails();
		List<DcChildren> kids = appentity.getChildernDetails();

		EligBind bind = new EligBind();
		bind.setPlanName(plan);
		bind.setCaseNum(caseNum);
		if ("SNAP".equals(plan)) {
			if (income.getSalaryIncome() > 50000.00) {
				bind.setPlanStatus("DN");
				bind.setDenialReason("High Income");
				bind.setBenefitAmt(0.00);
			} else {
				bind.setPlanStatus("AP");
				bind.setBenefitAmt(35000.00);
				bind.setEligstartDate(LocalDate.now());
				bind.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		} else if ("CCAP".equals(plan)) {

			if (income.getSalaryIncome() > 50000.00) {
				bind.setPlanStatus("DN");
				bind.setDenialReason("High Income");
				bind.setBenefitAmt(0.00);
			} else {
				if (kids.isEmpty()) {
					bind.setPlanStatus("DN");
					bind.setDenialReason("Kids Is Required");
				} else {
					bind.setPlanStatus("AP");
					bind.setBenefitAmt(35000.00);
					bind.setEligstartDate(LocalDate.now());
					bind.setEligEndDate(LocalDate.now().plusMonths(6));
				}
			}
		} else if ("MEDICAID".equals(plan)) {
			if (income.getSalaryIncome() < 50000.00) {
				Double rent = income.getRentIncome();
				if (rent == null) {
					rent = 0.00;
				}
				Double property = income.getPropertyIncome();
				if (property == null) {
					property = 0.00;
				}
				Double total = rent + property;
				if (total > 100000.00) {
					bind.setPlanStatus("AP");
					bind.setBenefitAmt(35000.00);
					bind.setEligstartDate(LocalDate.now());
					bind.setEligEndDate(LocalDate.now().plusMonths(6));
				} else {
					bind.setPlanStatus("DN");
					bind.setDenialReason("High Property Income");
					bind.setBenefitAmt(0.00);
				}
			} else {
				bind.setPlanStatus("DN");
				bind.setDenialReason("High Income");
				bind.setBenefitAmt(0.00);

			}

		} else if ("MEDICARE".equals(plan)) {
			ApplicationEntity entity = applicationRepo.findById(caseNum).get();
			String dob = entity.getDob();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date = LocalDate.parse(dob, format);
			LocalDate currentDate = LocalDate.now();
			Period period = Period.between(date, currentDate);
			int age = period.getYears();
			if (age < 60) {
				bind.setPlanStatus("DN");
				bind.setDenialReason("Age Above 60 Years");
				bind.setBenefitAmt(0.00);
			} else {
				bind.setPlanStatus("AP");
				bind.setBenefitAmt(45000.00);
				bind.setEligstartDate(LocalDate.now());
				bind.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		} else if ("RIW".equals(plan)) {
			if (education.getGraduationYear().isEmpty()) {
				bind.setPlanStatus("DN");
				bind.setDenialReason("Must Have Graduated");
				bind.setBenefitAmt(0.00);
			} else {
				bind.setPlanStatus("AP");
				bind.setBenefitAmt(10000.00);
				bind.setEligstartDate(LocalDate.now());
				bind.setEligEndDate(LocalDate.now().plusMonths(6));
			}

		}

		EDDTLS entity = new EDDTLS();
		BeanUtils.copyProperties(bind, entity);
		entity.setCaseNum(appentity);
		eddtlsRepo.save(entity);

		return bind;
	}

	@Override
	public boolean generateCo(Integer caseNum) {
		ApplicationEntity applicationEntity = applicationRepo.findById(caseNum).get();
		EDDTLS eddtls = applicationEntity.getEligibleDetails();
		CoNotices notices = new CoNotices();
		notices.setCreatedDate(LocalDate.now());
		notices.setNoticeStatus("P");
		notices.setEdId(eddtls);
		notices.setCaseNum(applicationEntity);
		notices.setCoPdfUrl("N/A");
		noticesRepo.save(notices);
		return true;
	}
}
