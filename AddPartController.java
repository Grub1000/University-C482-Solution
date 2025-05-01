package mypackage.managementsystem;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * Controller for the addPartPage.fxml file
 * @author Jorge Ramirez
 */
public class AddPartController implements Initializable {
    /**
     * In-House radio button object
     */
    public RadioButton inHouseRB;
    /**
     * Out-Sourced radio button object
     */
    public RadioButton outSourcedRB;
    /**
     * Part ID text field
     */
    public TextField partIDTextField;
    /**
     * Part Name text field
     */
    public TextField partNameTextField;
    /**
     * Part Inventory text field
     */
    public TextField partInvTextField;
    /**
     * Part Price text field
     */
    public TextField partPriceTextField;
    /**
     * Part Max text field
     */
    public TextField partMaxTextField;
    /**
     * Part Min text field
     */
    public TextField partMinTextField;
    /**
     * Part Machine ID text field
     */
    public TextField partMachineIDTextField;
    /**
     * Part Company Name text field
     */
    public TextField partCompanyNameTextField;
    /**
     * Part Save button
     */
    public Button addPartSaveButton;
    /**
     * Part Cancel button
     */
    public Button addPartCancelButton;
    /**
     * Part Machine ID text field Parent Object
     */
    public HBox partMachineIDTextFieldWrapper;
    /**
     * Parent Object of Company Name text field
     */
    public HBox partCompanyNameTextFieldWrapper;
    /**
     * Parent object of the text field wrappers
     */
    public VBox addPartMidSection;
    /**
     * Parent object of the exception text that is thrown during an exception check
     */
    public VBox addPartExceptionSection = new VBox();
    Text addPartNameExceptionText = new Text("No data in name field");
    Text addPartInvExceptionText = new Text("Inventory is not an integer");
    Text addPartPriceExceptionText = new Text("Price is not a double");
    Text addPartMaxExceptionText = new Text("Max is not an integer");
    Text addPartMinExceptionText = new Text("Min is not an integer");
    Text addPartMinMaxExceptionText = new Text("Min should be lower than Max");
    Text addPartInvMinMaxExceptionText = new Text("Inv must be between Min and Max");
    Text addPartMachineIDExceptionText = new Text("Machine ID required");
    Text addPartCompanyNameExceptionText = new Text("Company name required");
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
        ToggleGroup group = new ToggleGroup();
        inHouseRB.setToggleGroup(group);
        inHouseRB.setUserData(0);
        outSourcedRB.setToggleGroup(group);
        outSourcedRB.setUserData(1);
        data.setPartType(0);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (group.getSelectedToggle() != null){
                    final int tempVar = Integer.parseInt(group.getSelectedToggle().getUserData().toString());
                    if (tempVar == 0){
                        addPartMidSection.getChildren().remove(partCompanyNameTextFieldWrapper);
                        addPartMidSection.getChildren().add(partMachineIDTextFieldWrapper);
                    }
                    else {
                        addPartMidSection.getChildren().remove(partMachineIDTextFieldWrapper);
                        addPartMidSection.getChildren().add(partCompanyNameTextFieldWrapper);
                    }
                    data.setPartType(tempVar);
                }
            }
        });
        inHouseRB.setSelected(true);
    }
    /**
     * Add Part Cancel Button Function
     * <p>
     * This method runs when you click the Cancel button on the parts page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddPartCancel(ActionEvent actionEvent) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 354);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Add Part Save Button Function
     * <p>
     * This method runs when you click the Save button on the parts page and returns nothing.
     * This method throws and Exception if FXMLLoader.load() runs into an error.
     *
     * @param  actionEvent Event
     * @return void
     */
    public void handleAddPartSave(ActionEvent actionEvent) throws Exception{
        boolean readyToGo = true;
        if(partNameTextField.getText().isEmpty()){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartNameExceptionText)){
                addPartExceptionSection.getChildren().add(addPartNameExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartNameExceptionText);
        }

        if(!(isNumeric(partInvTextField.getText())) || partInvTextField.getText().contains(".")){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartInvExceptionText)){
                addPartExceptionSection.getChildren().add(addPartInvExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartInvExceptionText);
        }

        if(!(isNumeric(partPriceTextField.getText())) || !partPriceTextField.getText().contains(".")){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartPriceExceptionText)){
                addPartExceptionSection.getChildren().add(addPartPriceExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartPriceExceptionText);
        }

        if(!(isNumeric(partMaxTextField.getText())) || partMaxTextField.getText().contains(".")){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartMaxExceptionText)){
                addPartExceptionSection.getChildren().add(addPartMaxExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartMaxExceptionText);
        }

        if(!(isNumeric(partMinTextField.getText())) || partMinTextField.getText().contains(".")){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartMinExceptionText)){
                addPartExceptionSection.getChildren().add(addPartMinExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartMinExceptionText);
        }

        if((!partMinTextField.getText().isEmpty() && !partMaxTextField.getText().isEmpty()) && (Integer.parseInt(partMinTextField.getText()) > Integer.parseInt(partMaxTextField.getText()))){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartMinMaxExceptionText)){
                addPartExceptionSection.getChildren().add(addPartMinMaxExceptionText);
            }
        }else {
            addPartExceptionSection.getChildren().remove(addPartMinMaxExceptionText);
        }

        if((!partMinTextField.getText().isEmpty() && !partMaxTextField.getText().isEmpty() && !partInvTextField.getText().isEmpty()) && ((Integer.parseInt(partInvTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) || (Integer.parseInt(partInvTextField.getText()) < Integer.parseInt(partMinTextField.getText()))) ){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartInvMinMaxExceptionText)){
                addPartExceptionSection.getChildren().add(addPartInvMinMaxExceptionText);
            }
        }else {
            addPartExceptionSection.getChildren().remove(addPartInvMinMaxExceptionText);
        }

        if(!(isNumeric(partMachineIDTextField.getText())) && (data.getPartType() == 0) || partMachineIDTextField.getText().contains(".") && (data.getPartType() == 0)){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartMachineIDExceptionText)){
                addPartExceptionSection.getChildren().add(addPartMachineIDExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartMachineIDExceptionText);
        }

        if(partCompanyNameTextField.getText().isEmpty() && (data.getPartType() == 1)){
            readyToGo = false;
            if(!addPartExceptionSection.getChildren().contains(addPartCompanyNameExceptionText)){
                addPartExceptionSection.getChildren().add(addPartCompanyNameExceptionText);
            }
        }else{
            addPartExceptionSection.getChildren().remove(addPartCompanyNameExceptionText);
        }

        if(readyToGo){
            if(data.getPartType() == 0){
                inventory.addPart(new InHouse(data.incrementAndGetIdentifier(), partNameTextField.getText(), Double.parseDouble(partPriceTextField.getText()),Integer.parseInt( partInvTextField.getText()), Integer.parseInt(partMinTextField.getText()), Integer.parseInt(partMaxTextField.getText()), Integer.parseInt(partMachineIDTextField.getText())));
            }else{
                inventory.addPart(new OutSourced(data.incrementAndGetIdentifier(), partNameTextField.getText(), Double.parseDouble(partPriceTextField.getText()),Integer.parseInt( partInvTextField.getText()), Integer.parseInt(partMinTextField.getText()), Integer.parseInt(partMaxTextField.getText()), partCompanyNameTextField.getText()));
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
