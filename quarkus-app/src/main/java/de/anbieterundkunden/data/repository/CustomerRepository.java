package de.anbieterundkunden.data.repository;

import de.anbieterundkunden.data.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public Customer findByEmail(String email){
        return find("email", email).firstResult();
    }
}