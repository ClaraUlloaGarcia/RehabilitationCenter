package Interface;

import Patient.ServerConnection;
import java.net.URL;
import java.util.Optional;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

public class RehabilitationProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Class clas = getClass();
        URL resourceURL = clas.getResource("FXMLLogin1.fxml");
        Parent root = FXMLLoader.load(resourceURL);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        TextInputDialog ipDialog = new TextInputDialog("Please introduce Server IP");
        Optional<String> result = ipDialog.showAndWait();
        
        if(result.isPresent()) {
            final String IP = ipDialog.getEditor().getText();
            ServerConnection.getInstance().setIP(IP);
            stage.show();
        } else {
            System.exit(0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
}
