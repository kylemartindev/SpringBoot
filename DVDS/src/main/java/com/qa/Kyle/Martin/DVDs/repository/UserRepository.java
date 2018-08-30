package com.qa.Kyle.Martin.DVDs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.Kyle.Martin.DVDRental.model.UsersDataModel;

public interface UserRepository extends JpaRepository<UsersDataModel, Long> {

}
