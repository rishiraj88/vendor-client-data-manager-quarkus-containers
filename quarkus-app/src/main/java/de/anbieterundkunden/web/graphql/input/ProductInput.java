package de.anbieterundkunden.web.graphql.input;

import de.anbieterundkunden.data.entity.Product;
import java.math.BigDecimal;

public class ProductInput {
  private String name;
  private BigDecimal price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Product getEntity(){
    Product product = new Product();
    product.setName(this.name);
    product.setPrice(this.price);
    return product;
  }
}
