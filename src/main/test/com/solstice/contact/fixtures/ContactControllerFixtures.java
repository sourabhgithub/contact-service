package com.solstice.contact.fixtures;

import java.util.Arrays;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.solstice.contact.model.Address;
import com.solstice.contact.model.Contact;
import com.solstice.contact.model.ImmutableAddress;
import com.solstice.contact.model.ImmutableContact;
import com.solstice.contact.model.ImmutablePhone;
import com.solstice.contact.model.Phone;
import com.solstice.contact.model.request.ContactRequest;
import com.solstice.contact.model.request.ContactUpdateRequest;
import com.solstice.contact.model.request.ImmutableContactRequest;
import com.solstice.contact.model.request.ImmutableContactUpdateRequest;

public class ContactControllerFixtures {

  public static Contact getContacts() {

    return ImmutableContact.of(
        "Test Name",
        "Test Company",
        "now",
        "19830101",
        getPhone(),
        "sourabhkumar.verma@gmail.com",
        getAddress());
  }

  public static Address getAddress() {

    return ImmutableAddress.of("149 WOODLAKE BLVD", "GURNEE", "il", "60031", "US");
  }

  public static Phone getPhone() {

    return ImmutablePhone.of("2332222222", "2248189888");
  }

  public static Page<Contact> getContactsPage() {
    return new PageImpl<>(Arrays.asList(getContacts()));
  }

  public static ContactRequest getContactRequest() {
    return ImmutableContactRequest.of(
        "Test Name",
        "Test Company",
        "now",
        "19830101",
        getPhone(),
        "sourabhkumar.verma@gmail.com",
        getAddress());
  }

  public static ContactUpdateRequest getContactUpdateRequest() {
    return ImmutableContactUpdateRequest.of(
        "Test Name",
        "Test Company",
        "now",
        "19830101",
        getPhone(),
        "sourabhkumar.verma@gmail.com",
        getAddress());
  }
}
