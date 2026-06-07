package de.anbieterundkunden.web.graphql;

import de.anbieterundkunden.data.entity.Customer;
import de.anbieterundkunden.service.CustomerService;
import de.anbieterundkunden.web.graphql.input.CustomerInput;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Query("allCustomers")
    @Description("Gets all customers from the system")
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @Query("customersByEmail")
    @Description("Gets customers that match a given email address")
    public List<Customer> getCustomersByEmail(@Name("email")String email){
        return this.customerService.getCustomersByEmail(email);
    }

    @Mutation("addCustomer")
    @Description("Adds a customer to the system")
    public Customer addCustomer(CustomerInput customer){
        return this.customerService.addCustomer(customer.getEntity());
    }

    @Query("getCustomer")
    @Description("Gets a single customer from the system")
    public Customer getCustomer(@Name("id")long id){
        return this.customerService.getCustomer(id);
    }

    @Mutation("updateCustomer")
    @Description("Updates a customer to the system")
    public Customer updateCustomer(Customer customer){
        return this.customerService.updateCustomer(customer);
    }

    @Mutation("deleteCustomer")
    @Description("Deletes a customer from the system")
    public Customer deleteCustomer(@Name("id")long id){
        Customer customer = this.customerService.getCustomer(id);
        this.customerService.deleteCustomer(id);
        return customer;
    }
}
