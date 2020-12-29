/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.net.Socket;
import java.util.List;

/**
 *
 * @author ClaraU
 */
public class VariablesCommand extends Command{
    
    public VariablesCommand(Socket socket) {
        super(socket, 'D');
    }
    
    public Response variableData (List<Integer> bitalino, String flex_ang, String turn_ang ){

        this.addData(bitalino.size() + "");
        for (Integer bitalinoData : bitalino){
            this.addData(bitalinoData.toString());
        }
        this.addData(flex_ang);
        this.addData(turn_ang);
        return sendFullCommand();
    }
    
}
