package dev.fujioka.java.avancado.web.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.fujioka.java.avancado.web.exception.EntityNotFoundException;
import dev.fujioka.java.avancado.web.otto.model.Professor;
import dev.fujioka.java.avancado.web.service.ProfessorService;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@WebAppConfiguration
public class ProfessorServiceTest {
	@Autowired
	private ProfessorService professorService;
	
	@Test
	public void saveTest() throws EntityNotFoundException {
		Professor professorTest = new Professor();
		professorTest.setName("Professor");
		professorTest.setSubject("Math");
		professorTest.setEntryDate(new Date("2014/04/21"));
		
		assertNotNull(professorService.save(professorTest));
	}
	
	@Test
	public void getAllTest() throws EntityNotFoundException {
		professorService.findAll();
	}
	
	@Test
	public void findByIdTest() throws EntityNotFoundException {
		Optional<Professor> professorTest = professorService.findById(3L);
		assertTrue(professorTest.isPresent());
	}
	@Test
	public void deleteByIdTest() throws EntityNotFoundException {
		professorService.deleteById(4L);
		Optional<Professor> professorTest = professorService.findById(4L);
		assertFalse(professorTest.isPresent());
	}
	@Test
	public void updateTest() throws EntityNotFoundException {
		Optional<Professor> professorTest = professorService.findById(6L);
		assertTrue(professorTest.isPresent());
		professorTest.get().setName("Professor Dumbledore");
		professorTest.get().setSubject("Magic");
		professorTest.get().setEntryDate(new Date("1980/04/21"));
		professorService.save(professorTest.get());
	}
}
