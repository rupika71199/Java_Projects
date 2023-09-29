package com.dnapass.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnapass.training.entity.Equipment;
import com.dnapass.training.entity.EquipmentDetails;
import com.dnapass.training.entity.Item;
import com.dnapass.training.entity.ItemDetails;
import com.dnapass.training.entity.Login;
import com.dnapass.training.entity.User;
import com.dnapass.training.service.EquipmentService;
import com.dnapass.training.service.ItemService;
import com.dnapass.training.service.UserService;

@RestController
@RequestMapping("/farmmarket")
public class FarmMarketController {

	@Autowired
	UserService userService;
	@Autowired
	EquipmentService equipmentService;
	@Autowired
	ItemService itemService;

	// Mapping with /login with requestbody login
	@PostMapping("/login")
	public ResponseEntity<Object> checkLogin(@RequestBody Login login) {
		try {
			User findUser = userService.findUser(login.getUserid());
			return new ResponseEntity<>(findUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/newuser"
	@PostMapping("/newuser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User savedUser = userService.saveUser(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Equipments

	// Mapping with "/addNewEquipment/{id}" to add new equipment for particular
	// userid with equipment body
	@PostMapping("/addNewEquipment/{id}")
	public ResponseEntity<Equipment> addNewEquipment(@RequestBody Equipment equipment,
			@PathVariable("id") Integer userId) {
		try {
			User getUserDetails = userService.findUser(userId);
			Equipment equipmentDetail = equipmentService.addEquipment(equipment);
			return new ResponseEntity<>(equipmentDetail, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Mapping with "/getAllEquipment"
	@GetMapping("/getAllEquipment")
	public ResponseEntity<List<Equipment>> getAllEquipment() {
		try {
			List<Equipment> equipmentList = equipmentService.getAllEquipment();
			if (equipmentList.isEmpty())
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(equipmentList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getAllEquipments" to display all equipment for farmer based on
	// count
	@GetMapping("/getAllEquipments")
	public ResponseEntity<List<Equipment>> getAllEquipments() {
		try {
			List<Equipment> equipmentList = equipmentService.getAllEquipment();
			if (equipmentList.isEmpty())
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(equipmentList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getAllEquipmentById/{id} based on user id
	@GetMapping("/getAllEquipmentById/{id}")
	public ResponseEntity<List<Equipment>> getAllEquipmentById(@PathVariable("id") Integer id) {
		try {
			List<Equipment> equipmentList = equipmentService.getAllEquipmentById(id);
			return new ResponseEntity<>(equipmentList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/search" based on city parameter
	@GetMapping("/search")
	public ResponseEntity<List<Equipment>> farmerSearchEquipment(@RequestParam("city") String city) {
		try {
			List<Equipment> searchByCity = equipmentService.searchEquipment(city);
			if (searchByCity.isEmpty())
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

			return new ResponseEntity<>(searchByCity, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/equipmentDetails/{uid}" to update equipment detail based on
	// particular user with equipment body
	@PutMapping("/equipmentDetails/{uid}")
	public ResponseEntity<Equipment> updateEquipmentDetails(@PathVariable("uid") Integer userId,
			@RequestBody Equipment equipment) {
		try {
			Equipment updateEquipment = equipmentService.getEquipmentById(userId);
			equipmentService.deleteEquipmentDetail(userId);
			updateEquipment.setName(equipment.getName());
			updateEquipment.setCount(equipment.getCount());
			updateEquipment.setRentPerDay(equipment.getRentPerDay());
			updateEquipment.setContactPerson(equipment.getContactPerson());
			updateEquipment.setMobileNumber(equipment.getMobileNumber());
			equipmentService.addEquipment(updateEquipment);
			return new ResponseEntity<>(updateEquipment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/deleteEquipmentDetails/{equipId}" to delete particular
	// equipment based on equipment id
	@DeleteMapping("/deleteEquipmentDetails/{equipId}")
	public ResponseEntity<HttpStatus> deleteEquipmentDetails(@PathVariable("equipId") Integer equipId) {

		try {
			equipmentService.deleteEquipmentDetail(equipId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping wih "/book/{equipId}/{userid}/{quantitycount}" to book an equipment
	// based on equipment id, user id and number of quantity
	@PutMapping("/book/{equipId}/{userid}/{quantitycount}")
	public ResponseEntity<Equipment> bookEquipment(@PathVariable("equipId") Integer id,
			@PathVariable("quantitycount") Integer ecount, @PathVariable("userid") Integer uid) {
		try {
			Equipment findEquipment = equipmentService.getEquipmentById(id);
			findEquipment.setCount(findEquipment.getCount() - ecount);
			equipmentService.addEquipment(findEquipment);
			return new ResponseEntity<>(findEquipment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/returnEquipment" to return an equipment and update the count
	@PutMapping("/returnEquipment")
	public ResponseEntity<Equipment> returnEquipment(@RequestBody EquipmentDetails edetails) {
		try {
			Equipment returnEquipmentDetail = equipmentService.getEquipmentById(edetails.getEquipmentId());
			returnEquipmentDetail.setCount(returnEquipmentDetail.getCount() + edetails.getQuantityCount());
			equipmentService.addEquipment(returnEquipmentDetail);
			return new ResponseEntity<>(returnEquipmentDetail, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getHiredEquipment/{id}" to view hired equipment based on user
	// id
	@GetMapping("/getHiredEquipment/{id}")
	public ResponseEntity<List<EquipmentDetails>> getHiredEquipment(@PathVariable("id") Integer id) {
		try {
			List<EquipmentDetails> equipmentDetails = equipmentService.getHiredEquipment(id);
			return new ResponseEntity<>(equipmentDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Items
	// Mapping with "/addNewItem/{id}" based on particular userid with Item body
	@PostMapping("/addNewItem/{id}")
	public ResponseEntity<Item> addNewItem(@RequestBody Item item, @PathVariable("id") Integer userId) {
		try {
			User getUserDetails = userService.findUser(userId);
			Item savedItem = itemService.addNewItem(item);
			return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getAllItems" to return all items for traders to view
	@GetMapping("/getAllItems")
	public ResponseEntity<List<ItemDetails>> getAllItems() {
		try {

			List<ItemDetails> itemDetails = itemService.getAllItems();
			return new ResponseEntity<>(itemDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getAllItemById/{id}" based on user id
	@GetMapping("/getAllItemById/{id}")
	public ResponseEntity<List<Item>> getAllItemById(@PathVariable("id") Integer id) {
		try {
			List<Item> itemList = itemService.getAllItemById(id);
			if (itemList.isEmpty())
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(itemList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/itemDetails/{uid}" to update the items based on particular
	// user with body Item
	@PutMapping("/itemDetails/{uid}")
	public ResponseEntity<Item> updateItemDetails(@PathVariable("uid") Integer userId, @RequestBody Item item) {
		try {
			Item getItem = itemService.getItemByItemId(userId);
			itemService.deleteItemDetails(userId);
			getItem.setItemName(item.getItemName());
			getItem.setItemDescription(item.getItemDescription());
			getItem.setItemQuantity(item.getItemQuantity());
			itemService.addNewItem(getItem);
			System.out.println(getItem);
			return new ResponseEntity<>(getItem, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/deleteItemDetails/{itemId}" to delete item based on itemId
	@DeleteMapping("/deleteItemDetails/{itemId}")
	public ResponseEntity<HttpStatus> deleteItemDetails(@PathVariable("itemId") Integer itemId) {
		try {
			itemService.deleteItemDetails(itemId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/searchItem" to search the item by trader based on city
	@GetMapping("/searchItem")
	public ResponseEntity<List<ItemDetails>> traderSearchItem(@RequestParam("city") String city) {
		try {
			List<ItemDetails> itemDetails = itemService.searchItem(city);
			return new ResponseEntity<>(itemDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/getFarmer/{id}" to display all items for that farmer based on
	// userid
	@GetMapping("/getFarmer/{id}")
	public ResponseEntity<User> getFarmerDetails(@PathVariable("id") Integer id) {
		try {
			User getFarmerDetails = userService.findUser(id);
			if (!getFarmerDetails.getRole().equalsIgnoreCase("farmer"))
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(getFarmerDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Mapping with "/orderItem/{itemId}" by trader based on itemid and update the
	// count of items
	@PutMapping("/orderItem/{itemId}")
	public ResponseEntity<ItemDetails> orderItem(@PathVariable("itemId") Integer id) {
		try {
			Item item = itemService.getItemById(id);
			item.setItemQuantity(item.getItemQuantity() - 1);
			ItemDetails itemDetails = new ItemDetails(item.getItemId(), item.getItemName(), item.getItemQuantity(),
					item.getUser().getUsername(), item.getUser().getMobileNumber(), item.getUser().getId());
			itemService.deleteItemDetails(id);
			itemService.addNewItem(item);
			return new ResponseEntity<>(itemDetails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
