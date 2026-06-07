package de.anbieterundkunden;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.data.repository.ProductRepository;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

import javax.enterprise.context.control.ActivateRequestContext;
import java.util.List;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

    private final ProductRepository productRepository;

    public QuarkusApp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @ActivateRequestContext
    public int run(String... args) throws Exception {
        List<Product> products = this.productRepository.getAllProducts();
        products.forEach(System.out::println);
        return 0;
    }
}
