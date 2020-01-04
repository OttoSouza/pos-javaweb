package dev.fujioka.java.avancado.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.fujioka.java.avancado.web.otto.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
