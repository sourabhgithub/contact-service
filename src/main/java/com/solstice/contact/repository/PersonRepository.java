package com.solstice.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.solstice.contact.entities.PersonContact;

public interface PersonRepository
    extends JpaRepository<PersonContact, Long>, JpaSpecificationExecutor<PersonContact> {}
