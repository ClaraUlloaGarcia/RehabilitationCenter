package commands;

import java.util.ArrayList;
import java.util.List;

public class Response {

    private boolean success;
    private List<String> data;

    public Response() {
        success = false;
        data = null;
    }

    public static Response createSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public static Response createError() {
        Response response = new Response();
        response.setSuccess(false);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getData() {
        return data;
    }

    public void appendData(String line) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
        this.data.add(line);
    }
}
