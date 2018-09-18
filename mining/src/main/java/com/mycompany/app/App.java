package com.mycompany.app;

import java.io.IOException;
import org.json.JSONObject;

/**
 * Mining App
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        CustomHttpClient customHttpClient = new CustomHttpClient();
        try {
            final String response =
                customHttpClient.post("http://headlight-tournament-3.herokuapp.com/register",
                                      "{ \"callsign\" : \"\"}");
            System.out.println("response=" + response);
            final JSONObject jsonObject = new JSONObject(response);
            final JSONObject status = (JSONObject)jsonObject.get("Status");
            final String id = status.get("Id").toString();
            System.out.println("id==" + id);
            final String
                response2 =
                customHttpClient.post("http://headlight-tournament-3.herokuapp.com/scan",
                                      "{ \"callsign\" : \"" + id + "\"}");
            final JSONObject jsonObject2 = new JSONObject(response2);
            System.out.println("scan===" + jsonObject2.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
