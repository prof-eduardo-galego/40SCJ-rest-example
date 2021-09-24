package fiap.scj40.storectrl.controllers;

import fiap.scj40.storectrl.exceptions.EntityNotFound;
import fiap.scj40.storectrl.models.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

  private static final List<Product> STORAGE = new ArrayList<>();

  static {
    STORAGE.add(new Product(10, "Produto 10", 1645.80));
    STORAGE.add(new Product(11, "Produto 11", 184.90));
  }

  // [C]reate
  @PostMapping
  public ResponseEntity<Product> create(@RequestBody Product product) {
    STORAGE.add(product);
    System.out.println("product created: " + product);
    return ResponseEntity
            .created(URI.create("/products/"+ product.getId()))
            .body(product);
  }

  // [R]etrieve All
  //@GetMapping
  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @Operation(summary = "Listar todos", description = "Lista todos os produtos armazenados.")
  public List<Product> listAll() {
    return STORAGE;
  }

  // [R]etrieve One
  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@Parameter(description = "Identificador do Produto")
                                          @PathVariable Integer id) {
    Product product = STORAGE.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  // [U]pdate
  @PutMapping("{id}")
  public Product update(@PathVariable("id") Integer productId, @RequestBody Product product) {
    Product productUpdated = STORAGE.stream().filter(p -> p.getId().equals(productId)).findFirst().orElse(null);
    if (productUpdated == null) {
//      throw new EntityNotFound("Product");
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found");
    }
    productUpdated.setDescription(product.getDescription());
    productUpdated.setPrice(product.getPrice());
    return productUpdated;
  }

  // [D]elete
  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    STORAGE.removeIf(p -> p.getId().equals(id));
  }

}
