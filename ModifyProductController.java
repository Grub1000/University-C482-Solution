package mypackage.managementsystem;

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
 * Controller for the modifyProductPage.fxml file
 * @author Jorge Ramirez
 */
public class ModifyProductController implements Initializable {
    /**
     * Modify Product Search text field
     */
    public TextField modifyProductPartSearchTextField;
    public TextField modifyProductIDTextFieldWrapper;
    /**
     * Modify Product Name text field
     */
    public TextField modifyProductNameTextField;
    /**
     * Modify Product Inventory text field
     */
    public TextField modifyProductInvTextField;
    /**
     * Modify Product Price text field
     */
    public TextField modifyProductPriceTextField;
    /**
     * Modify Product Max text field
     */
    public TextField modifyProductMaxTextField;
    /**
     * Modify Product Min text field
     */
    public TextField modifyProductMinTextField;
    /**
     * Modify Product Part ID Table Column object
     */
    public TableColumn modifyProductaddPartsIdCol;
    /**
     * Modify Product Part Name Table Column object
     */
    public TableColumn modifyProductaddPartsNameCol;
    /**
     * Modify Product Part Inventory Table Column object
     */
    public TableColumn modifyProductaddPartsInventoryLvlCol;
    /**
     * Modify Product Part Price Table Column object
     */
    public TableColumn modifyProductaddPartsPriceCostPerUnitcol;
    /**
     * Modify Product Add Associated Part Button object
     */
    public Button modifyProductaddPartsBtn;
    /**
     * Modify Product Associated Part ID Table Column object
     */
    public TableColumn modifyProductAssociatedPartsIdCol;
    /**
     * Modify Product Associated Part Name Table Column object
     */
    public TableColumn modifyProductAssociatedPartsNameCol;
    /**
     * Modify Product Associated Part Inventory Table Column object
     */
    public TableColumn modifyProductAssociatedPartsInventoryLvlCol;
    /**
     * Modify Product Associated Part Price Table Column object
     */
    public TableColumn modifyProductAssociatedPartsPriceCostPerUnitcol;
    /**
     * Modify Product Remove Associated Part Button object
     */
    public Button modifyProductRmvAssociatedPartBtn;
    /**
     * Modify Product Save Button object
     */
    public Button modifyProductSaveBtn;
    /**
     * Modify Product Cancel Button object
     */
    public Button modifyProductCancelBtn;
    /**
     * Modify Product Part Table object
     */
    public TableView modifyProductaddPartsTable;
    /**
     * Modify Product ID Text Field
     */
    public TextField modifyProductIDTextField;
    /**
     * Modify Product Associated Part Table object
     */
    public TableView modifyProductAssociatedPartsTable;
    /**
     * Modify Product Exception Text Wrapper
     */
    public VBox modifyProductExceptionSection;
    Text modifyProductNameExceptionText = new Text("No data in name field");
    Text modifyProductInvExceptionText = new Text("Inventory is not an integer");
    Text modifyProductPriceExceptionText = new Text("Price is not a double");
    Text modifyProductMaxExceptionText = new Text("Max is not an integer");
    Text modifyProductMinExceptionText = new Text("Min is not an integer");
    Text modifyProductMinMaxExceptionText = new Text("Min should be lower than Max");
    Text modifyProductInvMinMaxExceptionText = new Text("Inv must be between Min and Max");
    Inventory inventory = DataSingleton.getInstance().getInventory();
    DataSingleton data = DataSingleton.getInstance();
    /**
     * Runs before all other methods
     * <p>
     * This method always runs at when we create an instance of this class and
     * returns nothing.
     * @param  url  an absolute URL giving the base location of the image
     * @param  resourceBundle Resource Bundle.
     * @return void
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modifyProductaddPartsIdCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("id")
        );
        modifyProductaddPartsNameCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name")
        );
        modifyProductaddPartsInventoryLvlCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("stock")
        );
        modifyProductaddPartsPriceCostPerUnitcol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );
        modifyProductaddPartsTable.setItems(inventory.getAllParts());

        modifyProductAssociatedPartsIdCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("id")
        );
        modifyProductAssociatedPartsNameCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("name")
        );
        modifyProductAssociatedPartsInventoryLvlCol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("stock")
        );
        modifyProductAssociatedPartsPriceCostPerUnitcol.setCellValueFactory(
                new PropertyValueFactory<Part, String>("price")
        );
        modifyProductAssociatedPartsTable.setItems(data.getPartsToAssociate());

        Product activeProduct = data.getActiveProduct();
        modifyProductIDTextField.setPromptText(String.valueOf(activeProduct.getId()));
        modifyProductNameTextField.setText(activeProduct.getName());
        modifyProductInvTextField.setText(String.valueOf(activeProduct.getStock()));
        modifyProductPriceTextField.setText(String.valueOf(activeProduct.getPrice()));
        modifyProductMaxTextField.setText(String.valueOf(activeProduct.getMax()));
        modifyProductMinTextField.setText(String.valueOf(activeProduct.getMin()));
        activeProduct.getAllAssociatedParts().forEach((y) -> {
            modifyProductAssociatedPartsTable.getItems().add(y);
        });
    }
    /**
     * Modify Product Cancel Button Function
     * <p>
     * This method runs when you click the Cancel button on the modify product page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handleModifyProductCancel(ActionEvent actionEvent) throws Exception{
        data.clearPartsToAssociate();
        data.clearPartsToCache();
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 354);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Modify Product Part Search Text Field Function
     * <p>
     * This method runs when you click enter on while on the search bar and returns nothing.
     * This method repopulates the Part Table with your desired search criteria.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleModifyProductPartSearch(ActionEvent actionEvent){
        ObservableList<Part> searchList = inventory.lookupPart(modifyProductPartSearchTextField.getText());
        try {
            if(!searchList.contains(inventory.lookupPart(Integer.parseInt(modifyProductPartSearchTextField.getText())))){
                if(inventory.lookupPart(Integer.parseInt(modifyProductPartSearchTextField.getText())) != null){
                    searchList.add(inventory.lookupPart(Integer.parseInt(modifyProductPartSearchTextField.getText())));
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
        modifyProductaddPartsTable.setItems(searchList);
    }
    /**
     * Add Part Button Function
     * <p>
     * This method runs when you click the Save button on the modify products page and returns nothing.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleModifyProductaddParts(ActionEvent actionEvent) {
        ObservableList<Part> tempInv = inventory.getAllParts();
        ObservableList<Part> activePart = data.getActivePart();
        ObservableList<Part> partsToAssociate = data.getPartsToAssociate();
        tempInv.forEach((x) -> {
            if (x == modifyProductaddPartsTable.getSelectionModel().getSelectedItem()) {
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
//                            System.out.println(this.partsToAssociate.getLast());
            }
        }
        );
    }
    /**
     * Remove Associated Part Button Function
     * <p>
     * This method runs when you click the Remove Associated Part button on the modify prodcuts page and returns nothing.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleModifyProductRmvAssociatedPart(ActionEvent actionEvent) {
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
        deleteAlert.setTitle("Products");
        deleteAlert.setHeaderText("Delete");
        deleteAlert.setContentText("Do you want to delete this associated part?");
        Optional<ButtonType> result = deleteAlert.showAndWait();
        ObservableList<Part> partsToAssociate = data.getPartsToAssociate();
        ObservableList<Part> partsToCache = data.getPartsToCache();
        if(result.get() == ButtonType.OK){
            partsToAssociate.forEach((x) -> {
                if (x == modifyProductAssociatedPartsTable.getSelectionModel().getSelectedItem()) {
                    partsToCache.add(x);
                    data.setPartsToCache(partsToCache);
                    partsToAssociate.remove(x);
                    data.setPartsToAssociate(partsToAssociate);
                    modifyProductAssociatedPartsTable.setItems(partsToAssociate);
                }
            }
            );
        }
    }
    /**
     * Modify Product Save Button Function
     * <p>
     * This method runs when you click the Save button on the modify prodcuts page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleModifyProductSave(ActionEvent actionEvent) throws Exception{
        boolean readyToGo = true;
        if(modifyProductNameTextField.getText().isEmpty()){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductNameExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductNameExceptionText);
            }
        }else{
            modifyProductExceptionSection.getChildren().remove(modifyProductNameExceptionText);
        }

        if(!(isNumeric(modifyProductInvTextField.getText())) || modifyProductInvTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductInvExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductInvExceptionText);
            }
        }else{
            modifyProductExceptionSection.getChildren().remove(modifyProductInvExceptionText);
        }

        if(!(isNumeric(modifyProductPriceTextField.getText())) || !modifyProductPriceTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductPriceExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductPriceExceptionText);
            }
        }else{
            modifyProductExceptionSection.getChildren().remove(modifyProductPriceExceptionText);
        }

        if(!(isNumeric(modifyProductMaxTextField.getText())) || modifyProductMaxTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductMaxExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductMaxExceptionText);
            }
        }else{
            modifyProductExceptionSection.getChildren().remove(modifyProductMaxExceptionText);
        }

        if(!(isNumeric(modifyProductMinTextField.getText())) || modifyProductMinTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductMinExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductMinExceptionText);
            }
        }else{
            modifyProductExceptionSection.getChildren().remove(modifyProductMinExceptionText);
        }

        if((!modifyProductMinTextField.getText().isEmpty() && !modifyProductMaxTextField.getText().isEmpty()) && (Integer.parseInt(modifyProductMinTextField.getText()) > Integer.parseInt(modifyProductMaxTextField.getText()))){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductMinMaxExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductMinMaxExceptionText);
            }
        }else {
            modifyProductExceptionSection.getChildren().remove(modifyProductMinMaxExceptionText);
        }

        if((!modifyProductMinTextField.getText().isEmpty() && !modifyProductMaxTextField.getText().isEmpty() && !modifyProductInvTextField.getText().isEmpty()) && ((Integer.parseInt(modifyProductInvTextField.getText()) > Integer.parseInt(modifyProductMaxTextField.getText())) || (Integer.parseInt(modifyProductInvTextField.getText()) < Integer.parseInt(modifyProductMinTextField.getText()))) ){
            readyToGo = false;
            if(!modifyProductExceptionSection.getChildren().contains(modifyProductInvMinMaxExceptionText)){
                modifyProductExceptionSection.getChildren().add(modifyProductInvMinMaxExceptionText);
            }
        }else {
            modifyProductExceptionSection.getChildren().remove(modifyProductInvMinMaxExceptionText);
        }
        if(readyToGo){
            Product activeProduct = new Product(data.getActiveProduct().getId(), "", 0.0, 0, 0, 0);
            activeProduct.setName(modifyProductNameTextField.getText());
            activeProduct.setPrice(Double.parseDouble(modifyProductPriceTextField.getText()));
            activeProduct.setStock(Integer.parseInt(modifyProductInvTextField.getText()));
            activeProduct.setMax(Integer.parseInt(modifyProductMaxTextField.getText()));
            activeProduct.setMin(Integer.parseInt(modifyProductMinTextField.getText()));
//            Product activeProduct = new Product(activeProduct.getId(), activeProduct.getName(), activeProduct.getPrice(), activeProduct.getStock(), activeProduct.getMin(), activeProduct.getMax());
            data.getPartsToAssociate().forEach((x) -> {
                activeProduct.deleteAssociatedPart(x);
                activeProduct.addAssociatedPart(x);
            });
            data.getPartsToCache().forEach((x) -> {
                activeProduct.deleteAssociatedPart(x);
            });
//            data.setActiveProduct(activeProduct);
            data.clearPartsToCache();
            Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 354);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();


//            Product activeProduct = data.getActiveProduct();
//            inventory.updateProduct(data.getActiveProductIndex(), new Product(activeProduct.getId(), activeProduct.getName(), activeProduct.getPrice(), activeProduct.getStock(), activeProduct.getMin(), activeProduct.getMax()));
            inventory.updateProduct(data.getActiveProductIndex(), activeProduct);
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
