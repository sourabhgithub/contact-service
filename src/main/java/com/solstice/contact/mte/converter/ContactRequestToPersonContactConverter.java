package com.solstice.contact.mte.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.solstice.contact.entities.PersonContact;
import com.solstice.contact.model.request.ContactRequest;

@Component
public class ContactRequestToPersonContactConverter
    implements Converter<ContactRequest, PersonContact> {
  @Override
  public PersonContact convert(final ContactRequest contactRequest) {

    PersonContact personalContact = new PersonContact();
    personalContact.setName(contactRequest.getName());
    personalContact.setCompany(contactRequest.getCompany());
    personalContact.setProfileImage(contactRequest.getProfileImage());
    personalContact.setBirthDate(contactRequest.getBirthDate());
    personalContact.setWorkNumber(contactRequest.getPhoneNumber().getWorkNumber());
    personalContact.setPersonalNumber(contactRequest.getPhoneNumber().getPersonalNumber());
    personalContact.setEmail(contactRequest.getEmail());
    personalContact.setAddressLine(contactRequest.getAddress().getAddressLine());
    personalContact.setCity(contactRequest.getAddress().getCity());
    personalContact.setState(contactRequest.getAddress().getState());
    personalContact.setZipCode(contactRequest.getAddress().getZipCode());
    personalContact.setCountryCode(contactRequest.getAddress().getCountryCode());
    return personalContact;
  }
}
