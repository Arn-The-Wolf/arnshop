package com.arnshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.arnshop.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    
} 