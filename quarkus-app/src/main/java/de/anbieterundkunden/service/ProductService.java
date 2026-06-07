package de.anbieterundkunden.service;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.data.repository.ProductRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return this.productRepository.listAll();
    }

    @Transactional
    public Product addProduct(Product product){
        this.productRepository.persist(product);
        return product;
    }

    public Product getProduct(long prodId){
        Product product =  this.productRepository.findById(prodId);
        if(product ==null){
            throw new NotFoundException();
        }
        return product;
    }

    @Transactional
    public Product updateProduct(Product product){

        Product entity = this.productRepository.findById(product.getId());
        if(entity == null){
            throw new NotFoundException();
        }
        entity.setPrice(product.getPrice());
        entity.setName(product.getName());
        this.productRepository.persist(entity);
        return entity;
    }

    @Transactional
    public void deleteProduct(long prodId){
        this.productRepository.deleteById(prodId);
    }
}
