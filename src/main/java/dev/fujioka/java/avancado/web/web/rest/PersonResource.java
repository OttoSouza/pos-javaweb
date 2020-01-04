package dev.fujioka.java.avancado.web.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.fujioka.java.avancado.web.exception.EntityNotFoundException;
import dev.fujioka.java.avancado.web.otto.model.Person;
import dev.fujioka.java.avancado.web.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonResource {

	@Autowired
	private PersonService personService;

	@GetMapping("/person")
	public List<Person> getPerson() {
		return personService.findAll();
	}

	@GetMapping("/person/{id}")
	public Person getPersonById(Long id) {
		return personService.findById(id).orElseThrow(() -> new EntityNotFoundException("Person", "id", id.toString()));
	}

	@PostMapping("/person")
	public ResponseEntity<Person> save(@Valid @RequestBody Person person) {
		personService.save(person);
		return ResponseEntity.ok(person);
	}
	
	@PutMapping("/person")
	public ResponseEntity<Person> update(@Valid @RequestBody Person person){
		personService.save(person);
		return ResponseEntity.ok().body(person);
	}

}
