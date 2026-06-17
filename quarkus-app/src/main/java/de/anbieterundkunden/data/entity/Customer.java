package de.anbieterundkunden.data.entity;

import org.hibernate.validator.constraints.Email;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="customer_id")
  private long id;
  @Column(name="fName") @Size(max = 50)
  private String fName;
  @Column(name="lName") @NotNull
  private String lName;
  @Column(name="email") @NotNull @Email
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

  public String getFName() {
    return fName;
  }

  public void setFName(String fName) {
    this.fName = fName;
  }

  public String getLName() {
    return lName;
  }

  public void setLName(String lName) {
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
        ", first Name = '" + fName + '\'' +
        ", last Name = '" + lName + '\'' +
        ", email = '" + email + '\'' +
        ", phone = '" + phone + '\'' +
        ", address = '" + address + '\'' +
        " }";
  }

}
