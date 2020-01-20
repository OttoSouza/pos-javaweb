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
import dev.fujioka.java.avancado.web.otto.model.Contact;
import dev.fujioka.java.avancado.web.service.ContactService;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@WebAppConfiguration
public class ContactServiceTest {
	@Autowired
	private ContactService contactService;
	
	@Test
	public void saveTest()throws EntityNotFoundException{
		Contact contactTest = new Contact();
		contactTest.setName("Otto");
		contactTest.setCellphone(998362522);
		contactTest.setEmail("otto@gmail.com");
		contactTest.setCreatedAt(new Date());
		
		assertNotNull(contactService.save(contactTest));
	}
	@Test
	public void getAllTest() throws EntityNotFoundException {
		contactService.findAll();
	}
	@Test
	public void findByIdTest() throws EntityNotFoundException {
		Optional<Contact> contactTest = contactService.findById(3L);
		assertTrue(contactTest.isPresent());
	}
	@Test
	public void deleteByIdTest() throws EntityNotFoundException {
		contactService.deleteById(3L);
		Optional<Contact> contactTest = contactService.findById(3L);
		assertFalse(contactTest.isPresent());
	}
	@Test
	public void updateTest() throws EntityNotFoundException {
		Optional<Contact> contactTest = contactService.findById(4L);
		assertTrue(contactTest.isPresent());
		contactTest.get().setName("Ronaldo");
		contactTest.get().setCellphone(998362522);
		contactTest.get().setEmail("ronaldo@gmail.com");	
		contactTest.get().setCreatedAt(new Date());
		contactService.save(contactTest.get());
	}
	
}
