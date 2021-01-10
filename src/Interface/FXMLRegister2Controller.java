package Interface;

import Patient.ServerConnection;
import commands.RegisterCommand;
import commands.Response;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLRegister2Controller implements Initializable {

    @FXML
    TextField nameRegister;

    @FXML
    TextField passwordRegister;

    @FXML
    TextField genderRegister;

    @FXML
    TextField ageRegister;

    @FXML
    TextField weightRegister;

    @FXML
    TextField heightRegister;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void register(ActionEvent event) {
        String name = nameRegister.getText();
        String password = passwordRegister.getText();
        String gender = genderRegister.getText();
        String age = ageRegister.getText();
        String weight = weightRegister.getText();
        String height = heightRegister.getText();
        Socket serverSocket = ServerConnection.getInstance().getSocket();
        if (serverSocket == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Could not connect to server.");
            alert.show();
            return;
        }
        Response response = new RegisterCommand(serverSocket).register(name, password, gender, age, weight, height);

        if (response.isSuccess()) {
            try {
                Class clas = getClass();
                URL resourceURL = clas.getResource("FXMLVariables3.fxml");
                Parent root = FXMLLoader.load(resourceURL);
                Scene scene = new Scene(root);

                Stage stage = (Stage) nameRegister.getScene().getWindow();
                stage.setScene(scene);

            } catch (IOException ex) {
                Logger.getLogger(FXMLLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Fail to register.");
            alert.show();
        }
    }
}
