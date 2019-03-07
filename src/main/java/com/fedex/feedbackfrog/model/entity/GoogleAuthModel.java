package com.fedex.feedbackfrog.model.entity;

public class GoogleAuthModel {


  private Long google_alias;
  private String email;
  private boolean verified_email;
  private String given_name;
  private String family_name;
  private String name;
  private String gender;
  private String locale;

  public GoogleAuthModel(Long google_alias, String email, boolean verified_email, String given_name, String family_name, String name, String gender, String locale) {
    this.google_alias = google_alias;
    this.email = email;
    this.verified_email = verified_email;
    this.given_name = given_name;
    this.family_name = family_name;
    this.name = name;
    this.gender = gender;
    this.locale = locale;
  }

  public GoogleAuthModel() {
  }

  public Long getId() {
    return google_alias;
  }

  public void setId(Long google_alias) {
    this.google_alias = google_alias;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isVerified_email() {
    return verified_email;
  }

  public void setVerified_email(boolean verified_email) {
    this.verified_email = verified_email;
  }

  public String getGiven_name() {
    return given_name;
  }

  public void setGiven_name(String given_name) {
    this.given_name = given_name;
  }

  public String getFamily_name() {
    return family_name;
  }

  public void setFamily_name(String family_name) {
    this.family_name = family_name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getLocale() {
    return locale;
  }

  public void setLocale(String locale) {
    this.locale = locale;
  }
}
