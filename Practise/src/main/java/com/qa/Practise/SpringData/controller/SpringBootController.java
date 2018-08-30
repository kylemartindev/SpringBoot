package com.qa.Practise.SpringData.controller;

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

import com.qa.Practise.SpringData.exceptions.ResourceNotFoundException;
import com.qa.Practise.SpringData.model.SpringBootDataModel;
import com.qa.Practise.SpringData.repository.SpringBootRepository;

@RestController
@RequestMapping("/api")
public class SpringBootController {
	
	@Autowired
	SpringBootRepository springbootrepo;
	
	@PostMapping("/person")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel sdm) {
		return springbootrepo.save(sdm);
	}

	@GetMapping("person/{id}")
	public SpringBootDataModel getUserById(@PathVariable(value="id")Long personId) {
		return springbootrepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("SpringBootDataModel","id", personId));
	}
	
	@GetMapping("person")
	public List<SpringBootDataModel> getAllUsers() {
		return springbootrepo.findAll();
	}
	
	@PutMapping("person/{id}")
	public SpringBootDataModel updateUsers(@PathVariable (value= "id")Long personId,
			@Valid @RequestBody SpringBootDataModel personDetails) {
		SpringBootDataModel sdm = springbootrepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person", "id", personId));
		sdm.setName(personDetails.getName());
		sdm.setAddress(personDetails.getAddress());
		sdm.setAge(personDetails.getAge());
		
		SpringBootDataModel updateData = springbootrepo.save(sdm);
		return updateData;
	}
	
	@DeleteMapping("person/{id}")
	public ResponseEntity<?>deleteUser(@PathVariable(value= "id") Long personId) {
		SpringBootDataModel sdm = springbootrepo.findById(personId).orElseThrow(()-> new ResourceNotFoundException("Person","id",personId));
		springbootrepo.delete(sdm);
		return ResponseEntity.ok().build();
	}
}
