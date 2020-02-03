package dev.fujioka.java.avancado.web.otto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.fujioka.java.avancado.web.otto.model.Person;
import dev.fujioka.java.avancado.web.service.PersonService;

@Controller
@RequestMapping("/")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String showHome() {
		return "index";
	}
	
	
	@GetMapping("/personform")
	public String showForm(Person person) {
		return "add-person";
	}
	
	@PostMapping("/update/{id}")
	public String updatePerson(@PathVariable("id") long id, @Valid Person person, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			person.setId(id);
			return "update-person";
		}

		personService.save(person);
		model.addAttribute("persons", personService.findAll());
		return "list-persons";
	}

	@GetMapping("/delete/{id}")
	public String deletePerson(@PathVariable("id") long id, Model model) {
		Person person = personService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
		personService.delete(person);
		model.addAttribute("persons", personService.findAll());
		return "list-persons";
	}

	@PostMapping("/addperson")
	public String addPerson(@Valid Person person, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-person";
		}

		personService.save(person);
		model.addAttribute("persons", personService.findAll());
		return "list-persons";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		Person person = personService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Person Id:" + id));

		model.addAttribute("person", person);
		return "update-person";
	}
}
