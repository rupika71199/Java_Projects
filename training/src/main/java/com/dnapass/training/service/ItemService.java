package com.dnapass.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnapass.training.entity.Item;
import com.dnapass.training.entity.ItemDetails;
import com.dnapass.training.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository itemRepo;
	// To add new item
	public Item addNewItem(Item item) {
		Item savedItem = new Item(item.getItemId(),item.getItemName(),item.getItemDescription(),item.getItemQuantity(),item.getUser());
		itemRepo.save(savedItem);
		return savedItem;
	}

	// To display all item details and its associated user for the trader
	public List<ItemDetails> getAllItems() {
		List<ItemDetails> itemDetails = itemRepo.findAllItems();
		return itemDetails;
	}

	// To delete the item based on id
	public void deleteItemDetails(Integer itemId) {
		itemRepo.deleteById(itemId);
	}

	// To fetch single item based on their id
	public Item getItemById(Integer itemId) {
		Item findItemById = itemRepo.findById(itemId).get();
		return findItemById;
	}

	// To display all item details when searching based on their name
	public List<ItemDetails> searchItem(String city) {
		List<ItemDetails> searchItemByCity = itemRepo.getItemDetailsBasedCity(city);
		return searchItemByCity;
	}

	// To display all items for a particular user
	public List<Item> getAllItemById(Integer id) {
		List<Item> getAllItems = itemRepo.findAllItem(id);
		return getAllItems;
	}
	
	//To display single item for particular user
	public Item getItemByItemId(Integer id) {
		Item getItem = itemRepo.findByItemid(id);
		return getItem;
	}
}
