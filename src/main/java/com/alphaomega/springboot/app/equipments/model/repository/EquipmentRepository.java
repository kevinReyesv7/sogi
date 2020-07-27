package com.alphaomega.springboot.app.equipments.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.alphaomega.springboot.app.equipments.model.entity.Equipment;

public interface EquipmentRepository extends CrudRepository<Equipment, Long>{

}
