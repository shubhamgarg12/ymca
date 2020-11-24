package com.ymcatpo.app.topapp.entity.Tpo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymcatpo.app.topapp.entity.Student.StudentPersonalDetails;

@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long comapnyId;
	private String companyName;
	private String jobProfile;
	private String jobPackage;
	private String jobDescription;
	private int noOfVacancies;
	private String eligibilityCriteria; 
	private boolean backlog_Allow;
	private int noOfBacklog; 
	private int batchYear;
	
	private String lastDateApply;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "company_id")
	private List<StudentPersonalDetails> student;
	
	public Company() {
		
	}
	
	
	
	public Company(long comapnyId, String companyName, String jobProfile, String jobPackage, String jobDescription,
			int noOfVacancies, String eligibilityCriteria, boolean backlog_Allow, int noOfBacklog, int batchYear,
			String lastDateApply, List<StudentPersonalDetails> student) {
		
		this.comapnyId = comapnyId;
		this.companyName = companyName;
		this.jobProfile = jobProfile;
		this.jobPackage = jobPackage;
		this.jobDescription = jobDescription;
		this.noOfVacancies = noOfVacancies;
		this.eligibilityCriteria = eligibilityCriteria;
		this.backlog_Allow = backlog_Allow;
		this.noOfBacklog = noOfBacklog;
		this.batchYear = batchYear;
		this.lastDateApply = lastDateApply;
		this.student = student;
	}



	public String getLastDateApply() {
		return lastDateApply;
	}



	public void setLastDateApply(String lastDateApply) {
		this.lastDateApply = lastDateApply;
	}



	public long getComapnyId() {
		return comapnyId;
	}
	public void setComapnyId(long comapnyId) {
		this.comapnyId = comapnyId;
	}
	public List<StudentPersonalDetails> getStudent() {
		return student;
	}
	public void setStudent(List<StudentPersonalDetails> student) {
		this.student = student;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getJobProfile() {
		return jobProfile;
	}
	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}
	public String getJobPackage() {
		return jobPackage;
	}
	public void setJobPackage(String jobPackage) {
		this.jobPackage = jobPackage;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public int getNoOfVacancies() {
		return noOfVacancies;
	}
	public void setNoOfVacancies(int noOfVacancies) {
		this.noOfVacancies = noOfVacancies;
	}
	public String getEligibilityCriteria() {
		return eligibilityCriteria;
	}
	public void setEligibilityCriteria(String eligibilityCriteria) {
		this.eligibilityCriteria = eligibilityCriteria;
	}
	public boolean isBacklog_Allow() {
		return backlog_Allow;
	}
	public void setBacklog_Allow(boolean backlog_Allow) {
		this.backlog_Allow = backlog_Allow;
	}
	public int getNoOfBacklog() {
		return noOfBacklog;
	}
	public void setNoOfBacklog(int noOfBacklog) {
		this.noOfBacklog = noOfBacklog;
	}
	public int getBatchYear() {
		return batchYear;
	}
	public void setBatchYear(int batchYear) {
		this.batchYear = batchYear;
	}
	

	
}
