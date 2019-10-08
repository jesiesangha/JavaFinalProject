package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnModifyCustomer;

    @FXML
    private Button btnModifyAgent;

    @FXML
    private Button btnModifyPackage;

    @FXML
    void onActionbtnModifyAgent(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Agent/agent.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Edit Agent Information");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onActionbtnModifyPackage(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Package/package.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Edit Package Information");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onActionbtnModifyCustomer(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../Customer/customer.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Edit Customer Information");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        assert btnModifyCustomer != null : "fx:id=\"btnModifyCustomer\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnModifyPackage != null : "fx:id=\"btnModifyPackage\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
