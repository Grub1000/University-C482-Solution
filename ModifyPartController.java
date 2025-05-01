package mypackage.managementsystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * Controller for the modifyPartPage.fxml file
 * @author Jorge Ramirez
 */

public class ModifyPartController implements Initializable {
    /**
     * Modify In-House radio button object
     */
    public RadioButton modifyInHouseRB;
    /**
     * Modify Out-Sourced radio button object
     */
    public RadioButton modifyOutSourcedRB;
    /**
     * Modify Part ID text field
     */
    public TextField modifyPartIDTextField;
    /**
     * Modify Part Name text field
     */
    public TextField modifyPartNameTextField;
    /**
     * Modify Part Inventory text field
     */
    public TextField modifyPartInvTextField;
    /**
     * Modify Part Price text field
     */
    public TextField modifyPartPriceTextField;
    /**
     * Modify Part Max text field
     */
    public TextField modifyPartMaxTextField;
    /**
     * Modify Part Min text field
     */
    public TextField modifyPartMinTextField;
    /**
     * Modify Part Machine ID text field
     */
    public TextField modifyPartMachineIDTextField;
    /**
     * Modify Part Company Name text field
     */
    public TextField modifyPartCompanyNameTextField;
    /**
     * Modify Part Save Button
     */
    public Button modifyPartSaveButton;
    /**
     * Modify Part Cancel Button
     */
    public Button modifyPartCancelButton;
    /**
     * Modify Part Machine ID Text Field object Wrapper
     */
    public HBox modifyPartMachineIDTextFieldWrapper;
    /**
     * Modify Part Company Name Text Field object Wrapper
     */
    public HBox modifyPartCompanyNameTextFieldWrapper;
    /**
     * Modify Part Middle Section Text Fields Wrapper
     */
    public VBox modifyPartMidSection;
    /**
     * Modify Part Exception Text Wrapper
     */
    public VBox modifyPartExceptionSection;

    Text modifyPartNameExceptionText = new Text("No data in name field");
    Text modifyPartInvExceptionText = new Text("Inventory is not an integer");
    Text modifyPartPriceExceptionText = new Text("Price is not a double");
    Text modifyPartMaxExceptionText = new Text("Max is not an integer");
    Text modifyPartMinExceptionText = new Text("Min is not an integer");
    Text modifyPartMinMaxExceptionText = new Text("Min should be lower than Max");
    Text modifyPartInvMinMaxExceptionText = new Text("Inv must be between Min and Max");
    Text modifyPartMachineIDExceptionText = new Text("Machine ID required");
    Text modifyPartCompanyNameExceptionText = new Text("Company name required");
    private DataSingleton data = DataSingleton.getInstance();
    private Inventory inventory = DataSingleton.getInstance().getInventory();
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
        ObservableList<Part> activePart = data.getActivePart();
        if(activePart.get(0) instanceof InHouse){
            if(data.getPartType() == 0){
                modifyPartMidSection.getChildren().remove(modifyPartCompanyNameTextFieldWrapper);
                modifyPartIDTextField.setPromptText(String.valueOf(activePart.get(0).getId()));
                modifyPartNameTextField.setText(activePart.get(0).getName());
                modifyPartInvTextField.setText(String.valueOf(activePart.get(0).getStock()));
                modifyPartPriceTextField.setText(String.valueOf(activePart.get(0).getPrice()));
                modifyPartMaxTextField.setText(String.valueOf(activePart.get(0).getMax()));
                modifyPartMinTextField.setText(String.valueOf(activePart.get(0).getMin()));
                modifyPartMachineIDTextField.setText(String.valueOf(((InHouse)activePart.get(0)).getMachineId()));
                modifyInHouseRB.setSelected(true);
            }else{
                data.setPartType(0);
                modifyPartMidSection.getChildren().remove(modifyPartCompanyNameTextFieldWrapper);
                modifyPartIDTextField.setPromptText(String.valueOf(activePart.get(0).getId()));
                modifyPartNameTextField.setText(activePart.get(0).getName());
                modifyPartInvTextField.setText(String.valueOf(activePart.get(0).getStock()));
                modifyPartPriceTextField.setText(String.valueOf(activePart.get(0).getPrice()));
                modifyPartMaxTextField.setText(String.valueOf(activePart.get(0).getMax()));
                modifyPartMinTextField.setText(String.valueOf(activePart.get(0).getMin()));
                modifyPartMachineIDTextField.setText(String.valueOf(((InHouse)activePart.get(0)).getMachineId()));
                modifyInHouseRB.setSelected(true);
            }
        }else {
            if(data.getPartType() == 1){
                modifyPartMidSection.getChildren().remove(modifyPartMachineIDTextFieldWrapper);
                modifyPartIDTextField.setPromptText(String.valueOf(activePart.get(0).getId()));
                modifyPartNameTextField.setText(activePart.get(0).getName());
                modifyPartInvTextField.setText(String.valueOf(activePart.get(0).getStock()));
                modifyPartPriceTextField.setText(String.valueOf(activePart.get(0).getPrice()));
                modifyPartMaxTextField.setText(String.valueOf(activePart.get(0).getMax()));
                modifyPartMinTextField.setText(String.valueOf(activePart.get(0).getMin()));
                modifyPartCompanyNameTextField.setText(String.valueOf(((OutSourced)activePart.get(0)).getCompanyName()));
                modifyOutSourcedRB.setSelected(true);
            }else{
                data.setPartType(1);
                modifyPartMidSection.getChildren().remove(modifyPartMachineIDTextFieldWrapper);
                modifyPartIDTextField.setPromptText(String.valueOf(activePart.get(0).getId()));
                modifyPartNameTextField.setText(activePart.get(0).getName());
                modifyPartInvTextField.setText(String.valueOf(activePart.get(0).getStock()));
                modifyPartPriceTextField.setText(String.valueOf(activePart.get(0).getPrice()));
                modifyPartMaxTextField.setText(String.valueOf(activePart.get(0).getMax()));
                modifyPartMinTextField.setText(String.valueOf(activePart.get(0).getMin()));
                modifyPartCompanyNameTextField.setText(String.valueOf(((OutSourced)activePart.get(0)).getCompanyName()));
                modifyOutSourcedRB.setSelected(true);
            }
        }
        ToggleGroup modifyGroup = new ToggleGroup();
        modifyInHouseRB.setToggleGroup(modifyGroup);
        modifyInHouseRB.setUserData(0);
        modifyOutSourcedRB.setToggleGroup(modifyGroup);
        modifyOutSourcedRB.setUserData(1);

        modifyGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (modifyGroup.getSelectedToggle() != null){
                    final int tempVar = Integer.parseInt(modifyGroup.getSelectedToggle().getUserData().toString());
                    if (tempVar == 0){
                        modifyPartMidSection.getChildren().remove(modifyPartCompanyNameTextFieldWrapper);
                        modifyPartMidSection.getChildren().add(modifyPartMachineIDTextFieldWrapper);
                    }
                    else {
                        modifyPartMidSection.getChildren().remove(modifyPartMachineIDTextFieldWrapper);
                        modifyPartMidSection.getChildren().add(modifyPartCompanyNameTextFieldWrapper);
                        if(activePart.get(0) instanceof OutSourced){
                            TextField modifyPartCompanyNameTextField = new TextField(((OutSourced)activePart.get(0)).getCompanyName());
                        }
//                        TextField modifyPartCompanyNameTextField = new TextField(((OutSourced)activePart.get(0)).getCompanyName());
                        modifyPartCompanyNameTextField.setStyle("-fx-translate-x: 70px; -fx-font: 12px arial; -fx-max-width: 105px");
                    }
                    data.setPartType(tempVar);
                }
            }
        });
    }

    /**
     * Modify Part Cancel Button Function
     * <p>
     * This method runs when you click the Cancel button on the modify part page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handleModifyPartCancel(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 354);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Modify Part Save Button Function
     * <p>
     * This method runs when you click the Save button on the modify part page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent ActionEvent object
     * @return void
     */
    public void handleModifyPartSave(ActionEvent actionEvent) throws Exception{
        boolean readyToGo = true;
        if(modifyPartNameTextField.getText().isEmpty()){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartNameExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartNameExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartNameExceptionText);
        }

        if(!(isNumeric(modifyPartInvTextField.getText())) || modifyPartInvTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartInvExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartInvExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartInvExceptionText);
        }

        if(!(isNumeric(modifyPartPriceTextField.getText())) || !modifyPartPriceTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartPriceExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartPriceExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartPriceExceptionText);
        }

        if(!(isNumeric(modifyPartMaxTextField.getText())) || modifyPartMaxTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartMaxExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartMaxExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartMaxExceptionText);
        }

        if(!(isNumeric(modifyPartMinTextField.getText())) || modifyPartMinTextField.getText().contains(".")){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartMinExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartMinExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartMinExceptionText);
        }

        if((!modifyPartMinTextField.getText().isEmpty() && !modifyPartMaxTextField.getText().isEmpty()) && (Integer.parseInt(modifyPartMinTextField.getText()) > Integer.parseInt(modifyPartMaxTextField.getText()))){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartMinMaxExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartMinMaxExceptionText);
            }
        }else {
            modifyPartExceptionSection.getChildren().remove(modifyPartMinMaxExceptionText);
        }

        if((!modifyPartMinTextField.getText().isEmpty() && !modifyPartMaxTextField.getText().isEmpty() && !modifyPartInvTextField.getText().isEmpty()) && ((Integer.parseInt(modifyPartInvTextField.getText()) > Integer.parseInt(modifyPartMaxTextField.getText())) || (Integer.parseInt(modifyPartInvTextField.getText()) < Integer.parseInt(modifyPartMinTextField.getText()))) ){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartInvMinMaxExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartInvMinMaxExceptionText);
            }
        }else {
            modifyPartExceptionSection.getChildren().remove(modifyPartInvMinMaxExceptionText);
        }

        if(!(isNumeric(modifyPartMachineIDTextField.getText())) && (data.getPartType() == 0) || modifyPartMachineIDTextField.getText().contains(".") && (data.getPartType() == 0)){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartMachineIDExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartMachineIDExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartMachineIDExceptionText);
        }

        if(modifyPartCompanyNameTextField.getText().isEmpty() && (data.getPartType() == 1)){
            readyToGo = false;
            if(!modifyPartExceptionSection.getChildren().contains(modifyPartCompanyNameExceptionText)){
                modifyPartExceptionSection.getChildren().add(modifyPartCompanyNameExceptionText);
            }
        }else{
            modifyPartExceptionSection.getChildren().remove(modifyPartCompanyNameExceptionText);
        }

        if(readyToGo){
            ObservableList<Part> activePart = data.getActivePart();
            if(data.getPartType() == 0){
                if( data.getPartType() == 0 && activePart.get(0) instanceof InHouse){
                    activePart.get(0).setName(modifyPartNameTextField.getText());
                    activePart.get(0).setStock(Integer.parseInt(modifyPartInvTextField.getText()));
                    activePart.get(0).setPrice(Double.parseDouble(modifyPartPriceTextField.getText()));
                    activePart.get(0).setMax(Integer.parseInt(modifyPartMaxTextField.getText()));
                    activePart.get(0).setMin(Integer.parseInt(modifyPartMinTextField.getText()));
//                    activePart.get(0).setTypeValue(modifyPartMachineIDTextField.getText());
                    ((InHouse) activePart.get(0)).setMachineId(Integer.parseInt(modifyPartMachineIDTextField.getText()));
                }else {
                    inventory.updatePart(data.getActivePartIndex(), new InHouse(activePart.get(0).getId(),modifyPartNameTextField.getText(),Double.parseDouble(modifyPartPriceTextField.getText()),Integer.parseInt(modifyPartInvTextField.getText()), Integer.parseInt(modifyPartMinTextField.getText()), Integer.parseInt(modifyPartMaxTextField.getText()),Integer.parseInt(modifyPartMachineIDTextField.getText())));
//                    inventory.addPart(new InHouse(activePart.get(0).getId(),modifyPartNameTextField.getText(),Double.parseDouble(modifyPartPriceTextField.getText()),Integer.parseInt(modifyPartInvTextField.getText()), Integer.parseInt(modifyPartMinTextField.getText()), Integer.parseInt(modifyPartMaxTextField.getText()),Integer.parseInt(modifyPartMachineIDTextField.getText())));
//                    inventory.deletePart(activePart.get(0));
                    modifyPartCompanyNameTextField.clear();
                }
            }else{
                if( activePart.get(0) instanceof OutSourced){
                    activePart.get(0).setName(modifyPartNameTextField.getText());
                    activePart.get(0).setStock(Integer.parseInt(modifyPartInvTextField.getText()));
                    activePart.get(0).setPrice(Double.parseDouble(modifyPartPriceTextField.getText()));
                    activePart.get(0).setMax(Integer.parseInt(modifyPartMaxTextField.getText()));
                    activePart.get(0).setMin(Integer.parseInt(modifyPartMinTextField.getText()));
//                    activePart.get(0).setTypeValue(modifyPartCompanyNameTextField.getText());
                    ((OutSourced) activePart.get(0)).setCompanyName(modifyPartCompanyNameTextField.getText());
                }else {
                    inventory.updatePart(data.getActivePartIndex(),new OutSourced(activePart.get(0).getId(),modifyPartNameTextField.getText(),Double.parseDouble(modifyPartPriceTextField.getText()),Integer.parseInt(modifyPartInvTextField.getText()),Integer.parseInt(modifyPartMinTextField.getText()), Integer.parseInt(modifyPartMaxTextField.getText()), modifyPartCompanyNameTextField.getText() ));
//                    inventory.addPart(new OutSourced(activePart.get(0).getId(),modifyPartNameTextField.getText(),Double.parseDouble(modifyPartPriceTextField.getText()),Integer.parseInt(modifyPartInvTextField.getText()),Integer.parseInt(modifyPartMinTextField.getText()), Integer.parseInt(modifyPartMaxTextField.getText()), modifyPartCompanyNameTextField.getText() ));
//                    inventory.deletePart(activePart.get(0));
                    modifyPartMachineIDTextField.clear();
                }
            }
            Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 900, 354);
            stage.setTitle("Inventory Management System");
            stage.setScene(scene);
            stage.show();
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
