package com.flockit.examen.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flockit.examen.dto.LoginDTO;
import com.flockit.examen.dto.UserDTO;
import com.flockit.examen.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("usersService")
	private UsersService usersService;
	
	@Operation(summary = "Simula un login a partir de un email y password suministrados a través de un objeto JSON LoginDTO. "
			+ "Retorna un objeto JSON UserDTO con los datos en base de datos del usuario en caso exitoso "
			+ "o el objeto JSON LoginDTO con el que se realizó el request en primera instancia en caso contrario. "
			+ "Retorna además un código de estatus HTTP 200, 403 o 500 según corresponda.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Autorizado", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDTO.class))}),
			@ApiResponse(responseCode = "403", description = "Email o password incorrectos", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginDTO.class))}),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = LoginDTO.class))})})
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> signIn(@RequestBody LoginDTO loginDTO) {
		
		ResponseEntity<?> responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(loginDTO);
		
		try {
			UserDTO userDTO = usersService.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		
			if (userDTO != null) {
				responseEntity = ResponseEntity.status(HttpStatus.OK).body(userDTO);
			}
		} catch (Exception e) {
			logger.error("Ocurrio el siguiente error al intentar loguearse con el email " + loginDTO.getEmail() + " y el password " + loginDTO.getPassword() + ": ", e);
			responseEntity = new ResponseEntity<LoginDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}
