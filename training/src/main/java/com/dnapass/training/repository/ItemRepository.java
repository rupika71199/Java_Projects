package com.dnapass.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dnapass.training.entity.Item;
import com.dnapass.training.entity.ItemDetails;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	//Query to display all item details based on their city for all user 
	@Query("select new com.dnapass.training.entity.ItemDetails(i.itemId,i.itemName,i.itemQuantity,u.username,u.mobileNumber,u.id) from Item i,User u where i.user.id=u.id and u.city=?1")
	List<ItemDetails> getItemDetailsBasedCity(@Param("code")String code);
	
	//Query to display all items for particular user 
	@Query(value="select * from item where user_id=?1",nativeQuery = true)
	List<Item> findAllItem(@Param("id")Integer id);
	
	//Query to display all item details with user details for the trader to order 
	@Query("select new com.dnapass.training.entity.ItemDetails(i.itemId,i.itemName,i.itemQuantity,u.username,u.mobileNumber,u.id) from Item i,User u where i.user.id=u.id and i.itemQuantity>0")
	List<ItemDetails> findAllItems();
	
	@Query(value="select * from item where user_id=?1",nativeQuery = true)
	Item findByItemid(@Param("id")Integer id);
}
