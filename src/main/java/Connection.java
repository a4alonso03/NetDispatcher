import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private int port;
    private String host;
    private String serverMessage;

    protected ServerSocket serverSocket;
    protected Socket clientSocket;

    protected DataOutputStream serverOutStream;
    protected DataInputStream clientOutStream;


    public Connection(ConnectionType type, int port, String host){
        try {
            this.port = port;
            this.host = host;

            if (type == ConnectionType.SERVER) {
                serverSocket = new ServerSocket(this.port);
                clientSocket = new Socket();
            }
            else{
                clientSocket = new Socket(host, port);
            }


        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated on Connection class constructor");

        }
    }

   /* public void createSocket(ConnectionType type, int PORT, String HOST) throws IOException{
        this.port = port;
        this.host = host;

        if(type == ConnectionType.SERVER){
            serverSocket = new ServerSocket(this.port);
            clientSocket = new Socket();
        }
        else{
            clientSocket = new Socket(host, port);
        }
    }
*/
}
