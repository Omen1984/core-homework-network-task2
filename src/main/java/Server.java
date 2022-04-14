import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        System.out.println("server started");
        int port = 8934;

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("Enter your name");
            final String name = in.readLine();

            out.println("Are you child? (yes/no)");
            final String isChild = in.readLine();

            switch (isChild.toLowerCase()) {
                case "yes":
                    out.println(String.format("Welcome to the kids area, %s! Let's play!\n", name));
                case "no":
                    out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!\n",
                            name));
                default:
                    out.println(String.format("Dear %s, mocking is not good, " +
                            "think about your behavior and come again, the connection is broken\n", name));
            }

            System.out.printf("New connection accepted:\n%s: %s:%d\n", name, clientSocket.getInetAddress(), clientSocket.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}