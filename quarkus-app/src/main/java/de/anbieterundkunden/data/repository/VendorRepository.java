package de.anbieterundkunden.data.repository;

import java.util.List;

import de.anbieterundkunden.data.entity.Vendor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VendorRepository implements PanacheRepository<Vendor> {
    public List<Vendor> findByEmail(String email){
        return find("email", email).list();
    }
    public List<Vendor> findByName(String name){
        return find("lower(name)", name.toLowerCase()).list();
    }
    public List<Vendor> findByEmailAndName(String email, String name) {
        return find("lower(name) = :name ; email = :email ",
                Parameters.with("name", name.toLowerCase()).and("email",email)).list();
    }
}