package com.flockit.examen.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flockit.examen.dto.CoordinatesDTO;
import com.flockit.examen.service.impl.ProvincesServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/provinces")
class ProvincesController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProvincesController.class);
	
	@Autowired
	@Qualifier("provincesService")
	private ProvincesServiceImpl provincesService;
	
	@Operation(summary = "Retorna un objeto JSON CoordinatesDTO conteniendo la latitud y la longitud de una provincia determinada por la variable \"name\". "
			+ "Retorna además un código de estatus HTTP 200, 404 o 500 según corresponda.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Coordenadas encontradas", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = CoordinatesDTO.class))}),
			@ApiResponse(responseCode = "404", description = "Coordenadas no encontradas", content = @Content),
			@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@GetMapping(path = "/{name}/coordinates", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<CoordinatesDTO> get(@PathVariable String name) {
		
		ResponseEntity<CoordinatesDTO> responseEntity = new ResponseEntity<CoordinatesDTO>(HttpStatus.NOT_FOUND);
		
		try {
			CoordinatesDTO coordinatesDTO  = provincesService.getCoordinatesByProvinceName(name);
			
			if (coordinatesDTO != null) {
				responseEntity = new ResponseEntity<CoordinatesDTO>(coordinatesDTO, HttpStatus.OK);
			}			
		} catch (Exception e) {
			logger.error("Ocurrio el siguiente error al intentar traer la latitud y la longitud de " + name + ": ", e);
			responseEntity = new ResponseEntity<CoordinatesDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}
}