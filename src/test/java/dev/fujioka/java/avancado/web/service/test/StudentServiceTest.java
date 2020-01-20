package dev.fujioka.java.avancado.web.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.fujioka.java.avancado.web.exception.EntityNotFoundException;
import dev.fujioka.java.avancado.web.otto.model.Student;
import dev.fujioka.java.avancado.web.repository.StudentRepository;
import dev.fujioka.java.avancado.web.service.StudentService;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@WebAppConfiguration
public class StudentServiceTest {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveTeste() throws EntityNotFoundException {
		Student studentTest = new Student();
		studentTest.setName("WANDERSON");
		studentTest.setCourse("FELIPE");
		studentTest.setFirstSemester(8F);
		studentTest.setSecondSemester(8F);
		studentTest.setThirdSemester(8F);
		studentTest.setAverage((studentTest.getFirstSemester() + studentTest.getSecondSemester() + studentTest.getThirdSemester()) / 3);
	
		assertNotNull(studentService.save(studentTest));
	}

	@Test
	public void getStudentsTest() throws EntityNotFoundException {
		studentService.findAll();
	}

	@Test
	public void findByIdTeste() throws EntityNotFoundException {
		Optional<Student> student = studentService.findById(37L);
		assertTrue(student.isPresent()); 
	}

	@Test
	public void deleteByIdTest() throws EntityNotFoundException {
		studentService.deleteById(37L);
		Optional<Student> student = studentRepository.findById(37L);
		assertFalse(student.isPresent()); 
	}
	@Test
	public void updateTest() throws EntityNotFoundException {
		Optional<Student> studentTest = studentService.findById(36L);
		assertTrue(studentTest.isPresent()); 
		studentTest.get().setName("Naruto");
		studentTest.get().setCourse("Hokage");
		studentTest.get().setFirstSemester(7F);
		studentTest.get().setSecondSemester(7F);
		studentTest.get().setThirdSemester(7F);
		studentTest.get().setAverage((studentTest.get().getFirstSemester() + studentTest.get().getSecondSemester() + studentTest.get().getThirdSemester()) / 3);
		studentService.save(studentTest.get());
	}
	
}
