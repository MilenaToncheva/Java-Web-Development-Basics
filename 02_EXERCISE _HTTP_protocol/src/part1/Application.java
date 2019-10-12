package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {
    private static BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        List<String> validUrls = getValidUrls();
        List<String> requestLines = getRequest();
        String protocol = getProtocol(requestLines.get(0));
        String method = getMethod(requestLines.get(0));
        String url = getUrl(requestLines.get(0));

        Map<String, String> headers = getHeaders(requestLines);
        Map<String, String> body = getBody(requestLines);


        String status = getResponseStatus(validUrls, url, headers, method, body);
        System.out.println(getResponse(protocol, status, headers, body));

    }

    private static String getResponse(String protocol, String status, Map<String, String> headers, Map<String, String> body) {
        sb.append(protocol).append(" ").append(status).append(System.lineSeparator());
        for (Map.Entry<String, String> kvp : headers.entrySet()) {
            if (kvp.getKey().equals("Date") ||
                    kvp.getKey().equals("Host") ||
                    kvp.getKey().equals("Content-type")) {
                sb.append(kvp.getKey()).append(": ").append(kvp.getValue());
                sb.append(System.lineSeparator());
            }
        }
        sb.append(System.lineSeparator());
        switch (status) {
            case "401 Unauthorized":
                sb.append("You are not authorized to access the requested functionality.");
                break;
            case "404 Not Found":
                sb.append("The requested functionality was not found.");
                break;
            case "400 Bad Request":

                sb.append("There was an error with the requested functionality due to malformed request.");
                break;
            default:            //200 OK
                sb.append(System.lineSeparator());

                byte[] decodedBytes = Base64.getDecoder().decode(headers.get("Authorization").split("\\s+")[1]);

                String username = new String(decodedBytes);
                sb.append("Greetings ").append(username).append("! You have successfully created ").append(body.get("name"))
                        .append(" with quantity - ").append(body.get("quantity")).append(", price - ").append(body.get("price")).append(".");
                break;
        }
        return sb.toString().trim();


    }

    public static String getResponseStatus(List<String> validUrls, String url, Map<String, String> headers, String method, Map<String, String> body) {
        if (!validUrls.contains(url)) {
            return "404 Not Found";
        }
        if (headers.get("Authorization") == null) {
            return "401 Unauthorized";
        }
        if (method.equals("POST") && body.isEmpty()) {
            return "400 Bad Request";
        }

        return "200 OK";
    }


    private static String getProtocol(String request) {
        return request.split("\\s+")[2];
    }


    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(br.readLine().split("\\s+"));
    }

    private static List<String> getRequest() throws IOException {
        String line = null;
        List<String> request = new ArrayList<String>();
        while ((line = br.readLine()) != null && line.length() > 0) {
            request.add(line);
        }
        request.add(System.lineSeparator());
        if ((line = br.readLine()) != null && line.length() > 0) {
            request.add(line);
        }
        return request;
    }

    private static String getMethod(String requestLine) {

        return requestLine.split("\\s+")[0];
    }

    private static String getUrl(String requestLine) {
        return requestLine.split("\\s+")[1];
    }

    private static Map<String, String> getHeaders(List<String> request) {
        Map<String, String> headers = new LinkedHashMap<>();
        request.stream()
                .skip(1)
                .filter(h -> h.contains(": "))
                .map(h -> h.split(": "))
                .forEach(header -> {
                    headers.put(header[0], header[1]);
                });

        return headers;
    }

    private static Map<String, String> getBody(List<String> requestLines) {
        Map<String, String> bodyParams = new LinkedHashMap<>();
        if (requestLines.get(requestLines.size() - 1).equals(System.lineSeparator())) {
            return bodyParams;
        }
        Arrays.stream(requestLines.get(requestLines.size() - 1)
                .split("&"))
                .map(bodyElem -> bodyElem.split("="))
                .forEach(beKvp -> {
                    bodyParams.put(beKvp[0], beKvp[1].trim());
                });
        return bodyParams;
    }
}
