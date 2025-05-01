package mypackage.managementsystem;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * Controller for the mainPage.fxml file
 *
 * <p>
 * <h1>FUTURE ENCHANCEMENTS:</h1> A few enhancements like -
 * re-using more common code and putting it in a function and
 * using a single controller / fxml-file for both add and modify features would
 * make the source code cleaner and more readable at times. Another enhancement
 * would be to make the search bar dynamically search on every keystroke.
 *
 * <h1>RUNTIME ERROR:</h1> I ran into a runtime error many times when implementing
 * the features in the AddPart and ModifyPart controllers when a user would
 * go from Inhouse to Outsourced using the radio buttons. I couldn't quite figure
 * the problem out until I began to debug my program by adding System.out.println("test")
 * comments throughout the controllers to figure out where the problem was occuring. It
 * turned out that I had hard coded the Machine ID and Company Name when I was testing earlier
 * and that had resulted in a huge headache later on.
 *
 * @author Jorge Ramirez
 */
public class MainController implements Initializable {
    /**
     * Parts Search text field
     */
    public TextField partsSectionWindowSearchBar;
    /**
     * Product Search text field
     */
    public TextField productsSectionWindowSearchBar;
    /**
     *  Add Part Button object
     */
    public Button addButton;
    /**
     *  Modify Part Button object
     */
    public Button modifyButton;
    /**
     *  Delete Part Button object
     */
    public Button deleteButton;
    /**
     *  Add Product Button object
     */
    public Button productAddButton;
    /**
     *  Modify Product Button object
     */
    public Button productModifyButton;
    /**
     *  Delete Product Button object
     */
    public Button productDeleteButton;
    /**
     *  Exit Main Page Button object
     */
    public Button exitMainPageButton;
    /**
     *  Part ID Table Column object
     */
    public TableColumn partIdCol;
    /**
     *  Part Name Table Column object
     */
    public TableColumn partNameCol;
    /**
     *  Part Inventory Table Column object
     */
    public TableColumn partInventoryLvlCol;
    /**
     *  Part Price Table Column object
     */
    public TableColumn partPriceCostPerUnitCol;
    /**
     *  Part Table Object
     */
    public TableView partsTable;
    /**
     *  Product Table Object
     */
    public TableView productsTable;
    /**
     *  Product ID Table Column object
     */
    public TableColumn productIdCol;
    /**
     *  Product Name Table Column object
     */
    public TableColumn productNameCol;
    /**
     *  Product Inventory Table Column object
     */
    public TableColumn productInventoryLvlCol;
    /**
     *  Product Price Table Column object
     */
    public TableColumn productPriceCostPerUnitCol;
    /**
     *  Product Table Button objects Wrapper
     */
    public HBox productsSectionWindowBtnsWrapper;
    /**
     *  Product Table On-Delete Exeption Text Wrapper
     */
    public Text productDeleteWithAssociatedExceptionText;
    /**
     *  Static Inventory Instance from Persistent Static Data Instance
     */

    public Inventory inventory = DataSingleton.getInstance().getInventory();
    /**
     *  Instance of Persistent Static Data Class
     */
    private DataSingleton data = DataSingleton.getInstance();
    /**
     * Part Search Text Field Function
     * <p>
     * This method runs when you click enter on while on the search bar and returns nothing.
     * This method repopulates the Part Table with your desired search criteria.
     *
     * @param  actionEvent Event
     * @return void
     */
    @FXML
    public void partsSearchHandler(ActionEvent actionEvent) {
            ObservableList<Part> searchList = inventory.lookupPart(partsSectionWindowSearchBar.getText());
            try {
            if(!searchList.contains(inventory.lookupPart(Integer.parseInt(partsSectionWindowSearchBar.getText())))){
                if(inventory.lookupPart(Integer.parseInt(partsSectionWindowSearchBar.getText())) != null){
                    searchList.add(inventory.lookupPart(Integer.parseInt(partsSectionWindowSearchBar.getText())));
                }
//                searchList.add(inventory.lookupPart(Integer.parseInt(partsSectionWindowSearchBar.getText())));
            }
            } catch(NumberFormatException e){

            }
            if(searchList.isEmpty()){
                Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
                deleteAlert.setTitle("No Search Results");
                deleteAlert.setHeaderText("No Search Results");
                deleteAlert.setContentText("There were no matching parts.");
                Optional<ButtonType> result = deleteAlert.showAndWait();
                if(result.get() == ButtonType.OK){
                }
            }
            partsTable.setItems(searchList);
            System.out.println("Part Search");
    }
    /**
     * Runs before all other methods
     * <p>
     * This method always runs at when we create an instance of this class and
     * returns nothing.
     * @param  url  URL
     * @param  resourceBundle Event
     * @return void
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(inventory.getAllParts());

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCostPerUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(inventory.getAllProducts());

//        partsTable.getSortOrder().add(partIdCol);
//        partsTable.refresh();
        productsSectionWindowBtnsWrapper.getChildren().remove(productDeleteWithAssociatedExceptionText);

    }
    /**
     * Product Search Text Field Function
     * <p>
     * This method runs when you click enter on while on the search bar and returns nothing.
     * This method repopulates the Product Table with your desired search criteria.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void productsSearchHandler(ActionEvent actionEvent) {
        ObservableList<Product> searchList = inventory.lookupProduct(productsSectionWindowSearchBar.getText());
        try {
            if(!searchList.contains(inventory.lookupProduct(Integer.parseInt(productsSectionWindowSearchBar.getText())))){
                if(inventory.lookupProduct(Integer.parseInt(productsSectionWindowSearchBar.getText())) != null){
                    searchList.add(inventory.lookupProduct(Integer.parseInt(productsSectionWindowSearchBar.getText())));
                }
            }
        } catch(NumberFormatException e){

        }
        if(searchList.isEmpty()){
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("No Search Results");
            deleteAlert.setHeaderText("No Search Results");
            deleteAlert.setContentText("There were no matching products.");
            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == ButtonType.OK){
            }
        }
        productsTable.setItems(searchList);
    }
    /**
     * Add Part Button Function
     * <p>
     * This method runs when you click the ADD button on the parts list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handlePartAdd(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("addPartPage.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 500,450);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Modify Part Button Function
     * <p>
     * This method runs when you click the Modify button on the parts list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handlePartModify(ActionEvent actionEvent) throws IOException{
        AtomicBoolean selectionMade = new AtomicBoolean(false);
        ObservableList<Part> tempInv = inventory.getAllParts();
        tempInv.forEach((x) -> {
            ObservableList<Part> activePart = data.getActivePart();
            if (x == partsTable.getSelectionModel().getSelectedItem()) {
                data.setActivePartIndex(tempInv.indexOf(x));
                selectionMade.set(true);
                if (activePart.isEmpty()){
                    activePart.add(x);
                    data.setActivePart(activePart);
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("modifyPartPage.fxml"));
                        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 500,450);
                        stage.setTitle("Modify Part");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }else {
                    activePart.add(x);
                    activePart.remove(0);
                    data.setActivePart(activePart);
//                    data.setActiveTypeValue(x.getTypeValue(""));
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("modifyPartPage.fxml"));
                        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 500,450);
                        stage.setTitle("Modify Part");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        if(!selectionMade.get()){
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("No Selection");
            deleteAlert.setHeaderText("No Selection");
            deleteAlert.setContentText("Please select a part first");
            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == ButtonType.OK){
            }
        }
    }
    /**
     * Delete Part Button Function
     * <p>
     * This method runs when you click the Delete button on the parts list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionevent ActionEvent object
     * @return void
     */
    public void handlePartDelete(ActionEvent actionevent){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Parts");
        deleteAlert.setHeaderText("Delete");
        deleteAlert.setContentText("Do you want to delete this part?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK){
            ObservableList<Part> tempInv = inventory.getAllParts();
            tempInv.forEach((x) -> {
                if (x == partsTable.getSelectionModel().getSelectedItem()) {
                    inventory.deletePart(x);
                    partsTable.setItems(inventory.getAllParts());
                }
            }
            );
        }
        productsSectionWindowBtnsWrapper.getChildren().remove(productDeleteWithAssociatedExceptionText);
    }
    /**
     * Add Product Button Function
     * <p>
     * This method runs when you click the Add button on the product list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionevent ActionEvent object
     * @return void
     */
    public void handleProductAdd(ActionEvent actionevent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("addProductPage.fxml"));
        Stage stage = (Stage)((Node)actionevent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900,470);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Modify Product Button Function
     * <p>
     * This method runs when you click the Modify button on the product list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionevent ActionEvent object
     * @return void
     */
    public void handleProductModify(ActionEvent actionevent) throws IOException{
        AtomicBoolean selectionMade = new AtomicBoolean(false);
        ObservableList<Product> tempInv = inventory.getAllProducts();
        tempInv.forEach((x) -> {
            if (x == productsTable.getSelectionModel().getSelectedItem()) {
                selectionMade.set(true);
                data.setActiveProductIndex(tempInv.indexOf(x));
                data.clearPartsToCache();
                data.clearPartsToAssociate();
                data.setActiveProduct(x);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("modifyProductPage.fxml"));
                    Stage stage = (Stage)((Node)actionevent.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 900,470);
                    stage.setTitle("Modify Product");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }});
        if(!selectionMade.get()){
            Alert deleteAlert = new Alert(Alert.AlertType.ERROR);
            deleteAlert.setTitle("No Selection");
            deleteAlert.setHeaderText("No Selection");
            deleteAlert.setContentText("Please select a product first");
            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == ButtonType.OK){
            }
        }

    }
    /**
     * Delete Product Button Function
     * <p>
     * This method runs when you click the Delete button on the product list and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionevent ActionEvent object
     * @return void
     */
    public void handleProductDelete(ActionEvent actionevent){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Products");
        deleteAlert.setHeaderText("Delete");
        deleteAlert.setContentText("Do you want to delete this product?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        ObservableList<Product> tempInv = inventory.getAllProducts();
        if(result.get() == ButtonType.OK){
            tempInv.forEach((x) -> {
                if (x == productsTable.getSelectionModel().getSelectedItem()) {
                    if(x.getAllAssociatedParts().isEmpty()){
                        inventory.deleteProduct(x);
                        productsTable.setItems(inventory.getAllProducts());
                        productsSectionWindowBtnsWrapper.getChildren().remove(productDeleteWithAssociatedExceptionText);
                    }
                    else {
                        productsSectionWindowBtnsWrapper.getChildren().add(productDeleteWithAssociatedExceptionText);
                    }
                }
            }
            );
        }
    }
    /**
     * Exit Main Page Button Function
     * <p>
     * This method runs when you click the Exit button on the main page and returns nothing.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handleExitMainPage(ActionEvent actionEvent){
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Exiting Application");
        deleteAlert.setHeaderText("Exiting Application");
        deleteAlert.setContentText("Are you sure you want to exit?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        if(result.get() == ButtonType.OK){
            javafx.application.Platform.exit();
            System.out.println("Exiting Main Page");
        }
    }
    /**
     * Check if Numeric Function
     * <p>
     * This method receives a string and checks if the string value is numeric.
     *
     * @param  strNum String variable
     * @return boolean
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}