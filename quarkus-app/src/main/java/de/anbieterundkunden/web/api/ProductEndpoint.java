package de.anbieterundkunden.web.api;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.service.ProductService;

@Path("/rest/products")
public class ProductEndpoint {
    private final ProductService productService;
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @GET
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @Transactional
    @POST
    @ResponseStatus(201)
    public Product addProduct(Product product){
        this.productService.addProduct(product);
        return product;
    }

    @GET
    @Path("/{prodId}")
    public Product getProduct(@RestPath("prodId")long id){
        Product product = this.productService.getProduct(id);
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
        this.productService.updateProduct(product);
    }

    @Transactional
    @DELETE
    @Path("/{prodId}")
    @ResponseStatus(205)
    public void deleteProduct(@RestPath("prodId")long id){
        this.productService.deleteProduct(id);
    }
}