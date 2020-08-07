package com.alphaomega.springboot.app.equipments.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alphaomega.springboot.app.equipments.exception.BadResourceException;
import com.alphaomega.springboot.app.equipments.exception.ResourceAlreadyExistsException;
import com.alphaomega.springboot.app.equipments.exception.ResourceNotFoundException;
import com.alphaomega.springboot.app.equipments.model.entity.Equipment;
import com.alphaomega.springboot.app.equipments.model.service.IEquipmentService;

@CrossOrigin
@RestController
public class EquipmentController {

	@Autowired
	private IEquipmentService equipmentService;
	
	@GetMapping("/equipamiento/todos")
	public List<Equipment> listartodos(){
		return equipmentService.findAll();
	}
	
	@GetMapping("/equipamiento/{id}")
	public Equipment ver(@PathVariable Long id) {
		return equipmentService.findById(id);
	}
	
	@GetMapping("/equipamiento")
    	public List<Equipment> listar(){
        	return equipmentService.findByIsDeletedFalse();
    	}
	
	@PostMapping(value = "/equipamiento")
	public ResponseEntity<Equipment> addEquipment(@Validated @RequestBody Equipment equipment) 

            throws URISyntaxException {

        try {

            Equipment newEquipment = equipmentService.save(equipment);

            return ResponseEntity.created(new URI("/api/equipments/" + newEquipment.getId()))

                    .body(equipment);

        } catch (ResourceAlreadyExistsException ex) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        } catch (BadResourceException ex) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }

    }
	
	
	@PutMapping("/equipamiento/{id}")
	public ResponseEntity<Object> update(@Validated @RequestBody Equipment equipment, @PathVariable long id) {
        try {

            equipment.setId(id);

            equipmentService.update(equipment);

            return ResponseEntity.ok().build();

        } catch (ResourceNotFoundException ex) {


            return ResponseEntity.notFound().build();

        } catch (BadResourceException ex) {



            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
	}
	
	
	@PatchMapping("/equipamiento/{id}")
	public ResponseEntity<Object> updateState(@PathVariable long id) {
        try {
        	equipmentService.updateState(id);
            return ResponseEntity.ok().build();

        } catch (ResourceNotFoundException ex) {


            return ResponseEntity.notFound().build();

        }
	}
	
	
	@DeleteMapping("/equipamiento/{id}")
    	public ResponseEntity<Void> deleteEquipmentById(@PathVariable long id) {
        try {
            	equipmentService.delete(id);
            return ResponseEntity.ok().build();

        } catch (ResourceNotFoundException ex) {


            return ResponseEntity.notFound().build();

        }
    	}
}
