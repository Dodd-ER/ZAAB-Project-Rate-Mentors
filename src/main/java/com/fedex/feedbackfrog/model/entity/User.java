package com.fedex.feedbackfrog.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
  @Id
  @GeneratedValue
  private long id;
  private String name;
  private boolean isAdmin;
  @OneToMany(mappedBy = "reviewer")
  private List<Review> sentReviews;
  private String emailAddress;


  public User() {}

  public User(String name, boolean isAdmin, List<Review> sentReviews) {
    this.name = name;
    this.isAdmin = isAdmin;
    this.sentReviews = sentReviews;
  }

  public User(String name, boolean isAdmin, List<Review> sentReviews, String email) {
    this.name = name;
    this.isAdmin = isAdmin;
    this.sentReviews = sentReviews;
    this.emailAddress = email;
  }

  public User(String name, boolean isAdmin, String emailAddress) {
    this.name = name;
    this.isAdmin = isAdmin;
    this.emailAddress = emailAddress;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public List<Review> getSentReviews() {
    return sentReviews;
  }

  public void setSentReviews(List<Review> sentReviews) {
    this.sentReviews = sentReviews;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }
}
