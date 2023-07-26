package com.example.BiteSpeed.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.BiteSpeed.Entity.ContactEntity;
import com.example.BiteSpeed.Entity.ContactResponse;

@Service
public interface ContactService {
	public ContactResponse saveContact(ContactEntity c);

	public List<ContactEntity> getContacts();
}
