package de.anbieterundkunden.web.graphql.input;

import de.anbieterundkunden.data.entity.Vendor;

public class VendorInput {

    private String name;
    private String spoc;
    private String email;
    private String phone;
    private String address;

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

    public Vendor getEntity(){
        Vendor vendor = new Vendor();
        vendor.setPhone(this.phone);
        vendor.setName(this.name);
        vendor.setEmail(this.email);
        vendor.setSpoc(this.spoc);
        vendor.setPhone(this.phone);
        return vendor;
    }
}
