import javax.management.relation.RoleInfo;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Server extends Connection {
    private LinkedList<RouterInfo> dataTable;

    public Server (int port, String host) {
        super(ConnectionType.SERVER, port, host);
    }

    public void startServer(LinkedList<RouterInfo> dataTable){
        this.dataTable = dataTable;
        try{
            while(true) {
                System.out.println("Server waiting...");
                clientSocket = serverSocket.accept();
                System.out.println("Server connected");
                clientOutStream = new DataInputStream(this.clientSocket.getInputStream());
                String message = this.clientOutStream.readUTF();
                checkMessage(message);
                //System.out.println("Mensaje recibido: " + message);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from startServer method on server Class");
        }


    }

    //Llega un mensaje --> si es de tipo 1 significa que es un terminal, entonces ocupo guardar mis datos y que me devuelvan los datos de terminales

    private void checkMessage(String message){
        //type + realIP + fakeIP + port
        String[] splitMessage = message.split("\n");

        final RouterInfo newData = new RouterInfo(splitMessage, checkIfTerminal(splitMessage[0]));

        dataTable.add(newData);
        System.out.println("Mensaje recibido: \n" + message);


        if(splitMessage[0].equals("1")){
            String messageToSend = "";
            //realIP + fakeIP + port
            for (int i = 0; i < dataTable.size(); i++) {
                messageToSend += dataTable.get(i).getRouterRealIp() + ",";

            }


            //En el caso de que sea una terminal
            //Retornar todos los que son routers de mi red
            Thread thread = new Thread("Return values to terminal node") {
                public void run(){
                    System.out.println("Running thread: " + getName());

                    Client client = new Client(ConnectionType.CLIENT, newData.getRouterPort(), newData.getRouterRealIp());
                    client.sendToClient(newData.toString());
                }
            };
            thread.start();

        }
        else{
            //Caso de router
            //retornar las cosas de mi red
        }

    }

    public boolean checkIfTerminal(String input){
        return input.equals("1");
    }
}
