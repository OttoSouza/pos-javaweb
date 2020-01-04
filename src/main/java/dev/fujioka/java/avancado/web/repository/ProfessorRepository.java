package dev.fujioka.java.avancado.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.java.avancado.web.otto.model.Professor;

public interface ProfessorRepository  extends JpaRepository<Professor, Long>{

}
