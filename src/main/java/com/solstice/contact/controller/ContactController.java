package com.solstice.contact.controller;

import java.util.Optional;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solstice.contact.model.Contact;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;
import com.solstice.contact.service.ContactService;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

  static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

  private static final Supplier<ResourceNotFoundException> notFoundException =
      () -> new ResourceNotFoundException("Resource not found");

  @Autowired ContactService contactService;

  @GetMapping
  public ResponseEntity<Page<Contact>> getContacts(
      @RequestParam(value = "email", required = false) final Optional<String> email,
      @RequestParam(value = "phoneNumber", required = false) final Optional<String> workNumber,
      @RequestParam(value = "phoneNumber", required = false) final Optional<String> personalNumber,
      @RequestParam(value = "city", required = false) final Optional<String> city,
      @RequestParam(value = "state", required = false) final Optional<String> state,
      final Pageable pageable) {
    Page<Contact> page =
        contactService.getContacts(email, workNumber, personalNumber, city, state, pageable);
    return new ResponseEntity<>(page, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public Contact getContact(@PathVariable final Long id, final Pageable pageable) {
    return contactService.getContact(id).orElseThrow(notFoundException);
  }

  @PostMapping
  public Contact createContact(@Valid @RequestBody final ContactRequest contactRequest) {
    return contactService.createContact(contactRequest);
  }

  @PutMapping("/{id}")
  public Contact updateContact(
      @PathVariable final Long id,
      @Valid @RequestBody final ContactUpdateRequest contactUpdateRequest) {
    return contactService.updateContact(id, contactUpdateRequest).orElseThrow(notFoundException);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContact(@PathVariable final Long id) {
    try {
      contactService.deleteContact(id);
      return ResponseEntity.noContent().build();
    } catch (final ResourceNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
