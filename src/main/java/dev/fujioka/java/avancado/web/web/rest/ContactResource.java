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
import dev.fujioka.java.avancado.web.otto.model.Contact;

import dev.fujioka.java.avancado.web.service.ContactService;
/**
 * This class is the contact controller with the basics endpoints: crud;
 * 
 * 
 * @author ottomint*/
@RestController
@RequestMapping("/api")
public class ContactResource {
	@Autowired
	private ContactService contactService;
	
	/**
	 * This endpoint returns  all registered contacts.
	 */
	@GetMapping("/contact")
	public List<Contact> getContact() {
		return contactService.findAll();
	}

	/**
	 * This endpoint gets an identifier called 'id', after that returns its values.
	 * 
	 * @param id
	 */
	@GetMapping("/contact/{id}")	
	public Contact getContactById(@PathVariable(value = "id") Long id) {
		return contactService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Contact", "id", id.toString()));
	}

	/**
	 * This endpoint gets all contacts values and save.
	 */
	@PostMapping("/contact")	
	public ResponseEntity<Contact> save(@Valid @RequestBody Contact contact) {
		contactService.save(contact);
		return ResponseEntity.ok().body(contact);
	}

	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can make changes, if no error message will be displayed. 
	  @param id, contact*/
	@PutMapping("/contact/{id}")
	public ResponseEntity<Contact> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Contact contact) {
		Contact contactUpdate = contactService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Contact", "id", id.toString()));
		contactUpdate.setName(contact.getName());
		contactUpdate.setEmail(contact.getEmail());
		contactUpdate.setCellphone(contact.getCellphone());
		contactUpdate.setCreatedAt(contact.getCreatedAt());
		contactService.save(contactUpdate);
		return ResponseEntity.ok().body(contactUpdate);
	}
	
	/**
	 * This endpoint gets an identifier called 'id', after that it will be checked. If it exists can delete, if no error message will be displayed. 
	  @param id*/
	@DeleteMapping("/contact/{id}")
	public ResponseEntity<Contact> delete(@PathVariable(value = "id") Long id){
		Contact contactDelete = contactService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Contact", "id", id.toString()));
		contactService.delete(contactDelete);
		return ResponseEntity.ok().build();
	}
}
