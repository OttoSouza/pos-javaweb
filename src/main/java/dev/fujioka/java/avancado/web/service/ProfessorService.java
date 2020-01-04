package dev.fujioka.java.avancado.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.java.avancado.web.otto.model.Professor;
import dev.fujioka.java.avancado.web.repository.ProfessorRepository;

@Service
public class ProfessorService implements CrudInterface<Professor>{
	@Autowired
	private ProfessorRepository professorRepository;
	@Override
	public List<Professor> findAll() {
		// TODO Auto-generated method stub
		return professorRepository.findAll();
	}

	@Override
	public Optional<Professor> save(Professor entity) {
		// TODO Auto-generated method stub
		return Optional.of(professorRepository.save(entity));
	}

	@Override
	public Optional<Professor> findById(long id) {
		// TODO Auto-generated method stub
		return professorRepository.findById(id);
	}

	@Override
	public void delete(Professor entity) {
		// TODO Auto-generated method stub
		professorRepository.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		professorRepository.deleteById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return professorRepository.count();
	}

}
