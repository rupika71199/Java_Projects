package com.dnapass.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnapass.training.entity.Equipment;
import com.dnapass.training.entity.EquipmentDetails;
import com.dnapass.training.entity.EquipmentUser;
import com.dnapass.training.repository.EquipmentRepository;
import com.dnapass.training.repository.EquipmentUserRepository;

@Service
public class EquipmentService{

	@Autowired
	EquipmentRepository equipmentrepo;
	@Autowired
	EquipmentUserRepository equipmentuserrepo;
	
	//To search all equipment based on their city 
	public List<Equipment> searchEquipment(String city) {
		List<Equipment> searchResultsByCity = equipmentrepo.findByCity(city);
		return searchResultsByCity;
	}

	//To fetch all equipmnet for the farmers to book it 
	public List<Equipment> getAllEquipment() {
		boolean bool = false;
		if(bool) {
			List<Equipment> equipmentList = equipmentrepo.findAll();
			return equipmentList;
		}
		else {
			List<Equipment> equipmentListByCount = equipmentrepo.findEquipmentByCount();
			return equipmentListByCount;
		}
	}

	//To add new equipment 
	public Equipment addEquipment(Equipment e) {
		Equipment demoEquipment = new Equipment(e.getId(),e.getName(), e.getCount(),e.getRentPerDay(),
				e.getState(),e.getCity(),e.getVillage(),e.getPinCode(),e.getContactPerson(),e.getMobileNumber(),e.getStatus(), e.getUser());
		
		equipmentrepo.save(demoEquipment);
		return demoEquipment;
		
	}

    //To delete particular equipment based on equipment id 
	public void deleteEquipmentDetail(Integer equipId) {
		equipmentrepo.deleteById(equipId);
	}

   //To get an equipment based on id 
	public Equipment getEquipmentById(Integer id) {
		Equipment equipmentDetail = equipmentrepo.findById(id).get();
		return equipmentDetail;
	}

    //To fetch all equipment for that particular user 
	public List<Equipment> getAllEquipmentById(Integer id) {
		List<Equipment> equipmentListById = equipmentrepo.findAllEquipment(id);
		return equipmentListById;
	}
	
	//To fetch all hired equipment for particular user 
	public List<EquipmentDetails> getHiredEquipment(Integer uid){
		List<EquipmentDetails> equipmentDetailsList = equipmentrepo.findHiredEquipment(uid);
		return equipmentDetailsList;
	}
	
	//To add new equipmentuser which contains both equipment details and user details 
	public EquipmentUser addEquipmentUser(EquipmentUser euser) {
		EquipmentUser equipUser = new EquipmentUser(euser.getId(),euser.getUser(),euser.getEquipment(),euser.getQuantity());
		equipmentuserrepo.save(equipUser);
		return equipUser;
	}
	
	//To delete equipment based on bookingid while returning the equipment
	public void deleteEquipmentUser(Integer bookingId) {
		equipmentuserrepo.deleteById(bookingId);
	}
}
