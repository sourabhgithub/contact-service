package com.solstice.contact.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableContactUpdateRequest.class)
public interface ContactUpdateRequest {

  @Value.Parameter
  @NotNull
  @Size(max = 30)
  String getName();

  @Value.Parameter
  @NotNull
  @Size(max = 30)
  String getCompany();
}
