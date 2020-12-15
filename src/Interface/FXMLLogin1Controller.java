/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

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
        System.out.println("You clicked me!");
        label.setText("Hello World!");
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
