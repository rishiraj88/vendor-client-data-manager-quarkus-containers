package de.anbieterundkunden.web.api;

import de.anbieterundkunden.data.entity.Customer;
import de.anbieterundkunden.data.repository.CustomerRepository;
import io.netty.util.internal.StringUtil;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/rest/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {
  private final CustomerRepository customerRepository;
  public CustomerEndpoint(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @GET
  public List<Customer> getCustomers(@RestQuery("email")String emailAddress){
    if(StringUtil.isNullOrEmpty(emailAddress)){
      return this.customerRepository.listAll();
    }
    List<Customer> customers = new ArrayList<>();
    Customer customer = this.customerRepository.findByEmail(emailAddress);
    customers.add(customer);
    return customers;
  }

  @Transactional
  @POST
  @ResponseStatus(201)
  public Customer addCustomer(Customer customer){
    this.customerRepository.persist(customer);
    return customer;
  }

  @GET
  @Path("/{custid}")
  public Customer getCustomer(@RestPath("custid")long id){
    Customer customer = this.customerRepository.findById(id);
    if (customer == null){
      throw new NotFoundException();
    }
    return customer;
  }

  @Transactional
  @PUT
  @Path("/{custid}")
  @ResponseStatus(204)
  public void updateCustomer(@RestPath("custid")long id, Customer customer){
    if(id != customer.getId()){
      throw new BadRequestException();
    }
    Customer entity = this.customerRepository.findById(id);
    if(entity == null){
      throw new NotFoundException();
    }
    entity.setAddress(customer.getAddress());
    entity.setEmail(customer.getEmail());
    entity.setFname(customer.getFname());
    entity.setLname(customer.getLname());
    entity.setPhone(customer.getPhone());
    this.customerRepository.persist(entity);
  }

  @Transactional
  @DELETE
  @Path("/{custid}")
  @ResponseStatus(205)
  public void deleteCustomer(@RestPath("custid")long id){
    this.customerRepository.deleteById(id);
  }
}
