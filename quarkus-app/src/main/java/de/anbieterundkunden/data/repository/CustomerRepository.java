package de.anbieterundkunden.data.repository;

import java.util.List;

import de.anbieterundkunden.data.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> findByEmail(String email){
        return find("email", email).list();
    }
}