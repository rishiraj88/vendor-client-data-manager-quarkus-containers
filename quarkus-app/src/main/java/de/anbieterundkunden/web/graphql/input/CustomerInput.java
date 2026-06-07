package de.anbieterundkunden.web.graphql.input;

import de.anbieterundkunden.data.entity.Customer;

public class CustomerInput {
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String address;

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLastName(String lName) {
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

    public Customer getEntity(){
        Customer customer = new Customer();
        customer.setLName(this.lName);
        customer.setFName(this.fName);
        customer.setPhone(this.phone);
        customer.setEmail(this.email);
        customer.setAddress(this.address);
        return customer;
    }
}
