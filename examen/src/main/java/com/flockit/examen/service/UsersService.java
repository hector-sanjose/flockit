package com.flockit.examen.service;

import com.flockit.examen.dto.UserDTO;

public interface UsersService {

	public UserDTO findByEmailAndPassword(String email, String password);	
}
