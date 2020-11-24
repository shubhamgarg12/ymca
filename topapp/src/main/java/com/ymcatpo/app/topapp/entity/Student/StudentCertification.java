package com.ymcatpo.app.topapp.entity.Student;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class StudentCertification {

	@Id
	private String certificateId; 
	private String orgiDetails; 
	private String certiTitle; 
	
	private String issueDate;
	
	@JsonIgnore
	@ManyToOne(targetEntity = StudentPersonalDetails.class , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name= "StudentPersonalDetails_rollNo")
	private StudentPersonalDetails stu;
	
	
	
	
	public StudentPersonalDetails getStu() {
		return stu;
	}
	public void setStu(StudentPersonalDetails stu) {
		this.stu = stu;
	}
	
	
	public String getCertificateId() {
		return certificateId;
	}
	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}
	public String getOrgiDetails() {
		return orgiDetails;
	}
	public void setOrgiDetails(String orgiDetails) {
		this.orgiDetails = orgiDetails;
	}
	public String getCertiTitle() {
		return certiTitle;
	}
	public void setCertiTitle(String certiTitle) {
		this.certiTitle = certiTitle;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	

	public StudentCertification() {
		
	}
	public StudentCertification(String certificateId, String orgiDetails, String certiTitle, String issueDate,
			StudentPersonalDetails stu) {
		this.certificateId = certificateId;
		this.orgiDetails = orgiDetails;
		this.certiTitle = certiTitle;
		this.issueDate = issueDate;
		this.stu = stu;
	} 
	
	
	
}
