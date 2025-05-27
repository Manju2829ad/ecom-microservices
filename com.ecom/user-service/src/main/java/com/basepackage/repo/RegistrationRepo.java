package com.basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basepackage.model.User;

@Repository
public interface RegistrationRepo extends JpaRepository<User, Long> {

	
	
}
