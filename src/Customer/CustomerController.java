package Customer;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerController {
    DBHelper helper = new DBHelper();
    ObservableList<Customer> customers;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Customer> cbCustomerId;

    @FXML
    private TextField tfFirstname;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfProvince;

    @FXML
    private TextField tfPostal;

    @FXML
    private TextField tfCountry;

    @FXML
    private TextField tfHomephone;

    @FXML
    private TextField tfBusPhone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfAgentId;

    @FXML
    private TextField tfUser;

    @FXML
    private TextField tfPass;


    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;
    @FXML
    void onActionBtnSave(ActionEvent event) {
        btnEdit.setDisable(false);
        btnSave.setDisable(true);
        Update(); //Update the customers table
        tfFirstname.setDisable(true);
        tfLastname.setDisable(true);
        tfAddress.setDisable(true);
        tfCity.setDisable(true);

        tfPostal.setDisable(true);
        tfProvince.setDisable(true);
        tfCountry.setDisable(true);
        tfHomephone.setDisable(true);

        tfBusPhone.setDisable(true);
        tfEmail.setDisable(true);
        tfAgentId.setDisable(true);
        tfUser.setDisable(true);
        tfPass.setDisable(true);
    }

    //Edit button allow to change the data in the text fields
    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        btnSave.setDisable(false);

        tfFirstname.setDisable(false);
        tfLastname.setDisable(false);
        tfAddress.setDisable(false);
        tfCity.setDisable(false);

        tfPostal.setDisable(false);
        tfProvince.setDisable(false);
        tfCountry.setDisable(false);
        tfHomephone.setDisable(false);

        tfBusPhone.setDisable(false);
        tfEmail.setDisable(false);
        tfAgentId.setDisable(false);
        tfUser.setDisable(false);
        tfPass.setDisable(false);
    }
    public void Update()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "jesie", "password");

            String sql = "UPDATE customers set "
                    + "custFirstName=?, "
                    + "custLastName=?, custAddress=?, "
                    + "custCity=?, custProv=?, custPostal=?, "
                    + "custCountry=?, custHomePhone=?, custBusPhone=?, "
                    + "custEmail=?, agentId=?, custUsername=?, "
                    + "custPassword=?"
                    + " WHERE customerId=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfFirstname.getText());
            stmt.setString(2, tfLastname.getText());
            stmt.setString(3, tfAddress.getText());
            stmt.setString(4, tfCity.getText());
            stmt.setString(5, tfProvince.getText());
            stmt.setString(6, tfPostal.getText());

            stmt.setString(7, tfCountry.getText());
            stmt.setString(8, tfHomephone.getText());
            stmt.setString(9, tfBusPhone.getText());
            stmt.setString(10, tfEmail.getText());
            stmt.setInt(11, Integer.parseInt(tfAgentId.getText()));
            stmt.setString(12, tfUser.getText());
            stmt.setString(13, tfPass.getText());

            stmt.setString(14, String.valueOf(cbCustomerId.getSelectionModel().selectedItemProperty().getValue()));
            int rows = stmt.executeUpdate();

            if (rows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Update failed, contact tech support", ButtonType.OK);
                alert.show();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Update successful", ButtonType.OK);
                alert.show();
                loadCombo();
            }
            conn.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    @FXML
    void initialize() throws SQLException {
        assert cbCustomerId != null : "fx:id=\"cbCustomerId\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfFirstname != null : "fx:id=\"tfFirstname\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfLastname != null : "fx:id=\"tfLastname\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfCity != null : "fx:id=\"tfCity\" was not injected: check your FXML file 'customer.fxml'.";

        assert tfProvince != null : "fx:id=\"tfProvince\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfPostal != null : "fx:id=\"tfPostal\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfCountry != null : "fx:id=\"tfCountry\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfHomephone != null : "fx:id=\"tfHomephone\" was not injected: check your FXML file 'customer.fxml'.";

        assert tfBusPhone != null : "fx:id=\"tfBusPhone\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfAgentId != null : "fx:id=\"tfAgentId\" was not injected: check your FXML file 'customer.fxml'.";
        assert tfUser != null : "fx:id=\"tfUser\" was not injected: check your FXML file 'customer.fxml'.";

        assert tfPass != null : "fx:id=\"tfPass\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'customer.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'customer.fxml'.";

        loadCombo();

        cbCustomerId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {
            @Override
            public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
                if (newValue != null) {
                   // cbCustomerId.setText(newValue.getCustomerId() + "");
                    tfFirstname.setText(newValue.getCustFirstName() + "");
                    tfLastname.setText(newValue.getCustLastName() + "");
                    tfAddress.setText(newValue.getCustAddress() + "");
                    tfCity.setText(newValue.getCustCity() + "");

                    tfPostal.setText(newValue.getCustPostal() + "");
                    tfProvince.setText(newValue.getCustProv() + "");
                    tfCountry.setText(newValue.getCustCountry() + "");
                    tfHomephone.setText(newValue.getCustHomePhone() + "");

                    tfBusPhone.setText(newValue.getCustBusPhone()+ "");
                    tfEmail.setText(newValue.getCustEmail()+ "");
                    tfAgentId.setText(newValue.getAgentId() + "");
                    tfUser.setText(newValue.getCustUsername() + "");
                    tfPass.setText(newValue.getCustPassword());
                }
            }
        });
    }

    private void loadCombo() throws SQLException {
        Connection conn = helper.createConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Customers");
        ArrayList<Customer> custArrayList = new ArrayList<>();
        while (rs.next())
        {
            custArrayList.add( new Customer(
                    rs.getInt(1),   rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),
                    rs.getString(11), rs.getInt(12), rs.getString(13),  rs.getString(14) ));
        }
        customers = FXCollections.observableArrayList(custArrayList);
        cbCustomerId.setItems(customers);
        conn.close();
    }
}
