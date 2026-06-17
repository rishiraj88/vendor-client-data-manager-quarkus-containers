package de.anbieterundkunden.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import de.anbieterundkunden.data.entity.Vendor;
import de.anbieterundkunden.data.repository.VendorRepository;

@ApplicationScoped
public class VendorService {
    private final VendorRepository vendorRepository;
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    
    public List<Vendor> getAllVendors(){
        return this.vendorRepository.listAll();
    }
    
    public List<Vendor> getVendorsByName(String vendorName){
        List<Vendor> vendors = new ArrayList<>();
        vendors.addAll(this.vendorRepository.findByName(vendorName));
        return vendors;
    }
    
    public List<Vendor> getVendorsByEmail(String emailAddress){
        List<Vendor> vendors = new ArrayList<>();
        vendors.addAll(this.vendorRepository.findByEmail(emailAddress));
        return vendors;
    }

    public Vendor getVendor(long vendorId){
        Vendor vendor = this.vendorRepository.findById(vendorId);
        if(vendor == null){
            throw new NotFoundException();
        }
        return vendor;
    }

    public List<Vendor> getVendorsByEmailAndName(String emailAddress, String vendorName){
        List<Vendor> vendors = new ArrayList<>();
        vendors.addAll(this.vendorRepository.findByEmailAndName(emailAddress, vendorName));
        return vendors;
    }
    
    @Transactional
    public Vendor addVendor(Vendor vendor){
        this.vendorRepository.persist(vendor);
        return vendor;
    }
    
    @Transactional
    public Vendor updateVendor(Vendor vendor){
        Vendor entity = this.vendorRepository.findById(vendor.getId());
        if (entity == null){
            throw new NotFoundException();
        }
        entity.setName(vendor.getName());
        entity.setSpoc(vendor.getSpoc());
        entity.setEmail(vendor.getEmail());
        entity.setPhone(vendor.getPhone());
        entity.setAddress(vendor.getAddress());
        this.vendorRepository.persist(entity);

        return entity;
    }
    
    @Transactional
    public void deleteVendor(long vendorId){
        this.vendorRepository.deleteById(vendorId);
    }
}
