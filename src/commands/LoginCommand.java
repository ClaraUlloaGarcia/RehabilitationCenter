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
public class LoginCommand extends Command{
    
    public LoginCommand(Socket socket) {
        super(socket, 'L');
    }
    
    public Response login (String userName, String password){
        this.addData(userName);
        this.addData(password);
        return sendFullCommand();
    }
    
}
