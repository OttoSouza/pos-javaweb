package dev.fujioka.java.avancado.web.otto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

/** This's a class named Student with some hypothetical attributes  @author ottomint*/
@Entity
@Table(name = "Student")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String course;
	
	
	@Nullable
	private Float firstSemester;
	
	@Nullable
	private Float secondSemester;
	
	@Nullable
	private Float thirdSemester;
	
	@Nullable
	private Float average ;

	
	public Student() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCourse() {
		return course;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public Float getFirstSemester() {
		return firstSemester;
	}
	
	public void setFirstSemester(Float firstSemester) {
		this.firstSemester = firstSemester;
	}
	
	public Float getSecondSemester() {
		return secondSemester;
	}
	
	public void setSecondSemester(Float secondSemester) {
		this.secondSemester = secondSemester;
	}

	public Float getThirdSemester() {
		return thirdSemester;
	}
	
	public void setThirdSemester(Float thirdSemester) {
		this.thirdSemester = thirdSemester;
	}
	
	public Float getAverage() {
		return average;
	}
	
	public void setAverage(Float average) {
		this.average = average;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
