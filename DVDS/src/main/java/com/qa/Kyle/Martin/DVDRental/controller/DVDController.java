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
import com.qa.Kyle.Martin.DVDRental.model.DVDDataModel;
import com.qa.Kyle.Martin.DVDs.repository.DVDRepository;

@RestController
@RequestMapping("/api")
public class DVDController {
	
	@Autowired
	DVDRepository dvdrepo;
	
	@PostMapping("/dvd")
	public DVDDataModel addDvd(@Valid @RequestBody DVDDataModel ddm) {
		return dvdrepo.save(ddm);
		} 
	
	
	@GetMapping("/dvd")
	public List<DVDDataModel> getAllDvds() {
		return dvdrepo.findAll();
	}
	
	@GetMapping("/dvd/{id}")
	public DVDDataModel getDVDbyId(@PathVariable(value="id")Long userID) {
		return dvdrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("DVDDataModel","id",userID));
		
		
	}
	
	@PutMapping("/dvd/{id}")
	public DVDDataModel updateDVDById(@PathVariable (value= "id") Long userID, @Valid @RequestBody DVDDataModel dvddetails) {
		DVDDataModel ddm = dvdrepo.findById(userID).orElseThrow(() -> new ResourceNotFoundException("Dvd","id",userID));
		
		ddm.setTitle(dvddetails.getTitle());
		ddm.setPrice(dvddetails.getPrice());
		
		DVDDataModel updateDvds = dvdrepo.save(ddm);
		return updateDvds; 
		
		
		
	}
	
	@DeleteMapping("/dvd/{id}")
	public ResponseEntity<?> deleteDVDById (@PathVariable (value= "id") Long userID) {
		DVDDataModel ddm = dvdrepo.findById(userID).orElseThrow(()-> new ResourceNotFoundException("Dvd","id",userID));
		dvdrepo.delete(ddm);
		return ResponseEntity.ok().build();
	}
	
}
