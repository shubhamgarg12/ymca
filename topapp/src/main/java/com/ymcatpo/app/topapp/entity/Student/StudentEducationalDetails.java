package com.ymcatpo.app.topapp.entity.Student;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StudentEducationalDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private float sscMarks;
	private int sscPassYear;
	private float hsscMarks;
	private String hsscStream;
	private int hsscPasYear;
	private String gradCourse;
	private float gradsCgpa; 
	private int gradPassYear;
	private String pgCourse;
	private float pgCgpa;
	private int pgPassYear;
	private int year;
	private String gapReason;
	
	@JsonIgnore
	@OneToOne( targetEntity = StudentPersonalDetails.class ,cascade = CascadeType.ALL)
	@JoinColumn(name="StudentPersonalDetails_rollNo",referencedColumnName = "rollNo")
	private StudentPersonalDetails stu;
	
	
	public StudentPersonalDetails getStu() {
		return stu;
	}

	public void setStu(StudentPersonalDetails stu) {
		this.stu = stu;
	}

	public StudentEducationalDetails() {
		}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public StudentEducationalDetails(long id, float sscMarks, int sscPassYear, float hsscMarks, String hsscStream,
			int hsscPasYear, String gradCourse, float gradsCgpa, int gradPassYear, String pgCourse, float pgCgpa,
			int pgPassYear, int year, String gapReason) {
		super();
		this.id = id;
		this.sscMarks = sscMarks;
		this.sscPassYear = sscPassYear;
		this.hsscMarks = hsscMarks;
		this.hsscStream = hsscStream;
		this.hsscPasYear = hsscPasYear;
		this.gradCourse = gradCourse;
		this.gradsCgpa = gradsCgpa;
		this.gradPassYear = gradPassYear;
		this.pgCourse = pgCourse;
		this.pgCgpa = pgCgpa;
		this.pgPassYear = pgPassYear;
		this.year = year;
		this.gapReason = gapReason;

	}
	public float getSscMarks() {
		return sscMarks;
	}
	public void setSscMarks(float sscMarks) {
		this.sscMarks = sscMarks;
	}
	public int getSscPassYear() {
		return sscPassYear;
	}
	public void setSscPassYear(int sscPassYear) {
		this.sscPassYear = sscPassYear;
	}
	public float getHsscMarks() {
		return hsscMarks;
	}
	public void setHsscMarks(float hsscMarks) {
		this.hsscMarks = hsscMarks;
	}
	public String getHsscStream() {
		return hsscStream;
	}
	public void setHsscStream(String hsscStream) {
		this.hsscStream = hsscStream;
	}
	public int getHsscPasYear() {
		return hsscPasYear;
	}
	public void setHsscPasYear(int hsscPasYear) {
		this.hsscPasYear = hsscPasYear;
	}
	public String getGradCourse() {
		return gradCourse;
	}
	public void setGradCourse(String gradCourse) {
		this.gradCourse = gradCourse;
	}
	public float getGradsCgpa() {
		return gradsCgpa;
	}
	public void setGradsCgpa(float gradsCgpa) {
		this.gradsCgpa = gradsCgpa;
	}
	public int getGradPassYear() {
		return gradPassYear;
	}
	public void setGradPassYear(int gradPassYear) {
		this.gradPassYear = gradPassYear;
	}
	public String getPgCourse() {
		return pgCourse;
	}
	public void setPgCourse(String pgCourse) {
		this.pgCourse = pgCourse;
	}
	public float getPgCgpa() {
		return pgCgpa;
	}
	public void setPgCgpa(float pgCgpa) {
		this.pgCgpa = pgCgpa;
	}
	public int getPgPassYear() {
		return pgPassYear;
	}
	public void setPgPassYear(int pgPassYear) {
		this.pgPassYear = pgPassYear;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getGapReason() {
		return gapReason;
	}
	@Override
	public String toString() {
		return "StudentEducationalDetails [id=" + id + ", sscMarks=" + sscMarks + ", sscPassYear=" + sscPassYear
				+ ", hsscMarks=" + hsscMarks + ", hsscStream=" + hsscStream + ", hsscPasYear=" + hsscPasYear
				+ ", gradCourse=" + gradCourse + ", gradsCgpa=" + gradsCgpa + ", gradPassYear=" + gradPassYear
				+ ", pgCourse=" + pgCourse + ", pgCgpa=" + pgCgpa + ", pgPassYear=" + pgPassYear + ", year=" + year
				+ ", gapReason=" + gapReason + "]";
	}
	public void setGapReason(String gapReason) {
		this.gapReason = gapReason;
	}
	

	
	
	
	
}
