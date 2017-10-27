package com.solstice.contact.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableAddress.class)
public interface Address {

  @Value.Parameter
  @NotNull
  @Size(max = 30)
  String getAddressLine();

  @Value.Parameter
  @NotNull
  @Size(max = 15)
  String getCity();

  @Value.Parameter
  @NotNull
  @Size(max = 2)
  String getState();

  @Value.Parameter
  @NotNull
  @Size(max = 6)
  String getZipCode();

  @Value.Parameter
  @NotNull
  @Size(max = 2)
  String getCountryCode();
}
