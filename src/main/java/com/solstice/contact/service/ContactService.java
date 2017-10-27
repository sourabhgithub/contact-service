package com.solstice.contact.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.solstice.contact.model.Contact;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;

public interface ContactService {

  Page<Contact> getContacts(
      final Optional<String> email,
      final Optional<String> workNumber,
      final Optional<String> personalNumber,
      final Optional<String> city,
      final Optional<String> state,
      final Pageable pageable);

  Optional<Contact> getContact(final Long id);

  Contact createContact(final ContactRequest contactRequest);

  Optional<Contact> updateContact(final Long id, final ContactUpdateRequest contactUpdateRequest);

  void deleteContact(final Long id);
}
