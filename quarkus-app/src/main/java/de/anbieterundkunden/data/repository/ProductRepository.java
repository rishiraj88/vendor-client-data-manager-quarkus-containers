package de.anbieterundkunden.data.repository;

import de.anbieterundkunden.data.entity.Product;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductRepository {

  private final EntityManager em;

  public ProductRepository(EntityManager em) {
    this.em = em;
  }

  public List<Product> getAllProducts(){
    List<Product> products = this.em.createQuery("select service from Service service", Product.class).getResultList();
    return products;
  }
}
