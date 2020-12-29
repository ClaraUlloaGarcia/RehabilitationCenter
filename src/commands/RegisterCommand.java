/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.net.Socket;

/**
 *
 * @author ClaraU
 */
public class RegisterCommand extends Command{
    
    public RegisterCommand(Socket socket) {
        super(socket, 'R');
    }
    
    public Response register (String userName, String password, String gender, String age, String weight, String height){
        this.addData(userName);
        this.addData(password);
        this.addData(gender);
        this.addData(age);
        this.addData(weight);
        this.addData(height);
        return sendFullCommand();
    }
    
}
