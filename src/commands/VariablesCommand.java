package commands;

import java.net.Socket;
import java.util.List;

public class VariablesCommand extends Command {

    public VariablesCommand(Socket socket) {
        super(socket, 'D');
    }

    public Response variableData(List<Integer> bitalino, String flex_ang, String turn_ang) {

        this.addData(bitalino.size() + ""); //Send to the server how much data from bitalino
        for (Integer bitalinoData : bitalino) {
            this.addData(bitalinoData.toString());
        }
        this.addData(flex_ang);
        this.addData(turn_ang);
        return sendFullCommand();
    }
}
