package de.anbieterundkunden.web.api;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.data.repository.ProductRepository;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;

@Path("/rest/products")
public class ProductEndpoint {
    private final ProductRepository productRepository;
    public ProductEndpoint(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GET
    public List<Product> getAllProducts(){
        return this.productRepository.listAll();
    }

    @Transactional
    @POST
    @ResponseStatus(201)
    public Product addProduct(Product product){
        this.productRepository.persist(product);
        return product;
    }

    @GET
    @Path("/{prodid}")
    public Product getProduct(@RestPath("prodid")long id){
        Product product = this.productRepository.findById(id);
        if(product ==null){
            throw new WebApplicationException(404);
        }
        return product;
    }

    @Transactional
    @PUT
    @Path("/{prodid}")
    @ResponseStatus(204)
    public void updateProduct(@RestPath("prodid")long id, Product product){
        if (id != product.getId()){
            throw new WebApplicationException(400);
        }
        this.productRepository.persist(product);
    }

    @Transactional
    @DELETE
    @Path("/{prodid}")
    @ResponseStatus(205)
    public void deleteProduct(@RestPath("prodid")long id){
        this.productRepository.deleteById(id);
    }
}