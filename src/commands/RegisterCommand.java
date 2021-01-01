package commands;

import java.net.Socket;

public class RegisterCommand extends Command {

    public RegisterCommand(Socket socket) {
        super(socket, 'R');
    }

    public Response register(String userName, String password, String gender, String age, String weight, String height) {
        this.addData(userName);
        this.addData(password);
        this.addData(gender);
        this.addData(age);
        this.addData(weight);
        this.addData(height);
        return sendFullCommand();
    }
}
