package com.example.BiteSpeed.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiteSpeed.Entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer>{

}
