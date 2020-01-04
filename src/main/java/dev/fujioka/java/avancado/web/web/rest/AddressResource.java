package dev.fujioka.java.avancado.web.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
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
import dev.fujioka.java.avancado.web.otto.model.Address;
import dev.fujioka.java.avancado.web.service.AddressService;

@RestController
@RequestMapping("/api")
public class AddressResource {
	@Autowired
	private AddressService addressService;

	@GetMapping("/address")
	public List<Address> getAddress() {
		return addressService.findAll();
	}

	@GetMapping("/address/{id}")
	public Address getAddressById(Long id) {
		return addressService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address", "id", id.toString()));
	}

	@PostMapping("/address")
	public ResponseEntity<Address> save(@Valid @RequestBody Address address) {
		addressService.save(address);
		return ResponseEntity.ok().body(address);
	}
	
	@PutMapping("/address/{id}")
	public ResponseEntity<Address> update(@PathVariable(value="id") Long id , @Valid @RequestBody Address address){
		Address addressUpdate = addressService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address", "id", id.toString()));
		
		addressUpdate.setStreat(address.getStreat());
		addressUpdate.setNeighborhood(address.getNeighborhood());
		addressUpdate.setHouseNumber(address.getHouseNumber());
		addressUpdate.setCode(address.getCode());
		
		addressService.save(addressUpdate);
		return ResponseEntity.ok().body(addressUpdate);
	}
	
	@DeleteMapping("address/{id}")
	public ResponseEntity<Address> delete(@PathVariable(value = "id") Long id){
		Address addressDelete = addressService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address", "id", id.toString()));
		addressService.delete(addressDelete);
		return ResponseEntity.ok().build();
	}
}
