package com.mycompany.app;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CustomHttpClient {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  final OkHttpClient client = new OkHttpClient();

  public String post(String url, String json) throws IOException {
    final RequestBody body = RequestBody.create(JSON, json);
    final Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    final Response response = client.newCall(request).execute();
    return response.body().string();
  }



}
