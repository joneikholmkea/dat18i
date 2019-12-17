import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NanoClient {
    public static void main(String[] args) {
        try {
            // create connection to server, using IP address and port number:
            Socket socket = new Socket("localhost", 5555);
            // Use PrintWriter to to conveniently send text to server
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            // Send text:
            Scanner scanner = new Scanner(System.in);
            System.out.print("Type your message: ");
            String message = scanner.nextLine();
            printWriter.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
