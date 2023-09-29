package com.dnapass.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnapass.training.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
