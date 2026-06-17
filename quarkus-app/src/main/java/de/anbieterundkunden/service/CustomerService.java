package de.anbieterundkunden.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import de.anbieterundkunden.data.entity.Customer;
import de.anbieterundkunden.data.repository.CustomerRepository;

@ApplicationScoped
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return this.customerRepository.listAll();
    }
    public List<Customer> getCustomersByEmail(String emailAddress){
        List<Customer> customers = new ArrayList<>();
        customers.addAll(this.customerRepository.findByEmail(emailAddress));
        return customers;
    }

    public Customer getCustomer(long custId){
        Customer customer =  this.customerRepository.findById(custId);
        if(customer == null){
            throw new NotFoundException();
        }
        return customer;
    }

    @Transactional
    public Customer addCustomer(Customer customer){
        this.customerRepository.persist(customer);
        return customer;
    }

    @Transactional
    public Customer updateCustomer(Customer customer){
        Customer entity = this.customerRepository.findById(customer.getId());
        if(entity == null){
            throw new NotFoundException();
        }
        entity.setAddress(customer.getAddress());
        entity.setEmail(customer.getEmail());
        entity.setFName(customer.getFName());
        entity.setPhone(customer.getPhone());
        entity.setLName(customer.getLName());
        this.customerRepository.persist(entity);
        return entity;
    }

    @Transactional
    public void deleteCustomer(long custId){
        this.customerRepository.deleteById(custId);
    }
}
