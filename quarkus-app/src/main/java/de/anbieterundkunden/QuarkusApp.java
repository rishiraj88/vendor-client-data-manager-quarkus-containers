package de.anbieterundkunden;

import de.anbieterundkunden.data.entity.Customer;
import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.data.entity.Vendor;
import de.anbieterundkunden.data.repository.CustomerRepository;
import de.anbieterundkunden.data.repository.ProductRepository;
import de.anbieterundkunden.data.repository.VendorRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.control.ActivateRequestContext;
import java.util.List;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public QuarkusApp(ProductRepository productRepository, CustomerRepository customerRepository,
                      VendorRepository vendorRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {
        System.out.println("**\nProducts**");
        List<Product> products = this.productRepository.listAll();
        products.forEach(System.out::println);
        Product product = this.productRepository.findById(5L);
        System.out.println(product);
        System.out.println("**\nVendors**");
        Vendor vendor = this.vendorRepository.findByName("Wikivu");
        System.out.println("Vendor by name: " + vendor);
        vendor = this.vendorRepository.findByEmail("rkingx@baidu.com");
        System.out.println("Vendor by email: " + vendor);
        System.out.println("**\nCustomers**");
        Customer customer = this.customerRepository.findByEmail("in.felis@Mauriseu.co.uk");
        System.out.println("Customer by email: " + customer);

        return 0;
    }
}
