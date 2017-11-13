import java.io.DataOutputStream;
import java.io.IOException;

public class Client extends Connection {


    public Client(ConnectionType type, int port, String host){
        super(ConnectionType.CLIENT, port, host);
        try {
            this.serverOutStream = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from Client constructor");
        }
    }

    public void sendToClient(String input){
        System.out.println("El mensaje a enviar es: " +input);
        try {
            serverOutStream.writeUTF(input);
            //serverSocket.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
