import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;

public class Server extends Connection {
    private LinkedList<NetComponent> dataTable;

    public Server (int port, String host) {
        super(ConnectionType.SERVER, port, host);
        setDataTable(new LinkedList<NetComponent>());
    }

    public void startServer(LinkedList<NetComponent> dataTable){
        this.setDataTable(dataTable);
        try{
            while(true) {
                System.out.println("Server waiting...");
                clientSocket = serverSocket.accept();
                System.out.println("Server connected");
                clientOutStream = new DataInputStream(this.clientSocket.getInputStream());
                String connectionIP = clientSocket.getRemoteSocketAddress().toString().split(":")[0];
                //Get data
                String message = this.clientOutStream.readUTF();
                checkMessage(message, connectionIP);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Error originated from startServer method on server Class");
        }


    }

    //Llega un mensaje --> si es de tipo 1 significa que es un terminal, entonces ocupo guardar mis datos y que me devuelvan los datos de terminales

    public void checkMessage(String message, String connectionIP) {
        //NodeIP, myIP (dispatcher), 7 (action type), nodePort
        String[] splitMessage = message.split("\n");
         NetComponent comp = new NetComponent(splitMessage, connectionIP);
        dataTable.add(comp);
        StringBuilder stringBuilder = new StringBuilder();
        for (NetComponent aDataTable : dataTable) {
            stringBuilder.append(aDataTable.toString()).append('#');
        }

        System.out.println(stringBuilder.toString());
        //createResponseThread(stringBuilder.toString(), comp.getPort(), comp.getRealIp());
    }

        /**
        //realIP + fakeIP + port
        String[] splitMessage = message.split("\n");

        final NetComponent newData = new NetComponent(splitMessage);

        getDataTable().add(newData);
        System.out.println("Mensaje recibido: \n" + message);
            String messageToSend = "";
            //realIP + fakeIP + port
            for (int i = 0; i < getDataTable().size(); i++) {
                messageToSend += getDataTable().get(i).getRealIp() + ",";

            }


            //En el caso de que sea una terminal
            //Retornar todos los que son routers de mi red
            Thread thread = new Thread("Return values to terminal node") {
                public void run(){
                    System.out.println("Running thread: " + getName());

                    Client client = new Client(ConnectionType.CLIENT, newData.getPort(), newData.getRealIp());
                    client.sendToClient(newData.toString());
                }
            };
            thread.start();
         */



    public NetComponent parseFromDispatcher(String dispatcherInput){
        String[] netComponents = dispatcherInput.split("#");
        String[] aux;
        for (String netComponent : netComponents) {
            aux = netComponent.split(",");
            //Create item
            //realIP = aux[0];
            //fakeIP = aux[1];
            //port = Integer.parseInt(aux[2].trim());

        }

        return null;
    }




    public LinkedList<NetComponent> getDataTable() {
        return dataTable;
    }

    public void setDataTable(LinkedList<NetComponent> dataTable) {
        this.dataTable = dataTable;
    }

    private void createResponseThread(final String responseMessage, int port, final String realIP){
        final Integer targetPort = port;
        Thread thread = new Thread("Return values to terminal node") {
            public void run(){
                System.out.println("Running thread: " + getName());

                Client client = new Client(ConnectionType.CLIENT, targetPort, realIP);
                client.sendToClient(responseMessage);
            }
        };
        thread.start();
    }
}




