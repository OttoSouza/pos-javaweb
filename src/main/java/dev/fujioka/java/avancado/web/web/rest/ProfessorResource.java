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

@RestController
@RequestMapping("/api")
public class ProfessorResource {
	@Autowired
	private ProfessorService professorService;

	@GetMapping("/professor")
	public List<Professor> getProfessor() {
		return professorService.findAll();
	}

	@GetMapping("/professor/{id}")
	public Professor getProfessorById(Long id) {
		return professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
	}

	@PostMapping("/professor")
	public ResponseEntity<Professor> save(@Valid @RequestBody Professor professor) {
		professorService.save(professor);
		return ResponseEntity.ok(professor);
	}

	@PutMapping("/professor")
	public ResponseEntity<Professor> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Professor professor) {
		Professor professorUpdate = professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
		 professorUpdate.setName(professor.getName());
		 professorUpdate.setSubject(professor.getSubject());
		 professorUpdate.setEntryDate(professor.getEntryDate());
		
		professorService.save(professorUpdate);
		return ResponseEntity.ok().body(professorUpdate);
	}

	@DeleteMapping("/professor/{id}")
	public ResponseEntity<Professor> delete(@PathVariable(value = "id") Long id) {
		Professor professorDelete = professorService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor", "id", id.toString()));
		professorService.delete(professorDelete);
		return ResponseEntity.ok().build();
	}

}
