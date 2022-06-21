package com.flockit.examen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flockit.examen.dto.UserDTO;
import com.flockit.examen.model.User;
import com.flockit.examen.repository.UsersRepository;
import com.flockit.examen.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDTO findByEmailAndPassword(String email, String password) {
		
		User user = usersRepository.findByEmailAndPassword(email, password);
		UserDTO userDTO = null;
		
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setPassword(user.getPassword());
			userDTO.setEmail(user.getEmail());
		}
		
		return userDTO;
	}
}
