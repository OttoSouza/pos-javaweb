package dev.fujioka.java.avancado.web.otto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotNull
	private int firstSemester;
	@NotNull
	private int secondSemester;
	@NotNull
	private int thirdSemester;
	@NotNull
	private int average = studentAvarage();

	public Long getId() {
		return id;
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

	public int getFirstSemester() {
		return firstSemester;
	}

	public void setFirstSemester(int firstSemester) {
		this.firstSemester = firstSemester;
	}

	public int getSecondSemester() {
		return secondSemester;
	}

	public void setSecondSemester(int secondSemester) {
		this.secondSemester = secondSemester;
	}

	public int getThirdSemester() {
		return thirdSemester;
	}

	public void setThirdSemester(int thirdSemester) {
		this.thirdSemester = thirdSemester;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int studentAvarage() {
		int avarageStudent = (firstSemester + secondSemester + thirdSemester) / 3;
		return avarageStudent;
	}

}
