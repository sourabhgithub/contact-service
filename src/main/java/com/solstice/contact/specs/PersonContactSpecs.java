package com.solstice.contact.specs;

import org.springframework.data.jpa.domain.Specification;

import com.solstice.contact.entities.PersonContact;

public class PersonContactSpecs {

  public static Specification<PersonContact> filterByEmail(final String email) {
    return (root, query, cb) -> cb.equal(root.get("email"), email);
  }

  public static Specification<PersonContact> filterByWorkNumber(final String workNumber) {
    return (root, query, cb) -> cb.equal(root.get("workNumber"), workNumber);
  }

  public static Specification<PersonContact> filterByPersonalNumber(final String personalNumber) {
    return (root, query, cb) -> cb.equal(root.get("personalNumber"), personalNumber);
  }

  public static Specification<PersonContact> filterByCity(final String city) {
    return (root, query, cb) -> cb.equal(root.get("city"), city);
  }

  public static Specification<PersonContact> filterByState(final String state) {
    return (root, query, cb) -> cb.equal(root.get("state"), state);
  }
}
