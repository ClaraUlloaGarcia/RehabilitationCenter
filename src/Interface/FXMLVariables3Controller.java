package Interface;

import BITalino.BITalino;
import BITalino.BITalinoException;
import BITalino.Frame;
import Patient.ServerConnection;
import commands.Response;
import commands.VariablesCommand;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class FXMLVariables3Controller implements Initializable {
    
    @FXML
    TextField MACVariable;
    
    @FXML
    TextField bendingVariable;
    
    @FXML
    TextField turningVariable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void update(ActionEvent event) {
        String macAddress = MACVariable.getText();
        String bendingAddress = bendingVariable.getText();
        String turningAddress = turningVariable.getText();
        List<Integer> bitalinoData = getBitalinoData(macAddress);
        if(bitalinoData == null || bitalinoData.isEmpty()) {
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Fail to read from bitalino."); 
            alert.show(); 
            return;
        }
        
        Socket serverSocket = ServerConnection.getInstance().getSocket();
        if(serverSocket == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Could not connect to server."); 
            alert.show(); 
            return;
        }
        Response response = new VariablesCommand(serverSocket).variableData(bitalinoData, bendingAddress, turningAddress);
         
        if(response.isSuccess()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Data sent."); 
            alert.show();
        }
        else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Fail to send data."); 
            alert.show(); 
        }
    }
    
    List<Integer> getBitalinoData(String macAddress) {
        BITalino bitalino = null;
        List<Integer> bitalinoData = new ArrayList<>();
        try {
            bitalino = new BITalino();
            int SamplingRate = 100;
            bitalino.open(macAddress, SamplingRate);

            //Start acquisition on analog channel A1 {0}, which is electromyogram (EMG)
            int[] channelsToAcquire = {0}; 
            bitalino.start(channelsToAcquire);

            //Read in total 500 times
            for (int j = 0; j < 50; j++) {
                System.out.println("J: " + j);
                //Each time read a block of 10 samples 
                int block_size=10;
                Frame[] frames = bitalino.read(block_size);
                System.out.println("Total frames: "+ frames.length);
                //Print the samples
                for (Frame frame: frames) {
                    bitalinoData.add(frame.analog[0]); //we are adding the EMG to the arrayList
                }
            }
            //stop acquisition      
            bitalino.stop();
            bitalino.close();
            return bitalinoData;
        } catch (BITalinoException ex) {
            Logger.getLogger(FXMLVariables3Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(FXMLVariables3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
