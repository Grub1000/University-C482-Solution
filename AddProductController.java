package mypackage.managementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * Controller for the addProductPage.fxml file
 * @author Jorge Ramirez
 */
public class AddProductController implements Initializable {
    public Button addProductCancelButton;
    /**
     * Product Parts Search text field
     */
    public TextField addProductPartSearchTextField;
    /**
     * Product ID text field
     */
    public TextField productIDTextField;
    /**
     * Product Name text field
     */
    public TextField productNameTextField;
    /**
     * Product Inventory text field
     */
    public TextField productInvTextField;
    /**
     * Product Price text field
     */
    public TextField productPriceTextField;
    /**
     * Product Max text field
     */
    public TextField productMaxTextField;
    /**
     * Product Min text field
     */
    public TextField productMinTextField;
    /**
     * Product Part ID Table Column object
     */
    public TableColumn addProductaddPartsIdCol;
    /**
     * Product Part Name Table Column object
     */
    public TableColumn addProductaddPartsNameCol;
    /**
     * Product Part Inventory Table Column object
     */
    public TableColumn addProductaddPartsInventoryLvlCol;
    /**
     * Product Part Price Table Column object
     */
    public TableColumn addProductaddPartsPriceCostPerUnitCol;
    /**
     * Product add Part Button object
     */
    public Button addProductaddPartsBtn;
    /**
     * Product Associated Part ID Table Column object
     */
    public TableColumn addProductAssociatedPartsIdCol;
    /**
     * Product Associated Part Name Table Column object
     */
    public TableColumn addProductAssociatedPartsNameCol;
    /**
     * Product Associated Part Inventory Table Column object
     */
    public TableColumn addProductAssociatedPartsInventoryLvlCol;
    /**
     * Product Associated Part Price Table Column object
     */
    public TableColumn addProductAssociatedPartsPriceCostPerUnitcol;
    /**
     * Product Associated Part remove part Button object
     */
    public Button addProductRmvAssociatedPartsBtn;
    /**
     * Product Save Button object
     */
    public Button addProductSaveBtn;
    /**
     * Product Cancel Button object
     */
    public Button addProductCancelBtn;
    /**
     * Product Part Table object
     */
    public TableView addProductaddPartsTable;
    /**
     * Product Associated Part Table object
     */
    public TableView addProductAssociatedPartsTable;
    /**
     * Parent object of the exception text that is thrown during an exception check
     */
    public VBox productExceptionSection;
    Text productNameExceptionText = new Text("No data in name field");
    Text productInvExceptionText = new Text("Inventory is not an integer");
    Text productPriceExceptionText = new Text("Price is not a double");
    Text productMaxExceptionText = new Text("Max is not an integer");
    Text productMinExceptionText = new Text("Min is not an integer");
    Text productMinMaxExceptionText = new Text("Min should be lower than Max");
    Text productInvMinMaxExceptionText = new Text("Inv must be between Min and Max");
    Inventory inventory = DataSingleton.getInstance().getInventory();
    DataSingleton data = DataSingleton.getInstance();

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductaddPartsIdCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("id")
        );
        addProductaddPartsNameCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name")
        );
        addProductaddPartsInventoryLvlCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("stock")
        );
        addProductaddPartsPriceCostPerUnitCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );
        addProductaddPartsTable.setItems(inventory.getAllParts());

        addProductAssociatedPartsIdCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("id")
        );
        addProductAssociatedPartsNameCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name")
        );
        addProductAssociatedPartsInventoryLvlCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("stock")
        );
        addProductAssociatedPartsPriceCostPerUnitcol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );
        addProductAssociatedPartsTable.setItems(data.getPartsToAssociate());
    }
    /**
     * Modify Product Cancel Button Function
     * <p>
     * This method runs when you click the Cancel button on the prodcuts page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddProductCancel(ActionEvent actionEvent) throws Exception{
        data.clearPartsToAssociate();
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 354);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Add Part Button Function
     * <p>
     * This method runs when you click the Save button on the products page and returns nothing.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddProductaddParts(ActionEvent actionEvent) {
        ObservableList<Part> tempInv = inventory.getAllParts();
        ObservableList<Part> activePart = data.getActivePart();
        ObservableList<Part> partsToAssociate = data.getPartsToAssociate();
        tempInv.forEach((x) -> {
            if (x == addProductaddPartsTable.getSelectionModel().getSelectedItem()) {
                if (activePart.isEmpty()){
                    partsToAssociate.forEach((y) -> {
                        if(y.getId() == x.getId()){
                            data.setInList(true);
                        }
                    });
                    if(!data.getInList()){
                        partsToAssociate.add(x);
                        data.setPartsToAssociate(partsToAssociate);
                    }
                    data.setInList(false);
                }else {
                    activePart.add(x);
                    activePart.remove(0);
                    data.setActivePart(activePart);
                    partsToAssociate.forEach((y) -> {
                        if(y.getId() == x.getId()){
                            data.setInList(true);
                        }
                    });
                    if(!data.getInList()){
                        partsToAssociate.add(x);
                        data.setPartsToAssociate(partsToAssociate);
                    }
                    data.setInList(false);
                }
            }
        }
        );
    }
    /**
     * Remove Associated Part Button Function
     * <p>
     * This method runs when you click the Remove Associated Part button on the prodcuts page and returns nothing.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddProductRmvAssociatedParts(ActionEvent actionEvent) {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Products");
        deleteAlert.setHeaderText("Delete");
        deleteAlert.setContentText("Do you want to delete this associated part?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        ObservableList<Part> partsToAssociate = data.getPartsToAssociate();
        if(result.get() == ButtonType.OK){
            partsToAssociate.forEach((x) -> {
                if (x == addProductAssociatedPartsTable.getSelectionModel().getSelectedItem()) {
                    partsToAssociate.remove(x);
                    data.setPartsToAssociate(partsToAssociate);
                    addProductAssociatedPartsTable.setItems(partsToAssociate);
                }
            }
            );
        }
    }
    /**
     * Add Product Save Button Function
     * <p>
     * This method runs when you click the Save button on the prodcuts page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddProductSaveBtn(ActionEvent actionEvent) throws Exception{
        boolean readyToGo = true;
        if(productNameTextField.getText().isEmpty()){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productNameExceptionText)){
                productExceptionSection.getChildren().add(productNameExceptionText);
            }
        }else{
            productExceptionSection.getChildren().remove(productNameExceptionText);
        }

        if(!(isNumeric(productInvTextField.getText())) || productInvTextField.getText().contains(".")){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productInvExceptionText)){
                productExceptionSection.getChildren().add(productInvExceptionText);
            }
        }else{
            productExceptionSection.getChildren().remove(productInvExceptionText);
        }

        if(!(isNumeric(productPriceTextField.getText())) || !productPriceTextField.getText().contains(".")){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productPriceExceptionText)){
                productExceptionSection.getChildren().add(productPriceExceptionText);
            }
        }else{
            productExceptionSection.getChildren().remove(productPriceExceptionText);
        }

        if(!(isNumeric(productMaxTextField.getText())) || productMaxTextField.getText().contains(".")){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productMaxExceptionText)){
                productExceptionSection.getChildren().add(productMaxExceptionText);
            }
        }else{
            productExceptionSection.getChildren().remove(productMaxExceptionText);
        }

        if(!(isNumeric(productMinTextField.getText())) || productMinTextField.getText().contains(".")){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productMinExceptionText)){
                productExceptionSection.getChildren().add(productMinExceptionText);
            }
        }else{
            productExceptionSection.getChildren().remove(productMinExceptionText);
        }

        if((!productMinTextField.getText().isEmpty() && !productMaxTextField.getText().isEmpty()) && (Integer.parseInt(productMinTextField.getText()) > Integer.parseInt(productMaxTextField.getText()))){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productMinMaxExceptionText)){
                productExceptionSection.getChildren().add(productMinMaxExceptionText);
            }
        }else {
            productExceptionSection.getChildren().remove(productMinMaxExceptionText);
        }

        if((!productMinTextField.getText().isEmpty() && !productMaxTextField.getText().isEmpty() && !productInvTextField.getText().isEmpty()) && ((Integer.parseInt(productInvTextField.getText()) > Integer.parseInt(productMaxTextField.getText())) || (Integer.parseInt(productInvTextField.getText()) < Integer.parseInt(productMinTextField.getText()))) ){
            readyToGo = false;
            if(!productExceptionSection.getChildren().contains(productInvMinMaxExceptionText)){
                productExceptionSection.getChildren().add(productInvMinMaxExceptionText);
            }
        }else {
            productExceptionSection.getChildren().remove(productInvMinMaxExceptionText);
        }

        if(readyToGo){
            ObservableList<Part> partsToAssociate = data.getPartsToAssociate();
            Product newProduct = new Product(data.incrementAndGetIdentifierProduct(), productNameTextField.getText(),Double.parseDouble(productPriceTextField.getText()), Integer.parseInt(productInvTextField.getText()),Integer.parseInt(productMinTextField.getText()), Integer.parseInt(productMaxTextField.getText()));
            partsToAssociate.forEach((x) -> {
                newProduct.addAssociatedPart(x);
            });
            inventory.addProduct(newProduct);
            data.clearPartsToAssociate();
            Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 354);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Product Part Search Text Field Function
     * <p>
     * This method runs when you click enter on while on the search bar and returns nothing.
     * This method repopulates the Part Table with your desired search criteria.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleProductPartSearch(ActionEvent actionEvent) {
        ObservableList<Part> searchList = inventory.lookupPart(addProductPartSearchTextField.getText());
        try {
            if(!searchList.contains(inventory.lookupPart(Integer.parseInt(addProductPartSearchTextField.getText())))){
                if(inventory.lookupPart(Integer.parseInt(addProductPartSearchTextField.getText())) != null){
                    searchList.add(inventory.lookupPart(Integer.parseInt(addProductPartSearchTextField.getText())));
                }
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
        addProductaddPartsTable.setItems(searchList);
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
