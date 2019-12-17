import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

// Github: https://bit.ly/36Ic6o5
public class MicroServer {
    private Map<Socket,PrintWriter> sockets = new HashMap<>();
    public static void main(String[] args) {
        new MicroServer();
    }

    public MicroServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);
            while (true) {
                // open connection from clients:
                Socket socket = serverSocket.accept(); // accept() blocks until there is a conn.
                System.out.println("connected!");
                //clients.add(socket);
                sockets.put(socket, new PrintWriter(socket.getOutputStream(),true));
                ClientHandler ch = new ClientHandler(socket,this);
                Thread thread = new Thread(ch);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(Socket socket){
        sockets.remove(socket);
        System.out.println("removed socket: " + socket.getRemoteSocketAddress());
    }

    public void broadCast(String receivedMsg, Socket socket) {
        for(Map.Entry<Socket, PrintWriter> s: sockets.entrySet()){
            if(!s.equals(socket)){
                s.getValue().println(receivedMsg);
            }
        }
    }
}
