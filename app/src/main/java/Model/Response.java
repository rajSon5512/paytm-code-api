package Model;

import org.json.JSONArray;
import org.json.JSONObject;

public class Response extends JSONObject {

  private String response;

    public Response(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }


}
