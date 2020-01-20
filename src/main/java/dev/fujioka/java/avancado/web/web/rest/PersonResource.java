package dev.fujioka.java.avancado.web.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.java.avancado.web.exception.EntityNotFoundException;
import dev.fujioka.java.avancado.web.otto.model.Person;
import dev.fujioka.java.avancado.web.otto.model.Professor;
import dev.fujioka.java.avancado.web.service.PersonService;
/**
 * This class is the person controller with the basics endpoints: crud;
 * 
 * 
 * @author ottomint*/
@RestController
@RequestMapping("/api")
public class PersonResource {

	@Autowired
	private PersonService personService;

	/**
	 * This endpoint returns  all registered persons.
	 */
	@GetMapping("/person")	
	public List<Person> getPerson() {
		return personService.findAll();
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that returns its values.
	  @param id*/
	@GetMapping("/person/{id}")	
	public Person getPersonById(Long id) {
		return personService.findById(id).orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
	}
	
	/**
	 * This endpoint gets all persons values and save.
	 */
	@PostMapping("/person")	
	public ResponseEntity<Person> save(@Valid @RequestBody Person person) {
		personService.save(person);
		return ResponseEntity.ok(person);
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can make changes, if no error message will be displayed. 
	  @param id, person*/	
	@PutMapping("/person/{id}")
	public ResponseEntity<Person> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Person person) {
		Person personUpdate = personService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
		personUpdate.setFirstName(person.getFirstName());
		personUpdate.setLastName(person.getLastName());
		personUpdate.setCreatedAt(person.getCreatedAt());		
		personService.save(personUpdate);
		return ResponseEntity.ok().body(personUpdate);
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can delete, if no error message will be displayed. 
	  @param id*/
	@DeleteMapping("/person/{id}")	
	public ResponseEntity<Person> delete(@PathVariable(value = "id") Long id) {
		Person personDelete = personService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
		personService.delete(personDelete);
		return ResponseEntity.ok().build();
	}

}
