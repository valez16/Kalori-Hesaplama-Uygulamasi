

package com.kalori.hesapla.networking;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class HttpRequest {

    public enum Method {
        GET, POST, PUT, DELETE
    }

    private String url;
    private Method method;
    private String requestBody;
    private String contentType;


    public HttpRequest(String url) {
        this.url = url;
        method = Method.GET;
    }


    public HttpRequest url(String url) {
        this.url = url;
        return this;
    }


    public HttpRequest method(Method method) {
        this.method = method;
        return this;
    }


    public HttpRequest body(String requestBody) {
        if(contentType == null)
            contentType = "text/plain";
        this.requestBody = requestBody;
        return this;
    }

    public HttpRequest contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }


    public HttpResponse perform() throws IOException {

        if(method == Method.GET && requestBody != null)
            throw new IllegalStateException("No request body for GET request.");

        HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
        connection.setRequestMethod(method.toString());

        if((method == Method.POST || method == Method.PUT) && requestBody != null) {
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);
            PrintWriter writer = new PrintWriter(connection.getOutputStream());
            writer.print(requestBody);
            writer.close();
        }

        HttpResponse response = new HttpResponse();

        response.setResponseCode(connection.getResponseCode());

        Map<String, List<String>> headers = connection.getHeaderFields();
        response.setHeaders(headers);
        int contentLength = 100;
        if(headers.containsKey("Content-Length"))
            contentLength = Integer.parseInt(headers.get("Content-Length").get(0));

        try {
            StringBuilder builder = new StringBuilder(contentLength);

            Scanner scanner;
            if(connection.getResponseCode() >= 400)
                scanner = new Scanner(connection.getErrorStream());
            else
                scanner = new Scanner(connection.getInputStream());

            while (scanner.hasNext()) {
                builder.append(scanner.nextLine() + '\n');
            }
            scanner.close();

            response.setResponseBody(builder.toString());
        }
        catch (FileNotFoundException e) {
            response.setResponseBody("");
        }

        return response;
    }

}
