package mypackage.managementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.ArrayList;
/**
 * This Class contains all of the Inventory methods to create, update, and delete Parts or Products.
 *
 * @author Jorge Ramirez
 */
public class Inventory {
    private  static ObservableList<Part> allParts = FXCollections.observableArrayList(
            new InHouse(1, "Wire 3", 90.00, 15, 1, 20, 3),
            new InHouse(2, "Iron 2", 60.00, 20, 1, 50, 4),
            new OutSourced(3, "Camera", 50.00, 5, 1, 20, "Apple")
    );
    private static ObservableList<Product> allProducts =
            FXCollections.observableArrayList(
                    new Product(1, "Bike", 190.00, 24, 1, 205),
                    new Product(2, "Scooter 1", 160.00, 16, 1, 200),
                    new Product(3, "Shoes", 105.00, 102, 1, 300)
            );
    /**
     * @param newPart - the part to set
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    /**
     * @param newProduct - the product to set
     */
    public static void addProduct(Product newProduct){allProducts.add(newProduct);}
    /**
     * @param partId - the part ID to search
     */
    public static Part lookupPart(int partId){
        for(int i = 0; i < allParts.size(); i++){
            Part tempPart = allParts.get(i);
            if(tempPart.getId() == partId){
                return tempPart;
            }
        }
        return null;
    }
    /**
     * @param productId - the product ID to search
     */
    public static Product lookupProduct(int productId){
        for(int i = 0; i < allProducts.size(); i++){
            Product tempProduct = allProducts.get(i);
            if(tempProduct.getId() == productId){
                return tempProduct;
            }
        }
        return null;
    }
    /**
     * @param partName - the part Name to search
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> searchList = FXCollections.observableArrayList();

        for(Part part : allParts){
            if(part.getName().contains(partName)){
                searchList.add(part);
            }
        }
        return searchList;
    }
    /**
     * @param productName - the product Name to search
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> searchList = FXCollections.observableArrayList();

        for(Product product : allProducts){
            if(product.getName().contains(productName)){
                searchList.add(product);
            }
        }
        return searchList;
    }
    /**
     * @param selectedPart - the part to update
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.remove(index);
        allParts.add(index,selectedPart);
    }
    /**
     * @param newProduct - the product to update
     */
    public static void updateProduct(int index, Product newProduct){
        allProducts.remove(index);
        allProducts.add(index, newProduct);
    }
    /**
     * @param selectedPart - the part to delete
     */
    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        return true;
    }
    /**
     * @param selectedProduct - the product to delete
     */
    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }
    /**
     * @return ObservableList - all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /**
     * @return ObservableList - all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
