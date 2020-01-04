package dev.fujioka.java.avancado.web.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.java.avancado.web.otto.model.Address;
import dev.fujioka.java.avancado.web.repository.AddressRepository;
@Service
public class AddressService implements CrudInterface<Address> {
	@Autowired
	private AddressRepository addressRepository;
	@Override
	public List<Address> findAll() {
		// TODO Auto-generated method stub
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> save(Address entity) {
		// TODO Auto-generated method stub
		return Optional.of(addressRepository.save(entity));
	}

	@Override
	public Optional<Address> findById(long id) {
		// TODO Auto-generated method stub
		return addressRepository.findById(id);
	}

	@Override
	public void delete(Address entity) {
		// TODO Auto-generated method stub
		addressRepository.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		addressRepository.deleteById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return addressRepository.count();
	}

}
