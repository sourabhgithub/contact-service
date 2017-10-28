package com.solstice.contact.service;

import static com.solstice.contact.fixtures.ContactControllerFixtures.getContactRequest;
import static com.solstice.contact.fixtures.ContactControllerFixtures.getContactUpdateRequest;
import static com.solstice.contact.fixtures.ContactControllerFixtures.getContacts;
import static com.solstice.contact.fixtures.ContactServiceFixtures.getPersonContactsEntity;
import static com.solstice.contact.fixtures.ContactServiceFixtures.getPersonContactsEntityPage;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import com.solstice.contact.entities.PersonContact;
import com.solstice.contact.etm.converter.PersonContactToContactConverter;
import com.solstice.contact.model.Contact;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;
import com.solstice.contact.mte.converter.ContactRequestToPersonContactConverter;
import com.solstice.contact.repository.PersonRepository;

@RunWith(SpringRunner.class)
public class ContactServiceTest {

  private final Class<Specification<PersonContact>> contactSpecificationClass = null;

  @Mock private PersonRepository personRepository;
  @Mock private PersonContactToContactConverter personContactToContactConverter;
  @Mock private ContactRequestToPersonContactConverter contactRequestToPersonContactConverter;

  @InjectMocks private ContactService contactService = new ContactServiceImpl();

  @Test
  public void getContacts_givenParameters_whenInvoked_shouldReturnContacts() {
    PersonContact personContactsEntity = getPersonContactsEntity();

    given(personRepository.findAll(any(contactSpecificationClass), any(Pageable.class)))
        .willReturn(getPersonContactsEntityPage());
    given(this.personContactToContactConverter.convert(any(PersonContact.class)))
        .willReturn(getContacts());
    Page<Contact> contactsResponse =
        contactService.getContacts(
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            new PageRequest(0, 1));

    contactsResponse.forEach(
        contact -> {
          assertThat(contact.getName(), equalTo(personContactsEntity.getName()));
          assertThat(contact.getEmail(), equalTo(personContactsEntity.getEmail()));
        });
  }

  @Test
  public void getContact_givenContactId_whenInvoked_shouldReturnContacts() {
    PersonContact personContactsEntity = getPersonContactsEntity();

    given(personRepository.findOne(any(Long.class))).willReturn(getPersonContactsEntity());
    given(this.personContactToContactConverter.convert(any(PersonContact.class)))
        .willReturn(getContacts());
    Optional<Contact> contactsResponse = contactService.getContact(1L);
    assertThat(contactsResponse, is(not(Optional.empty())));
    assertThat(contactsResponse.get().getName(), equalTo(personContactsEntity.getName()));
    assertThat(contactsResponse.get().getEmail(), equalTo(personContactsEntity.getEmail()));
  }

  @Test
  public void getContact_givenContactID_whenInvokedAndNoResultFound_shouldReturnOptionalEmpty() {
    given(personRepository.findOne(any(Long.class))).willReturn(null);
    Optional<Contact> contactsResponse = contactService.getContact(1L);
    assertThat(contactsResponse, is(Optional.empty()));
  }

  @Test
  public void createContact_givenContactRequest_whenInvoked_ShouldReturnNewlyCreatedContact() {
    ContactRequest contactRequest = getContactRequest();
    given(this.personRepository.save(any(PersonContact.class)))
        .willReturn(getPersonContactsEntity());
    given(this.contactRequestToPersonContactConverter.convert(any(ContactRequest.class)))
        .willReturn(getPersonContactsEntity());

    Contact createContactResponse = contactService.createContact(contactRequest);
    assertThat(createContactResponse.getName(), equalTo(contactRequest.getName()));
  }

  @Test
  public void
      updateContact_givenContactIdAndContactUpdateRequest_whenInvoked_ShouldReturnUpdatedContact() {
    ContactUpdateRequest contactUpdateRequest = getContactUpdateRequest();
    given(personRepository.findOne(any(Long.class))).willReturn(getPersonContactsEntity());
    given(this.personContactToContactConverter.convert(any(PersonContact.class)))
        .willReturn(getContacts());
    given(this.personRepository.save(any(PersonContact.class)))
        .willReturn(getPersonContactsEntity());

    Optional<Contact> updateContactResponse =
        contactService.updateContact(1L, contactUpdateRequest);
    assertThat(updateContactResponse, is(not(Optional.empty())));
    assertThat(updateContactResponse.get().getName(), equalTo(contactUpdateRequest.getName()));
    assertThat(updateContactResponse.get().getEmail(), equalTo(contactUpdateRequest.getEmail()));
  }

  @Test
  public void
      updateContact_givenInvalidContactIdAndContactUpdateRequest_whenInvoked_ShouldReturnOptionalEmpty() {
    ContactUpdateRequest contactUpdateRequest = getContactUpdateRequest();
    given(personRepository.findOne(any(Long.class))).willReturn(null);
    Optional<Contact> updateContactResponse =
        contactService.updateContact(1L, contactUpdateRequest);
    assertThat(updateContactResponse, is(Optional.empty()));
  }


  @Test
  public void
      deleteContact_givenContactId_whenInvoked_ShouldReturnNothing() {
    given(personRepository.findOne(any(Long.class))).willReturn(getPersonContactsEntity());
    doNothing().when(this.personRepository).delete(any(PersonContact.class));
    contactService.deleteContact(1L);
    verify(this.personRepository, times(1)).delete(any(PersonContact.class)); 
    }

}
