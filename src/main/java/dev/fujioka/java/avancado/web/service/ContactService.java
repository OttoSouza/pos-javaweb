package dev.fujioka.java.avancado.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.fujioka.java.avancado.web.otto.model.Contact;
import dev.fujioka.java.avancado.web.repository.ContactRepository;
@Service
public class ContactService implements CrudInterface<Contact> {
	@Autowired
	private ContactRepository contactRepository;
	@Override
	public List<Contact> findAll() {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Override
	public Optional<Contact> save(Contact entity) {
		// TODO Auto-generated method stub
		return Optional.of(contactRepository.save(entity));
	}

	@Override
	public Optional<Contact> findById(long id) {
		// TODO Auto-generated method stub
		return contactRepository.findById(id);
	}

	@Override
	public void delete(Contact entity) {
		// TODO Auto-generated method stub
		contactRepository.delete(entity);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub
		contactRepository.deleteById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return contactRepository.count();
	}

}
