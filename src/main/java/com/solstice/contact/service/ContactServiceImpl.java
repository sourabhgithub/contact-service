package com.solstice.contact.service;

import static com.solstice.contact.specs.PersonContactSpecs.filterByEmail;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.solstice.contact.entities.PersonContact;
import com.solstice.contact.etm.converter.PersonContactToContactConverter;
import com.solstice.contact.model.Contact;
import com.solstice.contact.model.ImmutableAddress;
import com.solstice.contact.model.ImmutableContact;
import com.solstice.contact.model.ImmutablePhone;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;
import com.solstice.contact.mte.converter.ContactRequestToPersonContactConverter;
import com.solstice.contact.repository.PersonRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

  static final Logger LOG = LoggerFactory.getLogger(ContactServiceImpl.class);

  @Autowired PersonRepository personRepository;

  @Autowired PersonContactToContactConverter personContactToContactConverter;

  @Autowired ContactRequestToPersonContactConverter contactRequestToPersonContactConverter;

  @Override
  public Page<Contact> getContacts(
      final Optional<String> email,
      final Optional<String> workNumber,
      final Optional<String> personalNumber,
      final Optional<String> city,
      final Optional<String> state,
      final Pageable pageable) {

    Specifications<PersonContact> spec =
        getFilteredCriteria(email, workNumber, personalNumber, city, state);

    return personRepository.findAll(spec, pageable).map(personContactToContactConverter);
  }

  @Override
  public Optional<Contact> getContact(final Long id) {
    return Optional.ofNullable(personRepository.findOne(id))
        .map(personContactToContactConverter::convert);
  }

  @Override
  public Contact createContact(final ContactRequest contactRequest) {

    PersonContact personContact = contactRequestToPersonContactConverter.convert(contactRequest);
    return mapToContact(personRepository.save(personContact));
  }

  @Override
  public Optional<Contact> updateContact(
      final Long id, final ContactUpdateRequest contactUpdateRequest) {
    return Optional.ofNullable(personRepository.findOne(id))
        .map(
            personContactEntity -> {
              personContactEntity.setName(contactUpdateRequest.getName());
              PersonContact updatedPersonContact = personRepository.save(personContactEntity);
              return personContactToContactConverter.convert(updatedPersonContact);
            });
  }

  @Override
  public void deleteContact(final Long id) {
    Optional.ofNullable(personRepository.findOne(id))
        .ifPresent(
            personContactEntity -> {
              personRepository.delete(personContactEntity);
            });
  }

  private Specifications<PersonContact> getFilteredCriteria(
      final Optional<String> email,
      final Optional<String> workNumber,
      final Optional<String> personalNumber,
      final Optional<String> city,
      final Optional<String> state) {
    Specifications<PersonContact> spec = where((root, query, cb) -> cb.and());

    if (email.isPresent()) spec = spec.and(filterByEmail(email.get()));
    if (workNumber.isPresent()) spec = spec.and(filterByEmail(workNumber.get()));
    if (personalNumber.isPresent()) spec = spec.and(filterByEmail(personalNumber.get()));
    if (city.isPresent()) spec = spec.and(filterByEmail(city.get()));
    if (state.isPresent()) spec = spec.and(filterByEmail(state.get()));
    return spec;
  }

  private Contact mapToContact(final PersonContact personContact) {
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
