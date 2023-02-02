
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Hishan Indrajith
 */
public class AuctionServer {
    
    public static final int BASE_PORT = 2000;
    
    public static void main(String [] args){
        //ServerController controller = new ServerController();
        StockDB stockDataStruct = new StockDB();
        OfferDB offerDatastruct = new OfferDB();
        ServerController guiController = new ServerController();
        Thread guiThread = new Thread(guiController);
        guiThread.start();
        AuctionServer server = new AuctionServer();
        try {
            server.serverLoop();
        } catch (IOException ex) {
        }
    }
    
    public void serverLoop() throws IOException { 
        ServerSocket serverSocket = new ServerSocket(BASE_PORT); 
	while(true) { 
	    Socket socket = serverSocket.accept(); // if error must close the socket
            Connection newConnection = new Connection(socket);
            newConnection.startThread();
	}
    }
    
    
    
}
