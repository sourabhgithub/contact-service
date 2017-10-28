package com.solstice.contact.fixtures;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.solstice.contact.entities.PersonContact;

public class ContactServiceFixtures {

  public static PersonContact getPersonContactsEntity() {

    PersonContact personalContact = new PersonContact();
    personalContact.setName("Test Name");
    personalContact.setCompany("Test Company");
    personalContact.setProfileImage("now");
    personalContact.setBirthDate("19830101");
    personalContact.setWorkNumber("2332222222");
    personalContact.setPersonalNumber("2248189888");
    personalContact.setEmail("sourabhkumar.verma@gmail.com");
    personalContact.setAddressLine("149 WOODLAKE BLVD");
    personalContact.setCity("GURNEE");
    personalContact.setState("il");
    personalContact.setZipCode("60031");
    personalContact.setCountryCode("US");
    return personalContact;
  }

  public static Page<PersonContact> getPersonContactsEntityPage() {
    return new PageImpl<>(Arrays.asList(getPersonContactsEntity()));
  }
}
