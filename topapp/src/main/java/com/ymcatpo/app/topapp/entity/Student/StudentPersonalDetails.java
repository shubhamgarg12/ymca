package com.ymcatpo.app.topapp.entity.Student;





import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ymcatpo.app.topapp.entity.Tpo.Company;

@Entity
@Table(name = "StudentPeronal")
public class StudentPersonalDetails {
	@Id
	private String rollNo;
	private String fullName;
	private String fatherName;
	private String motherName;
	private String dob;
	private int gender; // 0 Male 1 Female
	private String email; 
	private String contactNo;
	private String address;
	private int zipCode;
	private String state;
	private String city;
	
	@JsonIgnore
	@ManyToMany( cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	@JoinTable(
			name= "company_student",
			joinColumns = @JoinColumn(name = "rollNo"),
			inverseJoinColumns =  @JoinColumn(name = "comapany_id")
			)
	private Set<Company> company_id;
	
	 
	public Set<Company> getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Set<Company> company_id) {
		this.company_id = company_id;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public StudentPersonalDetails() {
	}
	public StudentPersonalDetails(String rollNo, String fullName, String fatherName, String motherName, String dob,
			int gender, String email, String contactNo, String address, int zipCode, String state, String city) {
		super();
		this.rollNo = rollNo;
		this.fullName = fullName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.contactNo = contactNo;
		this.address = address;
		this.zipCode = zipCode;
		this.state = state;
		this.city = city;
	}
	
	
	
	
	
		
}
