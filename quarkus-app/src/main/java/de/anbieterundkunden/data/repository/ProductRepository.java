package de.anbieterundkunden.data.repository;

import de.anbieterundkunden.data.entity.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

  private final EntityManager em;

  public ProductRepository(EntityManager em) {
    this.em = em;
  }

  public List<Product> getAllProducts(){
    return this.em.createQuery("select product from Product product", Product.class).getResultList();
  }
}
