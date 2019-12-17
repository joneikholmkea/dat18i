import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {

    Socket socket = null;
    MicroServer microServer = null;

    public ClientHandler(Socket socket, MicroServer microServer) {
        this.socket = socket;
        this.microServer = microServer;
    }

    @Override
    public void run() {
        // create a "normal" scanner, to read network data
        Scanner scanner = null;
        try {
            scanner = new Scanner(socket.getInputStream());
            // read a line, just as when you read from a file or console
            System.out.println("Client handler ready.");
            while (true) {
                if(scanner.hasNextLine()) {
                    String receivedMsg = scanner.nextLine(); // blocks !!
                    microServer.broadCast(receivedMsg, socket);
                    if (receivedMsg.equalsIgnoreCase("quit")) {
                        microServer.removeClient(socket);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
