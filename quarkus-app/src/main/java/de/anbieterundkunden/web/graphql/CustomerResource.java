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
    @Description("Gets the data of all the customers")
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @Query("customersByEmail")
    @Description("Lists all customers, matched by email address")
    public List<Customer> getCustomersByEmail(@Name("email")String email){
        return this.customerService.getCustomersByEmail(email);
    }

    @Mutation("addCustomer")
    @Description("Save the details of a customer")
    public Customer addCustomer(CustomerInput customer){
        return this.customerService.addCustomer(customer.getEntity());
    }

    @Query("getCustomer")
    @Description("Gets the details of a customer, matched by Customer ID")
    public Customer getCustomer(@Name("id")long id){
        return this.customerService.getCustomer(id);
    }

    @Mutation("updateCustomer")
    @Description("Updates the details of a customer")
    public Customer updateCustomer(Customer customer){
        return this.customerService.updateCustomer(customer);
    }

    @Mutation("deleteCustomer")
    @Description("Removes a customer record")
    public Customer deleteCustomer(@Name("id")long id){
        Customer customer = this.customerService.getCustomer(id);
        this.customerService.deleteCustomer(id);
        return customer;
    }
}
