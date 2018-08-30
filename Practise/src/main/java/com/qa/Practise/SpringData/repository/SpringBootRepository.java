package com.qa.Practise.SpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.Practise.SpringData.model.SpringBootDataModel;

@Repository
public interface SpringBootRepository extends JpaRepository<SpringBootDataModel, Long> {

}
