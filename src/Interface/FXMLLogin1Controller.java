/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Patient.ServerConnection;
import commands.LoginCommand;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author ClaraU
 */
public class FXMLLogin1Controller implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private TextField userTextField;
    
    @FXML
    private TextField passwordTextField;
    
    @FXML
    private void handleButtonAction(ActionEvent event) { //Botón Aceptar en la ventana Login
        String user = userTextField.getText();
        String password = passwordTextField.getText();
        Socket serverSocket = ServerConnection.getInstance().getSocket();
        if(serverSocket == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Could not connect to server."); 
            alert.show(); 
            return;
        }
        Response response = new LoginCommand (serverSocket).login(user, password);
         
        if(response.isSuccess()) {
             try {
            //Botón Nuevo Paciente de la ventana Login
            Class clas = getClass();
            URL resourceURL = clas.getResource("FXMLVariables3.fxml");
            Parent root = FXMLLoader.load(resourceURL);
            Scene scene = new Scene(root);

            Stage stage = (Stage) userTextField.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(FXMLLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Fail to login."); 
            alert.show(); 
            
        }
        
    }
    
    @FXML
    private void openRegistrationScene(ActionEvent event) {
        try {
            //Botón Nuevo Paciente de la ventana Login
            Class clas = getClass();
            URL resourceURL = clas.getResource("FXMLRegister2.fxml");
            Parent root = FXMLLoader.load(resourceURL);
            Scene scene = new Scene(root);

            Stage stage = (Stage) userTextField.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            Logger.getLogger(FXMLLogin1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
