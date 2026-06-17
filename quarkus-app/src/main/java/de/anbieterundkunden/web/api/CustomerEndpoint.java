package de.anbieterundkunden.web.api;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import de.anbieterundkunden.data.entity.Customer;
import de.anbieterundkunden.service.CustomerService;
import io.netty.util.internal.StringUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/rest/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {
  private final CustomerService customerService;
  public CustomerEndpoint(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GET
  public List<Customer> getCustomers(@RestQuery("email")String emailAddress){
    if(StringUtil.isNullOrEmpty(emailAddress.trim())){
      return this.customerService.getAllCustomers();
    }
    List<Customer> customers = new ArrayList<>();
    List<Customer> customersFound = this.customerService.getCustomersByEmail(emailAddress);
    customers.addAll(customersFound);
    return customers;
  }

  @Transactional
  @POST
  @ResponseStatus(201)
  public Customer addCustomer(Customer customer){
    this.customerService.addCustomer(customer);
    return customer;
  }

  @GET
  @Path("/{custId}")
  public Customer getCustomer(@RestPath("custId")long id){
    Customer customer = this.customerService.getCustomer(id);
    if (customer == null){
      throw new NotFoundException();
    }
    return customer;
  }

  @Transactional
  @PUT
  @Path("/{custId}")
  @ResponseStatus(204)
  public Customer updateCustomer(@RestPath("custId")long id, Customer customer){
    if(id != customer.getId()){
      throw new BadRequestException();
    }
    Customer entity = this.customerService.getCustomer(id);
    if(entity == null){
      throw new NotFoundException();
    }
    entity.setAddress(customer.getAddress());
    entity.setEmail(customer.getEmail());
    entity.setFName(customer.getFName());
    entity.setLName(customer.getLName());
    entity.setPhone(customer.getPhone());
    this.customerService.updateCustomer(entity);

    return entity;
  }

  @Transactional
  @DELETE
  @Path("/{custId}")
  @ResponseStatus(204)
  public void deleteCustomer(@RestPath("custId")long id){
    this.customerService.deleteCustomer(id);
  }
}
