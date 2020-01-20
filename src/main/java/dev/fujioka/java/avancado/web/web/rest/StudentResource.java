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
import dev.fujioka.java.avancado.web.otto.model.Student;
import dev.fujioka.java.avancado.web.service.StudentService;
/**
 * This class is the Student controller with the basics endpoints: crud;
 * 
 * 
 * @author ottomint*/
@RestController
@RequestMapping("/api")
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	/**
	 * This endpoint returns  all registered students.
	 */
	@GetMapping("/student")
	public List<Student> getStudents(){
		return studentService.findAll();
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that returns its values.
	  @param id*/
	@GetMapping("student/{id}")
	public Student getStudentById(Long id) {
		return studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
	}
	
	/**
	 * This endpoint gets all students values and save.
	 */
	@PostMapping("/student")	
	public ResponseEntity<Student> save(@Valid @RequestBody Student student){
		
		studentService.save(student);
		return ResponseEntity.ok().body(student);
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can make changes, if no error message will be displayed. 
	  @param id, student*/
	@PutMapping("/student/{id}")	
	public ResponseEntity<Student> update(@PathVariable(value = "id")Long id, @Valid @RequestBody Student student){
		Student studentUpdate = studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
		studentUpdate.setName(student.getName());
		studentUpdate.setCourse(student.getCourse());
		studentUpdate.setFirstSemester(student.getFirstSemester());
		studentUpdate.setSecondSemester(student.getSecondSemester());
		studentUpdate.setThirdSemester(student.getThirdSemester());
				
		studentService.save(studentUpdate);
		return ResponseEntity.ok().body(studentUpdate);
	}
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can delete, if no error message will be displayed. 
	  @param id*/
	@DeleteMapping("/student/{id}")	
	 public ResponseEntity<Student> delete(@PathVariable(value = "id") Long id) {
		Student studentDelete = studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
		studentService.delete(studentDelete);
		return ResponseEntity.ok().build();
	}
	
}
