package de.anbieterundkunden.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="customer_id")
  private long id;
  @Column(name="fname")
  private String fName;
  @Column(name="lname")
  private String lName;
  @Column(name="email")
  private String email;
  @Column(name="phone")
  private String phone;
  @Column(name="address")
  private String address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFname() {
    return fName;
  }

  public void setFname(String fName) {
    this.fName = fName;
  }

  public String getLname() {
    return lName;
  }

  public void setLname(String lName) {
    this.lName = lName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer { " +
        "id = " + id +
        ", first name = '" + fName + '\'' +
        ", last name = '" + lName + '\'' +
        ", email = '" + email + '\'' +
        ", phone = '" + phone + '\'' +
        ", address = '" + address + '\'' +
        " }";
  }

}
