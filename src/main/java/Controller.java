import java.util.LinkedList;


public class Controller {
    private LinkedList<NetComponent> dataTable;

    public Controller(){
        dataTable = new LinkedList<NetComponent>();

    }

    public void start(){
        Server server = new Server(SystemVar.dispatcherPort, "localhost");
        //NodeIP, myIP (dispatcher), 7 (action type), nodePort

        server.checkMessage("127.0.0.1 \n 123.123.123.123 \n 7 \n 2000", "192.168.0.0");
        //server.startServer(dataTable);
    }



}
