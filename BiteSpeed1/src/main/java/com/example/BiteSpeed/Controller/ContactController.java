package com.example.BiteSpeed.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BiteSpeed.Entity.ContactEntity;
import com.example.BiteSpeed.Entity.ContactResponse;
import com.example.BiteSpeed.Service.ContactService;

@RestController
public class ContactController {
	@Autowired
	ContactService contact;
	@PostMapping(value = "/identify")
	public ContactResponse sendRequest(@RequestBody ContactEntity c) {
		return contact.saveContact(c);
	}
	@GetMapping(value = "/fetch")
	public List<ContactEntity> fetchData(){
		return contact.getContacts();
	}
}
