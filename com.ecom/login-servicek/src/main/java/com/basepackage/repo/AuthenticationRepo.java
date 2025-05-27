package com.basepackage.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.basepackage.model.User;




@Repository
public interface AuthenticationRepo extends JpaRepository<User, Long> {
 
	
	          User     getUserNameByUserName(String userName);
	          
	         User      getPasswordByUserName(String  userName);

			java.util.Optional<User> findByUserName(String username);
	         
	         
}
