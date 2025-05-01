package mypackage.managementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * First Class Called
 * @author Jorge Ramirez
 */
public class Application extends javafx.application.Application
{
    /**
     * JavaFX Start Method
     * <p>
     * This method starts the entire application by loading the Main Page at app startup.
     *
     * @param  stage javaFX Stage object
     * @return void
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setResizable(false);
        stage.setScene(new Scene(root, 900, 354));
        stage.show();
    }
    /**
     * Main Method - The JavaDocs for this project are under the attached JavaDocs.zip folder on my task submission.
     * <p>
     *  The main method lauches the javafx project using launch();
     *
     *  JavaDocs - The JavaDocs are attached in my submission as JavaDocs.zip. Run the index.html to see full JavaDocs.
     *
     * @param  args String[] object
     * @return void
     */
    public static void main(String[] args) {
        launch();
    }
}