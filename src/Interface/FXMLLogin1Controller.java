/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Patient.ServerConnection;
import commands.LoginCommand;
import commands.Response;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

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
    private void handleButtonAction(ActionEvent event) {
        String user = userTextField.getText();
        String password = passwordTextField.getText();
        Socket serverSocket = ServerConnection.getInstance().getSocket();
        Response response = new LoginCommand (serverSocket).login(user, password);
         
        if(response.isSuccess()) {
            System.out.println("OK");
        }
        else{
            System.out.println("ERROR");
            System.exit(1);
        }
        
    }
   // @FXML
    /*private void ok(ActionEvent event) {
        System.out.println("funciono");
    }*/
    
    @FXML
    private void login(MouseEvent  event) {
        String userName = userTextField.getText();
        String password = passwordTextField.getText();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
