package com.alphaomega.springboot.app.equipments.model.service;

import java.util.List;

import com.alphaomega.springboot.app.equipments.exception.BadResourceException;
import com.alphaomega.springboot.app.equipments.exception.ResourceAlreadyExistsException;
import com.alphaomega.springboot.app.equipments.exception.ResourceNotFoundException;
import com.alphaomega.springboot.app.equipments.model.entity.Equipment;

public interface IEquipmentService {

	public List<Equipment> findAll();
	public Equipment findById(Long id);
	public List<Equipment> findByIsDeletedFalse();
	public Equipment save(Equipment equipment) throws BadResourceException, ResourceAlreadyExistsException;
	public void update(Equipment equipment) throws BadResourceException, ResourceNotFoundException;
	public void delete(Long id) throws ResourceNotFoundException;
	public void updateState(Long id) throws ResourceNotFoundException;
}
