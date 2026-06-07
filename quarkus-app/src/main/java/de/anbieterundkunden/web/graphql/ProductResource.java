package de.anbieterundkunden.web.graphql;

import de.anbieterundkunden.data.entity.Product;
import de.anbieterundkunden.service.ProductService;
import de.anbieterundkunden.web.graphql.input.ProductInput;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import java.util.List;

@GraphQLApi
public class ProductResource {

  private final ProductService productService;

  public ProductResource(ProductService productService) {
    this.productService = productService;
  }

  @Query("allProducts")
  @Description("Gets all products available in the system")
  public List<Product> getAllProducts(){
    return this.productService.getAllProducts();
  }

  @Mutation("addProduct")
  @Description("Adds a product to the system")
  public Product addProduct(ProductInput product){
    return this.productService.addProduct(product.getEntity());
  }

  @Query("getProduct")
  @Description("Gets an individual product by ID")
  public Product getProduct(@Name("id")long id){
    return this.productService.getProduct(id);
  }

  @Mutation("updateProduct")
  @Description("Updates an individual product")
  public Product updateProduct(Product product){
    return this.productService.updateProduct(product);
  }

  @Mutation("deleteProduct")
  @Description("Removes an individual product")
  public Product deleteProduct(@Name("id")long id){
      Product product = this.productService.getProduct(id);
    this.productService.deleteProduct(id);
    return product;
  }
}
