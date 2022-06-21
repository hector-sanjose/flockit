package com.flockit.examen.service;

import com.flockit.examen.dto.CoordinatesDTO;

public interface ProvincesService {
	
	public CoordinatesDTO getCoordinatesByProvinceName(String name) throws Exception;
}
