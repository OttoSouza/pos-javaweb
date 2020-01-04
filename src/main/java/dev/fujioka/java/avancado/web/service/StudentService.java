package dev.fujioka.java.avancado.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.java.avancado.web.otto.model.Student;
import dev.fujioka.java.avancado.web.repository.StudentRepository;
@Service
public class StudentService implements CrudInterface<Student> {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {

		return studentRepository.findAll();

	}

	@Override
	public Optional<Student> save(Student entity) {
		return Optional.of(studentRepository.save(entity));
	}

	@Override
	public Optional<Student> findById(long id) {
		return studentRepository.findById(id);
	}

	@Override
	public void delete(Student entity) {
		studentRepository.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public long count() {
		return studentRepository.count();
	}
}
