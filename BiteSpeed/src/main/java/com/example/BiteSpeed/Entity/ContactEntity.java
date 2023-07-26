package com.example.BiteSpeed.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ContactEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	String phoneNumber;
	String email;
	Integer linkedId;
	String linkPrecedence;
	@CreationTimestamp
	Date createdAt;

	@UpdateTimestamp
	Date updatedAt;
	LocalDateTime deletedAt;
	public ContactEntity(String phoneNumber, String email, Integer linkedId, String linkPrecedence,
			Date createdAt, Date updatedAt, LocalDateTime deletedAt) {
		super();
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.linkedId = linkedId;
		this.linkPrecedence = linkPrecedence;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	
}
