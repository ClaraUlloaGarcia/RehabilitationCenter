package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Command {

    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private char command;
    private List<String> data = new ArrayList();

    private final int CODE_ERROR = 1;
    private final int CODE_OK = 0;

    Command(Socket socket, char command) {
        try {
            this.socket = socket;
            this.command = command;
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addData(String line) {
        data.add(line);
    }

    protected void sendSequence() {
        printWriter.append((char) 31);
        printWriter.append((char) 18);
        printWriter.append((char) 30);
        printWriter.append(command);
    }

    protected void sendLine(String line) {
        printWriter.append(line + "\n");
    }

    public Response sendFullCommand() {
        this.sendSequence();
        for (String line : data) {
            this.sendLine(line);
        }
        printWriter.flush();
        return receiveData();
    }

    protected Response receiveData() {
        try {
            int response = bufferedReader.read();

            if (response == CODE_ERROR) {
                return Response.createError();

            } else if (response == CODE_OK) {
                return Response.createSuccess();
            } else {
                String total = "";
                do {
                    total += (char) response;
                    response = bufferedReader.read();
                } while (response != '\n' && response != CODE_ERROR && response != CODE_OK);
                if (response == CODE_OK) {
                    return Response.createSuccess();
                } else if (response == CODE_ERROR) {
                    return Response.createError();
                }

                int totalData = Integer.parseInt(total);
                Response responseData = new Response();

                for (int i = 0; i < totalData; i++) {
                    String line = bufferedReader.readLine();
                    responseData.appendData(line);
                }
                response = bufferedReader.read();
                if (response == 0) {
                    responseData.setSuccess(true);
                } else {
                    responseData.setSuccess(false);
                }
                return responseData;
            }
        } catch (IOException ex) {
            Logger.getLogger(Command.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.createError();
    }
}
