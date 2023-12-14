import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        // Create an HTTP server that listens on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Create a context for the "/hello" path and set the handler
        server.createContext("/hello", new HelloHandler());

        // Set the executor (null means default executor)
        server.setExecutor(null);

        // Start the server
        server.start();

        System.out.println("Server is running on port 8080. Try accessing http://localhost:8080/hello");
    }

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Send a response to the client
            String response = "Hello, this is the response from the server!";
            exchange.sendResponseHeaders(200, response.length());
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }
}
