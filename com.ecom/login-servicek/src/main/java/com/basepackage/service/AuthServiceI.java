package com.basepackage.service;

import java.util.Optional;

//@Service
public interface AuthServiceI {

            Optional<String> authenticate(String username,String password);

}
