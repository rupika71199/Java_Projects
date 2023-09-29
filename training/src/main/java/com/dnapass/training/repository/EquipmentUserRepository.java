package com.dnapass.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnapass.training.entity.EquipmentUser;

@Repository
public interface EquipmentUserRepository extends JpaRepository<EquipmentUser, Integer>{

}
