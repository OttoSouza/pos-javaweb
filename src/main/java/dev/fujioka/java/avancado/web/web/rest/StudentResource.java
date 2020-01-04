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

@RestController
@RequestMapping("/api")
public class StudentResource {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public List<Student> getStudents(){
		return studentService.findAll();
	}
	
	@GetMapping("student/{id}")
	public Student getStudentById(Long id) {
		return studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
	}
	
	@PostMapping("/student")
	public ResponseEntity<Student> save(@Valid @RequestBody Student student){
		studentService.save(student);
		return ResponseEntity.ok().body(student);
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> update(@PathVariable(value = "id")Long id, @Valid @RequestBody Student student){
		Student studentUpdate = studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
		studentUpdate.setName(student.getName());
		studentUpdate.setCourse(student.getCourse());
		studentUpdate.setFirstSemester(student.getFirstSemester());
		studentUpdate.setSecondSemester(student.getSecondSemester());
		studentUpdate.setThirdSemester(student.getThirdSemester());
		studentUpdate.setAverage(student.getAverage());
		
		studentService.save(studentUpdate);
		return ResponseEntity.ok().body(studentUpdate);
	}
	@DeleteMapping("/student/{id}")
	 public ResponseEntity<Student> delete(@PathVariable(value = "id") Long id) {
		Student studentDelete = studentService.findById(id).orElseThrow(() -> new EntityNotFoundException("Student", "id", id.toString()));
		studentService.delete(studentDelete);
		return ResponseEntity.ok().build();
		
	}
	
}
