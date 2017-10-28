package com.solstice.contact.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solstice.contact.model.Address;
import com.solstice.contact.model.Phone;

@Value.Immutable
@JsonDeserialize(as = ImmutableContactUpdateRequest.class)
public interface ContactUpdateRequest {

  @Value.Parameter
  @Size(max = 30)
  @NotNull
  String getName();

  @Value.Parameter
  @Size(max = 30)
  @NotNull
  String getCompany();

  @Value.Parameter
  @Size(max = 30)
  @NotNull
  String getProfileImage();

  @Value.Parameter
  @JsonFormat(pattern = "yyyy-MM-dd")
  String getBirthDate();

  @Value.Parameter
  @NotNull
  Phone getPhoneNumber();

  @Value.Parameter
  @Size(max = 30)
  @Email(message = "Please provide a valid email address")
  @NotNull
  String getEmail();

  @Value.Parameter
  @NotNull
  Address getAddress();
}
