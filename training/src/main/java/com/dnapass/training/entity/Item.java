package com.dnapass.training.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
public class Item {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "itemId")
	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private Integer itemQuantity;
	@OneToOne
	@JoinColumn(name = "userId")
	@JsonBackReference
	private User user;
	public Item(Integer id, String itemName, String itemDescription, Integer quantity, User user) {
		super();
		this.itemId = id;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemQuantity = quantity;
		this.user = user;
	}
	public Item(String itemName, String itemDescription, Integer quantity, User user) {
		super();
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemQuantity = quantity;
		this.user = user;
	}
	
	
}
