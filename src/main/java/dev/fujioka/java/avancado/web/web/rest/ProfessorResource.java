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
import dev.fujioka.java.avancado.web.otto.model.Professor;
import dev.fujioka.java.avancado.web.service.ProfessorService;
/**
 * This class is the professor controller with the basics endpoints: crud;
 * 
 * 
 * @author ottomint*/
@RestController
@RequestMapping("/api")
public class ProfessorResource {
	@Autowired
	private ProfessorService professorService;

	/**
	 * This endpoint returns  all registered professor.
	 */
	@GetMapping("/professor")	
	public List<Professor> getProfessor() {
		return professorService.findAll();
	}

	/**
	 * This endpoint gets an identifier called 'id', after that returns its values.
	  @param id*/
	@GetMapping("/professor/{id}")	
	public Professor getProfessorById(Long id) {
		return professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
	}

	/**
	 * This endpoint gets all professors values and save.
	 */
	@PostMapping("/professor")	
	public ResponseEntity<Professor> save(@Valid @RequestBody Professor professor) {
		professorService.save(professor);
		return ResponseEntity.ok(professor);
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can make changes, if no error message will be displayed. 
	  @param id, professor*/
	@PutMapping("/professor/{id}")	
	public ResponseEntity<Professor> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Professor professor) {
		Professor professorUpdate = professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
		 professorUpdate.setName(professor.getName());
		 professorUpdate.setSubject(professor.getSubject());
		 professorUpdate.setEntryDate(professor.getEntryDate());
		
		professorService.save(professorUpdate);
		return ResponseEntity.ok().body(professorUpdate);
	}

	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can delete, if no error message will be displayed. 
	  @param id*/
	@DeleteMapping("/professor/{id}")
	public ResponseEntity<Professor> delete(@PathVariable(value = "id") Long id) {
		Professor professorDelete = professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
		professorService.delete(professorDelete);
		return ResponseEntity.ok().build();
	}

}
