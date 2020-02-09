package dev.fujioka.java.avancado.web.service.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.fujioka.java.avancado.web.exception.EntityNotFoundException;
import dev.fujioka.java.avancado.web.otto.model.Address;
import dev.fujioka.java.avancado.web.service.AddressService;

@SpringBootTest
@RunWith(JUnitPlatform.class)
@WebAppConfiguration
public class AddressServiceTest {
	@Autowired
	private AddressService addressServiceTest;
	@Test
	public void saveTest()throws EntityNotFoundException {
		Address addressTest = new Address();
		addressTest.setStreat("Street Lisboa");
		addressTest.setNeighborhood("Europa");
		addressTest.setHouseNumber(400);
		addressTest.setCode("58974014");
		assertNotNull(addressServiceTest.save(addressTest));
	}
	
	
	@Test
	public void getAllTest() throws EntityNotFoundException {
		addressServiceTest.findAll();
	}
	@Test
	public void findByIdTest() throws EntityNotFoundException {
		Optional<Address> addressTest = addressServiceTest.findById(3L);
		assertTrue(addressTest.isPresent());
	}
	@Test
	public void deleteByIdTest() throws EntityNotFoundException {
		addressServiceTest.deleteById(3l);
		
		Optional<Address> addressTest = addressServiceTest.findById(3L);
		assertFalse(addressTest.isPresent());
	}
	
	@Test
	public void updateTest()throws EntityNotFoundException {
		Optional<Address> addressTest = addressServiceTest.findById(3L);
		assertTrue(addressTest.isPresent());
		
		addressTest.get().setStreat("Dublinnnnn");
		addressTest.get().setNeighborhood("Europaaaa");
		addressTest.get().setHouseNumber(10001);
		addressTest.get().setCode("9999999");
		
		addressServiceTest.save(addressTest.get());
	}
}
