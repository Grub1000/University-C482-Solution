package mypackage.managementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This Class is used to create Product objects that contain Part objects through association.
 *
 * @author Jorge Ramirez
 */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /**
     * Creates product object
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    /**
     * sets id
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * sets name
     */
    public void setName(String name){
        this.name = name;
    }
    /**
     * sets price
     */
    public void setPrice(double price){
        this.price = price;
    }
    /**
     * sets stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }
    /**
     * sets min
     */
    public void setMin(int min){
        this.min = min;
    }
    /**
     * sets max
     */
    public void setMax(int max){
        this.max = max;
    }
    /**
     * @return id
     */
    public int getId(){
        return this.id;
    }
    /**
     * @return name
     */
    public String getName(){return this.name;}
    /**
     * @return price
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * @return stock
     */
    public int getStock(){return this.stock;}
    /**
     * @return min
     */
    public int getMin(){
        return this.min;
    }
    /**
     * @return max
     */
    public int getMax(){
        return this.max;
    }
    /**
     * adds associated part
     */
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }
    /**
     * @return true
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        this.associatedParts.remove(selectedAssociatedPart);
        return true;
    }
    /**
     * @return list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }

}
