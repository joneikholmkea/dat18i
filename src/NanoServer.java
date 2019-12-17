import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
// Github: https://bit.ly/36Ic6o5
public class NanoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            // open connection from clients:
            Socket socket = serverSocket.accept(); // accept() blocks until there is a conn.
                System.out.println("connected!");
                // create a "normal" scanner, to read network data
                Scanner scanner = new Scanner(socket.getInputStream());
                // read a line, just as when you read from a file or console
            while (true) { // move this line !!
                String receivedMsg = scanner.nextLine(); // blocks !!
                System.out.println(receivedMsg);
                if(receivedMsg.equalsIgnoreCase("quit")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
