package de.anbieterundkunden.web.api;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.data.repository.ProductRepository;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
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
    @Path("/{prodId}")
    public Product getProduct(@RestPath("prodId")long id){
        Product product = this.productRepository.findById(id);
        if(product ==null){
            throw new WebApplicationException(404);
        }
        return product;
    }

    @Transactional
    @PUT
    @Path("/{prodId}")
    @ResponseStatus(204)
    public void updateProduct(@RestPath("prodId")long id, Product product){
        if (id != product.getId()){
            throw new WebApplicationException(400);
        }
        this.productRepository.persist(product);
    }

    @Transactional
    @DELETE
    @Path("/{prodId}")
    @ResponseStatus(205)
    public void deleteProduct(@RestPath("prodId")long id){
        this.productRepository.deleteById(id);
    }
}