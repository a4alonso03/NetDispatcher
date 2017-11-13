import java.util.LinkedList;


public class Controller {
    private LinkedList<RouterInfo> dataTable;

    public Controller(){
        dataTable = new LinkedList<RouterInfo>();

    }

    public void start(){
        Server server = new Server(SystemVar.dispatcherPort, "localhost");
        server.startServer(dataTable);
    }
}
