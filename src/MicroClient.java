import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MicroClient {
    public static void main(String[] args) {
        new MicroClient();
    }

    public MicroClient() {
        try {// create connection to server, using IP address and port number:
            Socket socket = new Socket("localhost", 5555);
            System.out.println("Client connected to server: " + socket.getRemoteSocketAddress());
            // Use PrintWriter to to conveniently send text to server
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader buffServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader buffKeyboard = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                handleNetwork(buffServer);
                if (handleKeyboard(buffKeyboard, printWriter)){
                    break; // if the user entered "quit" then break
                }
                Thread.sleep(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleNetwork(BufferedReader buffServer) throws IOException {
        if(buffServer.ready()){ // the ready() does NOT block, and solves the problem
            // of checking both the network and the keyboard without an extra thread.
            System.out.println("from server: " + buffServer.readLine());
        }
    }

    private boolean handleKeyboard(BufferedReader buffKey, PrintWriter printWriter) throws IOException {
        if(buffKey.ready()){
            String keybMSg = buffKey.readLine();
            printWriter.println(keybMSg);
            if(keybMSg.equalsIgnoreCase("quit")){
                return true;
            }
        }
        return false;
    }
}
