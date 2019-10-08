package Agent;
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

public class AgentController {
    DBHelper helper = new DBHelper();
    ObservableList<Agent> agents;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Agent> cbAgentId;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfMiddleInitial;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfBusinessPhone;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfPosition;

    @FXML
    private TextField tfAgencyId;

    @FXML
    private TextField tfPassword;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    void onActionBtnEdit(ActionEvent event) {
        btnEdit.setDisable(true);
        btnSave.setDisable(false);

        tfFirstName.setDisable(false);
        tfMiddleInitial.setDisable(false);
        tfLastName.setDisable(false);
        tfBusinessPhone.setDisable(false);
        tfEmail.setDisable(false);
        tfPosition.setDisable(false);
        tfAgencyId.setDisable(false);
        tfPassword.setDisable(false);
    }

    @FXML
    void onActionBtnSave(ActionEvent event) {
        btnEdit.setDisable(false);
        btnSave.setDisable(true);
        Update(); //Update the packages table
        tfFirstName.setDisable(true);
        tfMiddleInitial.setDisable(true);
        tfLastName.setDisable(true);
        tfBusinessPhone.setDisable(true);

        tfEmail.setDisable(true);
        tfPosition.setDisable(true);
        tfAgencyId.setDisable(true);
        tfPassword.setDisable(true);
    }

    public void Update()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "jesie", "password");

            String sql = "UPDATE agents set "
                    + "agtFirstName=?, "
                    + "agtMiddleInitial=?, agtLastName=?, "
                    + "agtBusPhone=?, agtEmail=?, agtPosition=?, "
                    + "agencyId=?, agtPassword=?"
                    + " WHERE agentId=?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfFirstName.getText());
            stmt.setString(2, tfMiddleInitial.getText());
            stmt.setString(3, tfLastName.getText());
            stmt.setString(4, tfBusinessPhone.getText());
            stmt.setString(5, tfEmail.getText());
            stmt.setString(6, tfPosition.getText());
            stmt.setString(7, tfAgencyId.getText());
            stmt.setString(8, tfPassword.getText());

            stmt.setString(9, String.valueOf(cbAgentId.getSelectionModel().selectedItemProperty().getValue()));
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
        assert cbAgentId != null : "fx:id=\"cbAgentId\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfFirstName != null : "fx:id=\"tfFirstName\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfMiddleInitial != null : "fx:id=\"tfMiddleInitial\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfLastName != null : "fx:id=\"tfLastName\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfBusinessPhone != null : "fx:id=\"tfBusinessPhone\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfPosition != null : "fx:id=\"tfPosition\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfAgencyId != null : "fx:id=\"tfAgencyId\" was not injected: check your FXML file 'agent.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'agent.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'agent.fxml'.";

        loadCombo();

        cbAgentId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observable, Agent oldValue, Agent newValue) {
                if (newValue != null) {
                    tfFirstName.setText(newValue.getAgtFirstName() + "");
                    tfMiddleInitial.setText(newValue.getAgtMiddleInitial() + "");
                    tfLastName.setText(newValue.getAgtLastName() + "");
                    tfBusinessPhone.setText(newValue.getAgtBusPhone() + "");
                    tfEmail.setText(newValue.getAgtEmail() + "");
                    tfPosition.setText(newValue.getAgtPosition() + "");
                    tfAgencyId.setText(newValue.getAgencyId()+ "");
                    tfPassword.setText(newValue.getPassword());
                }
            }
        });
    }

    private void loadCombo() throws SQLException {
        Connection conn = helper.createConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from agents");
        ArrayList<Agent> agentArrayList = new ArrayList<>();
        while (rs.next())
        {
            agentArrayList.add( new Agent(
                    rs.getInt(1),   rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(8)));
        }
        agents = FXCollections.observableArrayList(agentArrayList);
        cbAgentId.setItems(agents);
        conn.close();
    }
}
