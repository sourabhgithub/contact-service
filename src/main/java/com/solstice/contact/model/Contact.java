package com.solstice.contact.model;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableContact.class)
public interface Contact {

  @Value.Parameter
  String getName();

  @Value.Parameter
  String getCompany();

  @Value.Parameter
  String getProfileImage();

  @Value.Parameter
  @JsonFormat(pattern = "yyyy-MM-dd")
  String getBirthDate();

  @Value.Parameter
  Phone getPhoneNumber();

  @Value.Parameter
  String getEmail();

  @Value.Parameter
  Address getAddress();
}
