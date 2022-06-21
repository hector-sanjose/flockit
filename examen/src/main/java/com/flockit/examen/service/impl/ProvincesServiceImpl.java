package com.flockit.examen.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.flockit.examen.dto.CoordinatesDTO;
import com.flockit.examen.service.ProvincesService;

@Service("provincesService")
public class ProvincesServiceImpl implements ProvincesService {
	
	@Override
	public CoordinatesDTO getCoordinatesByProvinceName(String name) throws Exception {

		CoordinatesDTO coordinatesDTO = null;
		
		try {

			String provEndpoint = "https://apis.datos.gob.ar/georef/api/provincias?nombre=" + name;

			RestTemplate restTemplate = new RestTemplate();
			ObjectNode node = restTemplate.getForObject(provEndpoint, ObjectNode.class);
			
			if (node.get("cantidad").asInt() != 0) {
				coordinatesDTO = new CoordinatesDTO();
				coordinatesDTO.setLatitud(node.get("provincias").get(0).get("centroide").get("lat").toString());
				coordinatesDTO.setLongitud(node.get("provincias").get(0).get("centroide").get("lon").toString());
			}
		} catch (Exception e) {
			throw e;
		}
		
		return coordinatesDTO;
	}
}
