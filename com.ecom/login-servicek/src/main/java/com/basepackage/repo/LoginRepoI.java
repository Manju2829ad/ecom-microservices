package com.basepackage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basepackage.model.Login;

public interface LoginRepoI  extends JpaRepository<Login, Long>{

}
