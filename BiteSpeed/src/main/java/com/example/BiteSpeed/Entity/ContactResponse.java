package com.example.BiteSpeed.Entity;

import java.util.ArrayList;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
	Integer primaryContactId;
	HashSet<String> emails;
	HashSet<String> phoneNumbers;
	ArrayList<Integer> secondaryContactIds;
	
}
