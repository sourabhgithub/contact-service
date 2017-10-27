package com.solstice.contact.model;

import javax.validation.constraints.Size;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutablePhone.class)
public interface Phone {

  @Value.Parameter
  @Size(min = 10, max = 10)
  String getWorkNumber();

  @Value.Parameter
  @Size(min = 10, max = 10)
  String getPersonalNumber();
}
