package commands;

import java.net.Socket;

public class LoginCommand extends Command {

    public LoginCommand(Socket socket) {
        super(socket, 'L');
    }

    public Response login(String userName, String password) {
        this.addData(userName);
        this.addData(password);
        return sendFullCommand();
    }
}
