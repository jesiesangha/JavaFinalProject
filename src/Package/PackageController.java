package Package;

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

public class PackageController {
    DBHelper helper = new DBHelper();
    ObservableList<Package> packages;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Package> cbPackageId;

    @FXML
    private TextField tfPackageName;

    @FXML
    private TextField tfPackageStartDate;

    @FXML
    private TextField tfPackageEndDate;

    @FXML
    private TextField tfPackageDescription;

    @FXML
    private TextField tfPackageBasePrice;

    @FXML
    private TextField tfPackageAgencyComm;

    @FXML
    private TextField tfTripTypeId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;
    @FXML
    void onActionBtnSave(ActionEvent event) {
        btnEdit.setDisable(false);
        btnSave.setDisable(true);
        Update(); //Update the packages table
        tfPackageName.setDisable(true);
        tfPackageStartDate.setDisable(true);
        tfPackageEndDate.setDisable(true);
        tfPackageDescription.setDisable(true);

        tfPackageBasePrice.setDisable(true);
        tfPackageAgencyComm.setDisable(true);
        tfTripTypeId.setDisable(true);
    }

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        btnSave.setDisable(false);

        tfPackageName.setDisable(false);
        tfPackageStartDate.setDisable(false);
        tfPackageEndDate.setDisable(false);
        tfPackageDescription.setDisable(false);

        tfPackageBasePrice.setDisable(false);
        tfPackageAgencyComm.setDisable(false);
        tfTripTypeId.setDisable(false);
    }

    public void Update()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "jesie", "password");

            String sql = "UPDATE packages set "
                    + "pkgName=?, "
                    + "pkgStartDate=?, pkgEndDate=?, "
                    + "pkgDesc=?, pkgBasePrice=?, pkgAgencyCommission=?, "
                    + "tripTypeId=?"
                    + " WHERE packageId=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfPackageName.getText());
            stmt.setString(2, String.valueOf(Date.valueOf(tfPackageStartDate.getText())));
            stmt.setString(3, String.valueOf(Date.valueOf(tfPackageEndDate.getText())));
            stmt.setString(4, tfPackageDescription.getText());
            stmt.setString(5, String.valueOf(Float.parseFloat(tfPackageBasePrice.getText())));
            stmt.setString(6, String.valueOf(Float.parseFloat(tfPackageAgencyComm.getText())));
            stmt.setString(7, tfTripTypeId.getText());

            stmt.setString(8, String.valueOf(cbPackageId.getSelectionModel().selectedItemProperty().getValue()));
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
        assert cbPackageId != null : "fx:id=\"cbPackageId\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageName != null : "fx:id=\"tfPackageName\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageStartDate != null : "fx:id=\"tfPackageStartDate\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageEndDate != null : "fx:id=\"tfPackageEndDate\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageDescription != null : "fx:id=\"tfPackageDescription\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageBasePrice != null : "fx:id=\"tfPackageBasePrice\" was not injected: check your FXML file 'package.fxml'.";
        assert tfPackageAgencyComm != null : "fx:id=\"tfPackageAgencyComm\" was not injected: check your FXML file 'package.fxml'.";
        assert tfTripTypeId != null : "fx:id=\"tfTripTypeId\" was not injected: check your FXML file 'package.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'package.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'package.fxml'.";

        loadCombo();

        cbPackageId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observable, Package oldValue, Package newValue) {
                if (newValue != null) {
                    tfPackageName.setText(newValue.getPkgName() + "");
                    tfPackageStartDate.setText(newValue.getPkgStartDate() + "");
                    tfPackageEndDate.setText(newValue.getPkgEndDate() + "");
                    tfPackageDescription.setText(newValue.getPkgDesc() + "");
                    tfPackageBasePrice.setText(newValue.getPkgBasePrice() + "");
                    tfPackageAgencyComm.setText(newValue.getPkgAgencyCommission() + "");
                    tfTripTypeId.setText(newValue.getTripTypeId());
                }
            }
        });
    }

    private void loadCombo() throws SQLException {
        Connection conn = helper.createConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Packages");
        ArrayList<Package> packArrayList = new ArrayList<>();
        while (rs.next())
        {
            packArrayList.add( new Package(
                    rs.getInt(1),   rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5),
                    rs.getFloat(6), rs.getFloat(7), rs.getString(8)));
        }
        packages = FXCollections.observableArrayList(packArrayList);
        cbPackageId.setItems(packages);
        conn.close();
    }
}
