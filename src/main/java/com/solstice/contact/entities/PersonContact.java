package com.solstice.contact.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PERSON_CONTACT")
public class PersonContact implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "COMPANY")
  private String company;

  @Column(name = "PROF_IMG")
  private String profileImage;

  @Column(name = "DOB")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private String birthDate;

  @Column(name = "WRK_NUM")
  private String workNumber;

  @Column(name = "PER_NUM")
  private String personalNumber;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "ADDRESS")
  private String addressLine;

  @Column(name = "CITY")
  private String city;

  @Column(name = "STATE")
  private String state;

  @Column(name = "ZIPCODE")
  private String zipCode;

  @Column(name = "CCODE")
  private String countryCode;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(final String company) {
    this.company = company;
  }

  public String getProfileImage() {
    return profileImage;
  }

  public void setProfileImage(final String profileImage) {
    this.profileImage = profileImage;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(final String birthDate) {
    this.birthDate = birthDate;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public void setWorkNumber(final String workNumber) {
    this.workNumber = workNumber;
  }

  public String getPersonalNumber() {
    return personalNumber;
  }

  public void setPersonalNumber(final String personalNumber) {
    this.personalNumber = personalNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public String getAddressLine() {
    return addressLine;
  }

  public void setAddressLine(final String addressLine) {
    this.addressLine = addressLine;
  }

  public String getCity() {
    return city;
  }

  public void setCity(final String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(final String state) {
    this.state = state;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(final String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(final String countryCode) {
    this.countryCode = countryCode;
  }
}
