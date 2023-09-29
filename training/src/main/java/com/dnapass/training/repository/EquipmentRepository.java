package com.dnapass.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dnapass.training.entity.Equipment;
import com.dnapass.training.entity.EquipmentDetails;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
	// Query to fetch all equipment based on the city and count
	List<Equipment> findByCity(@Param("city") String code);

	// Query to fetch all equipment based on particular userid
	@Query(value = "select e.id, e.city, e.contact_person, e.count, e.name, e.rent_per_day,e.mobile_number,e.status,e.state,e.pin_code,e.user_id,e.village from equipment e join user200 u on e.user_id = u.id where e.user_id=?1", nativeQuery = true)
	List<Equipment> findAllEquipment(Integer id);

	// Query to select all equipment if their count is more than 0
	@Query(value = "select * from equipment where count>0", nativeQuery = true)
	List<Equipment> findEquipmentByCount();

	// Query to display hired equipment details based on particular user id
	@Query("select NEW com.dnapass.training.entity.EquipmentDetails(e.id, e.name, e.contactPerson,e.mobileNumber,e.count) from Equipment e, User u where e.user.id=u.id and u.id=?1")
	List<EquipmentDetails> findHiredEquipment(@Param("uid") Integer id);

}
