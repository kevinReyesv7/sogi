package com.alphaomega.springboot.app.equipments.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alphaomega.springboot.app.equipments.exception.BadResourceException;
import com.alphaomega.springboot.app.equipments.exception.ResourceAlreadyExistsException;
import com.alphaomega.springboot.app.equipments.exception.ResourceNotFoundException;
import com.alphaomega.springboot.app.equipments.model.entity.Equipment;
import com.alphaomega.springboot.app.equipments.model.repository.EquipmentRepository;

@Service
public class EquipmentServiceImpl implements IEquipmentService{

	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Equipment> findAll() {
		return (List<Equipment>) equipmentRepository.findAll();
	}

	
	
	@Override
	@Transactional(readOnly = true)
	public Equipment findById(Long id) {
		return equipmentRepository.findById(id).orElse(null);
	}
	
    private boolean existsById(Long id) {

        return equipmentRepository.existsById(id);

    }
    
    public Equipment save(Equipment equipment) throws BadResourceException, ResourceAlreadyExistsException {

        if (!StringUtils.isEmpty(equipment.getName())) {

            if (equipment.getId() != null && existsById(equipment.getId())) { 

                throw new ResourceAlreadyExistsException("Equipment with id: " + equipment.getId() +

                        " already exists");

            }

            return equipmentRepository.save(equipment);

        }

        else {

            BadResourceException exc = new BadResourceException("Failed to save Equipment");

            exc.addErrorMessage("Equipment is null or empty");

            throw exc;

        }

    }
	
	@Override
	@Transactional
	public void update(Equipment equipment) 
        throws BadResourceException, ResourceNotFoundException {

    if (!StringUtils.isEmpty(equipment.getName())) {

        if (!existsById(equipment.getId())) {

            throw new ResourceNotFoundException("Equipamiento no encontrado" + equipment.getName());

        }

        equipmentRepository.save(equipment);

    }

    else {

        BadResourceException exc = new BadResourceException("Error al guardar el equipamiento");

        exc.addErrorMessage("El equipamiento no existe");

        throw exc;

    }
	}
	
	@Override
	@Transactional
    public void delete(Long id) 

            throws ResourceNotFoundException {

        Equipment equipment = findById(id);

        equipment.setIsDeleted(true);

        equipmentRepository.save(equipment);        

    }
	
	
	@Override
	@Transactional
    public void updateState(Long id) 

            throws ResourceNotFoundException {

        Equipment equipment = findById(id);
        boolean actual = equipment.getState();
        
        if (actual == true) {
        	equipment.setState(false);
        } else {
        	equipment.setState(true);
        }

        equipmentRepository.save(equipment);        

    }
	
}
