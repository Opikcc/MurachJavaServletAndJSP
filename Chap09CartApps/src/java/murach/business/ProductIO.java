package murach.business;

import java.util.ArrayList;

public class ProductIO {
  
  public static final ArrayList<Product> products;
 
  public static Product getProduct(String productCode) {
    Product product = new Product();
    
    for (Product prod : products) {
      if (prod.getCode().equals(productCode)) {
        product = prod;
        break;
      }
    }
    
    return product;
  }
  
  static {
    products = new ArrayList();
    products.add(new Product("8601", "86 (the band) - True Life Songs and Pictures", 14.95));
    products.add(new Product("pf01", "Paddlefoot = The First CD", 12.95));
    products.add(new Product("pf02", "Paddlefoot = The Second CD", 14.95));
    products.add(new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", 14.95));
  }
}
