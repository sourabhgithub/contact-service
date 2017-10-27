package com.solstice.contact.etm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.solstice.contact.entities.PersonContact;
import com.solstice.contact.model.Contact;
import com.solstice.contact.model.ImmutableAddress;
import com.solstice.contact.model.ImmutableContact;
import com.solstice.contact.model.ImmutablePhone;

@Component
public class PersonContactToContactConverter implements Converter<PersonContact, Contact> {

  @Override
  public Contact convert(final PersonContact personContact) {

    return ImmutableContact.of(
        personContact.getName(),
        personContact.getCompany(),
        personContact.getProfileImage(),
        personContact.getBirthDate(),
        ImmutablePhone.of(personContact.getWorkNumber(), personContact.getPersonalNumber()),
        personContact.getEmail(),
        ImmutableAddress.of(
            personContact.getAddressLine(),
            personContact.getCity(),
            personContact.getState(),
            personContact.getZipCode(),
            personContact.getCountryCode()));
  }
}
