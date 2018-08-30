package com.qa.Kyle.Martin.DVDRental.controller;

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

import com.qa.Kyle.Martin.DVDRental.exception.ResourceNotFoundException;
import com.qa.Kyle.Martin.DVDRental.model.UsersDataModel;
import com.qa.Kyle.Martin.DVDs.repository.UserRepository;

@RestController
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	UserRepository userrepo;
	
	@PostMapping("/user")
	public UsersDataModel addUser(@Valid @RequestBody UsersDataModel udm) {
		return userrepo.save(udm);
		} 
	
	
	@GetMapping("/user")
	public List<UsersDataModel> getAllUsers() {
		return userrepo.findAll();
	}
	
	@GetMapping("/user/{id}")
	public UsersDataModel getUserbyId(@PathVariable(value="id")Long userID) {
		return userrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("UsersDataModel","id",userID));
		
		
	}
	
	@PutMapping("/user/{id}")
	public UsersDataModel updateUserById(@PathVariable (value= "id") Long userID, @Valid @RequestBody UsersDataModel userdetails) {
		UsersDataModel udm = userrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User","id",userID));
		
		udm.setName(userdetails.getName());
		udm.setAddress(userdetails.getAddress());
		
		UsersDataModel updateUsers = userrepo.save(udm);
		return updateUsers; 
		
		
		
	}
	
	@DeleteMapping("/dvd/{id}")
	public ResponseEntity<?> deleteUserById (@PathVariable (value= "id") Long userID) {
		UsersDataModel udm = userrepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User","id",userID));
		userrepo.delete(udm);
		return ResponseEntity.ok().build();
	}
	
}


