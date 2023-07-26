package com.example.BiteSpeed.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiteSpeed.Entity.ContactEntity;
import com.example.BiteSpeed.Entity.ContactResponse;
import com.example.BiteSpeed.Repository.ContactRepository;

@Service
public class ContactServiceImplementation implements ContactService{
	
	@Autowired
    ContactRepository conRepo;
	@Override
	public ContactResponse saveContact(ContactEntity c) {
		
		List<ContactEntity> contactList = conRepo.findAll();
		ContactResponse temp = new ContactResponse();
		ContactEntity temp1=null;
		ArrayList<Integer> secondaryContact = new ArrayList<>();
		HashSet<String> emailIds = new HashSet<>();
		HashSet<String> phoneNumbers = new HashSet<>();
		boolean flag = false;
		//boolean flagship = false;
		int counter =0;
		for(ContactEntity ctr: contactList) {
			if(ctr.getEmail().equals(c.getEmail()) || ctr.getPhoneNumber().equals(c.getPhoneNumber())) {
				flag=true;
				
				if(counter==0) {
					temp1=ctr;
					//flagship = true;

				}
				counter++;
			}
		}
		if(flag && counter==1) {
			conRepo.delete(temp1);
			temp1.setLinkPrecedence("primary");
			conRepo.save(temp1);
			c.setLinkedId(temp1.getId());
			c.setLinkPrecedence("secondary");
		}
		else if(flag) {
			c.setLinkedId(temp1.getId());
			c.setLinkPrecedence("secondary");
		}
		else {
			c.setLinkedId(null);
			c.setLinkPrecedence("primary");
		}
			
		conRepo.save(c);
		List<ContactEntity> contactList1 = conRepo.findAll();
		for(ContactEntity ctr: contactList1) {
			if((c.getEmail().equals(ctr.getEmail()) || c.getPhoneNumber().equals(ctr.getPhoneNumber())) && ctr.getLinkPrecedence().equals("primary")) {
				temp.setPrimaryContactId(ctr.getId());
			}
			if((c.getEmail().equals(ctr.getEmail()) || c.getPhoneNumber().equals(ctr.getPhoneNumber())) && ctr.getLinkPrecedence().equals("secondary")) {
				secondaryContact.add(ctr.getId());
				
			}
			if((c.getEmail().equals(ctr.getEmail()))) {
				phoneNumbers.add(ctr.getPhoneNumber());
				
			}
			if(c.getPhoneNumber().equals(ctr.getPhoneNumber())) {
				emailIds.add(ctr.getEmail());
			}

		}
		temp.setEmails(emailIds);
		temp.setPhoneNumbers(phoneNumbers);
		temp.setSecondaryContactIds(secondaryContact);
		return temp;
	
	}
	@Override
	public List<ContactEntity> getContacts() {
		return conRepo.findAll();
	}
}
