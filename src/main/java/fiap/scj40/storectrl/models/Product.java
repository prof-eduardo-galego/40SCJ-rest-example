package fiap.scj40.storectrl.models;

import io.swagger.v3.oas.annotations.media.Schema;

public class Product {

  @Schema(description = "Identificador do Produto", nullable = false)
  private Integer id;

  @Schema(description = "Descrição do Produto", nullable = false)
  private String description;

  @Schema(description = "Preço unitário de venda do Produto")
  private Double price;

  public Product() {
  }

  public Product(Integer id, String description, Double price) {
    this.id = id;
    this.description = description;
    this.price = price;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Product{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", price=" + price +
            '}';
  }
}
