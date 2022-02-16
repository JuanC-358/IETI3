/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.repository;

/**
 *
 * @author JP
 */
import com.example.demo.data.User;
import java.util.Date;
import java.util.List;
 import org.springframework.data.mongodb.repository.MongoRepository;
  
  public interface UserRepository extends MongoRepository<User, String>
  {
    List<User> findByName(String queryText);
    List<User> findBylastName(String queryText);
    List<User> findBycreatedAtAfter(Date date);
    User findByEmail(String email);
  }
