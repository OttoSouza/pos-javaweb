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
import dev.fujioka.java.avancado.web.otto.model.Person;
import dev.fujioka.java.avancado.web.service.PersonService;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@WebAppConfiguration
public class PersonServiceTest {
	@Autowired
	private PersonService personServiceTest;
	@Test
	public void saveTest()throws EntityNotFoundException {
		Person personTest = new Person();
		personTest.setFirstName("Otto");
		personTest.setLastName("Neto");
		personTest.setCreatedAt(new Date());
		assertNotNull(personServiceTest.save(personTest));
	}
	@Test
	public void getAllTest() throws EntityNotFoundException {
		personServiceTest.findAll();
	}
	@Test
	public void findByIdTest() throws EntityNotFoundException {
		Optional<Person> personTest = personServiceTest.findById(1L);
		assertTrue(personTest.isPresent());
	}
	@Test
	public void deleteByIdTest() throws EntityNotFoundException {
		personServiceTest.deleteById(1L);
		Optional<Person> personTest = personServiceTest.findById(1L);
		assertFalse(personTest.isPresent());
	}
	@Test
	public void updateTest() throws EntityNotFoundException {
		Optional<Person> personTest = personServiceTest.findById(2L);
		assertTrue(personTest.isPresent());
		personTest.get().setFirstName("Maria");
		personTest.get().setLastName("Silva");
		personTest.get().setCreatedAt(new Date());
		assertNotNull(personServiceTest.save(personTest.get()));
	}
}
