
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;


public class Connection implements Runnable{
    private Socket connectionSocket;
    
    public Connection(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    @Override
    public void run() {
        try {
            handle();
        } catch (IOException ex) {
        }
    }

    public void startThread(){
        Thread thread = new Thread(this);
        thread.start();
    }
    
    
    private void handle() throws IOException{ 
	
	try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            out.print("==================================================================\n"
                    + "===========  Welcome to Stock Exchange - Auction Server ==========\n"
                    + "==================================================================\n\n"
                    + "Enter 'quit' and press enter any time to quit bidding.\n"
                    + "Please Enter Your Name : ");                
            out.flush();    
            String name = in.readLine();
            if(name.equals("quit"))
                connectionSocket.close();
            
            out.print("\nOK "+name+", Please Enter the symbol of the security you want to bid : " + "\n");                
            out.flush(); 
            
            String symbol;
            for(symbol = in.readLine(); !StockDB.isExist(symbol) ; symbol = in.readLine()){
                if(symbol.equals("quit"))
                     connectionSocket.close();
                out.print("\n-1 , Symbol is invalid.Try again\n");                
                out.flush();
            }
            
            StockItem item = StockDB.getStockItem(symbol);
            out.print("\nPlease Wait.......\n");                
            out.flush();
            synchronized(item){
                double currentPrice = item.getPrice();
                out.print("\nYes "+name+", The CURRENT PRICE of the security is : " + currentPrice + "\nPlease enter your price to bid : ");                
                out.flush();
                String price="0";
                int excessBytesDuringWait = connectionSocket.getInputStream().available();
                if(excessBytesDuringWait > 0){
                    connectionSocket.getInputStream().skip(excessBytesDuringWait);
                    System.out.println(excessBytesDuringWait);
                } 
                try{
                    for(price = in.readLine(); !price.equals("quit") && Double.parseDouble(price) <= currentPrice ; price = in.readLine()){
                        out.print("\nError: Hi "+name+", The price you entered must be more than the current price of the security. Note that the current price of " +symbol+ " is "+currentPrice+"\nPlease re-enter your price to bid : ");                
                        out.flush();
                    }
                    if(price.equals("quit"))
                        connectionSocket.close();
                }catch(NumberFormatException e){
                    out.print("\nError You entered an invalid value for price. Exiting the Auction server ....Try again");                
                    out.flush();
                    connectionSocket.close();
                }
                


                out.print("\nOk "+name+",Your price accepted. Please enter 'confirm' and press enter to comfim bidding. Or enter 'quit' and press enter to quit bidding.\n");                
                out.flush();

                for(String confirm = in.readLine(); !confirm.equals("confirm") ; confirm = in.readLine()){
                    if(confirm.equals("quit"))
                        connectionSocket.close();
                    out.print("\nError input: Hi "+name+", Enter 'confirm' and press enter to comfim bidding. Or enter 'quit' and press enter to quit bidding.\n");                
                    out.flush();
                }

                   //saving data in OfferDB
                Offer offer =new Offer(name, symbol,new Date(), Double.parseDouble(price));
                OfferDB.addOffer(offer);
                StockDB.setPrice(symbol, Double.parseDouble(price));
                
                out.print("\nCongratulations "+name+",Your bid saved successfully. Thank you for using Stock Exchange - Auction Server.\n");                
                out.flush();
            }
            connectionSocket.close();
                       
        } catch (IOException iOException) {
            this.connectionSocket.close();
        }
    }
    
    
}
