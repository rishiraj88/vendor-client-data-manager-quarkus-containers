package de.anbieterundkunden.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendors")
public class Vendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="vendor_id")
  private long id;
  @Column(name="name")
  private String name;
  @Column(name="spoc")
  private String spoc;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpoc() {
    return spoc;
  }

  public void setSpoc(String spoc) {
    this.spoc = spoc;
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
    return "Vendor { " +
        "id = " + id +
        ", name = '" + name + '\'' +
        ", spoc = '" + spoc + '\'' +
        ", email = '" + email + '\'' +
        ", phone = '" + phone + '\'' +
        ", address = '" + address + '\'' +
        " }";
  }
}
