package mypackage.managementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * This Class contains all data that needs to persist during the running of the application
 *
 * @author Jorge Ramirez
 */
public class DataSingleton {
    private static final DataSingleton instance = new DataSingleton();
    private Inventory inventory = new Inventory();
    private ObservableList<Part> activePart = FXCollections.observableArrayList();
    private int activePartIndex;
    private int partType = 0;
    private Product activeProduct;
    private int activeProductIndex;
    private final AtomicInteger identifier = new AtomicInteger(3);
    private final AtomicInteger identifierProduct = new AtomicInteger(3);
    private ObservableList<Part> partsToAssociate = FXCollections.observableArrayList();
    private ObservableList<Part> partsToCache = FXCollections.observableArrayList();
    private boolean inList = false;
    private DataSingleton(){}

    /**
     * Static Instance of a DataSingleton
     * <p>
     * This method returns the Static DataSingleton object that is used for persistant data in the app.
     *
     * @return DataSingleton
     */
    public static DataSingleton getInstance(){
        return instance;
    }

    /**
     * Get Inventory
     * <p>
     * Retrieves Static Inventory Object
     *
     * @return Inventory
     */
    public Inventory getInventory(){
        return this.inventory;
    }
    /**
     * Get Identifier for part auto generation and increment the Identifier.
     * <p>
     * Retrieves the int value of the identifier variable.
     *
     * @return int
     */
    public int incrementAndGetIdentifier(){
        return identifier.incrementAndGet();
    }
    /**
     * Gets the Part Type for Front-end changes to the Application
     * <p>
     * Retrieves the int value of the part type to swap the final text fields in the part pages.
     *
     * @return int
     */
    public int getPartType(){
        return this.partType;
    }
    /**
     * Set the part type variable for Front-end changes to the Application
     * <p>
     * Sets the int value of the part type to swap the final text fields in the part pages.
     *
     * @return void
     */
    public void setPartType(int partType){
        this.partType = partType;
    }
    /**
     * Get the active part for Back-end changes to the data
     * <p>
     * Gets the active part ObservableList with the Part of unknown type inside.
     *
     * @return ObservableList
     */
    public ObservableList<Part> getActivePart(){
        return activePart;
    }
    /**
     * Set the active part for Back-end changes to the data
     * <p>
     * Sets the active part ObservableList with the Part of unknown type inside.
     *
     * @return void
     */
    public void setActivePart(ObservableList<Part> activePart){
        this.activePart = activePart;
    }
    /**
     * Get the active part index
     * <p>
     * Gets the active part index in the list of parts for use in the modify parts controller.
     *
     * @return int
     */
    public int getActivePartIndex(){
        return this.activePartIndex;
    }
    /**
     * Set the active part index
     * <p>
     * Sets the active part index in the list of parts for use in the modify parts controller.
     *
     * @return void
     */
    public void setActivePartIndex(int activePartIndex){
        this.activePartIndex = activePartIndex;
    }
    /**
     * Get the active product index
     * <p>
     * Gets the active product index in the list of parts for use in the modify product controller.
     *
     * @return int
     */
    public int getActiveProductIndex(){
        return this.activeProductIndex;
    }
    /**
     * Set the active product index
     * <p>
     * Sets the active product index in the list of parts for use in the modify product controller.
     *
     * @return void
     */
    public void setActiveProductIndex(int activeProductIndex){
        this.activeProductIndex = activeProductIndex;
    }
    /**
     * Get the parts to associate
     * <p>
     * Gets the parts you want to associate to a product during a modify and an addition of a product.
     *
     * @return ObservableList
     */
    public ObservableList<Part> getPartsToAssociate(){
        return this.partsToAssociate;
    }
    /**
     * Set the parts to associate
     * <p>
     * Sets the parts you want to associate to a product during a modify and an addition of a product.
     *
     * @return void
     */
    public void setPartsToAssociate(ObservableList<Part> partsToAssociate){
        this.partsToAssociate = partsToAssociate;
    }
    /**
     * Clears the parts to associate
     * <p>
     * Clears the parts you want to associate to a product during a modify and an addition of a product.
     *
     * @return void
     */
    public void clearPartsToAssociate(){
        this.partsToAssociate.clear();
    }
    /**
     * Check if part is in associated parts List
     * <p>
     * Checks if part is in associated parts List when adding a part to the associated parts list during an add or modify.
     *
     * @return Boolean
     */
    public boolean getInList(){
        return this.inList;
    }
    /**
     * Sets if part is in associated parts List
     * <p>
     * Sets if part is in associated parts List when adding a part to the associated parts list during an add or modify.
     *
     * @return void
     */
    public void setInList(boolean inList){
        this.inList = inList;
    }
    /**
     * Get Identifier for product auto generation and increment the Identifier.
     * <p>
     * Retrieves the int value of the identifier variable.
     *
     * @return int
     */
    public int incrementAndGetIdentifierProduct(){
        return this.identifierProduct.incrementAndGet();
    }
    /**
     * Get the parts to cache
     * <p>
     * Gets the associated parts you have cached incase of an accidental deletion during modifying.
     *
     * @return ObservableList
     */
    public ObservableList<Part> getPartsToCache(){
        return this.partsToCache;
    }
    /**
     * Set the parts to cache
     * <p>
     * Sets the associated parts you want cached incase of an accidental deletion during modifying.
     *
     * @return void
     */
    public void setPartsToCache(ObservableList<Part> partsToCache){
        this.partsToCache = partsToCache;
    }
    /**
     * Clear the parts to cache
     * <p>
     * Clear the associated parts you have cached incase of an accidental deletion during modifying.
     *
     * @return ObservableList
     */
    public void clearPartsToCache(){
        this.partsToCache.clear();
    }
    /**
     * Get the active product for Back-end changes to the data
     * <p>
     * Gets the active product
     *
     * @return Product
     */
    public Product getActiveProduct(){
        return this.activeProduct;
    }
    /**
     * Set the active product for Back-end changes to the data
     * <p>
     * Sets the active product
     *
     * @return void
     */
    public void setActiveProduct(Product activeProduct){
        this.activeProduct = activeProduct;
    }
}
