package bll;

import dao.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.*;
import java.util.List;

public class ProductBLL {
   private ProductDAO productDAO;

   public ProductBLL(){
       this.productDAO = new ProductDAO();
   }

   public Product findProductById(Integer id){
       Product result = productDAO.findById(id);
       if(result == null){
           System.out.println("No product with id "+id+ " was found");
       }else{
           System.out.println("Product with id "+id+ " was found");
           System.out.println(result.toString());
       }
       return result;
   }

   public List<Product> findAllProducts(){
       return productDAO.findAll();
   }

   public void addProduct(Product product){
       List<Product> products = productDAO.findAll();
       for(Product p : products){
           if(p.getID() == product.getID()){
               System.out.println("Product with id "+product.getID()+" already exists");
               throw new IllegalArgumentException("Product with id "+product.getID()+" already exists");
           }
       }
       productDAO.insert(product);
   }

   public void deleteProduct(Integer id){
       Product product = productDAO.findById(id);
       productDAO.delete(product.getID());
       System.out.println("Product with id " + product.getID() + " deleted.");
   }


   public void updateProduct(Integer id, Product newProduct){
       Product product = productDAO.findById(id);
       Integer newId = product.getID();
       product = new Product(newId, newProduct.getName(),newProduct.getPrice(), newProduct.getStock());
       productDAO.update(product,newId);
   }

    public JTable createTable (){
        List<Product> products = productDAO.findAll();
        //findAll e bun
        JTable table = productDAO.createTable(products); //si aici se creeaza bine tabelul
        return table;
    }
}
