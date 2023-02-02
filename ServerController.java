
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hishan Indrajith
 */
public class ServerController extends Thread{
    private static ServerScreen gui;
    public ServerController() {
        gui = new ServerScreen();
        gui.setVisible(true);
        gui.pack(); 
	gui.setLocationRelativeTo(null); 
        gui.setResizable(false);
        gui.getChangePriceBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePriceBtnAction();
            }
        });
        gui.getListStockBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listStockBtnAction();
            }
        });
        
    }
    
    
    private void changePriceBtnAction(){
        ChangePriceController con = new ChangePriceController();
    }
    
    private void listStockBtnAction(){
        ListStockController con = new ListStockController();
    }
    
    private void loop(){
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.getFb().setText(""+StockDB.getStockItem("FB").getPrice());
            gui.getVrtu().setText(""+StockDB.getStockItem("VRTU").getPrice());
            gui.getMsft().setText(""+StockDB.getStockItem("MSFT").getPrice());
            gui.getGoogl().setText(""+StockDB.getStockItem("GOOGL").getPrice());
            gui.getYhoo().setText(""+StockDB.getStockItem("YHOO").getPrice());
            gui.getXlnx().setText(""+StockDB.getStockItem("XLNX").getPrice());
            gui.getTsla().setText(""+StockDB.getStockItem("TSLA").getPrice());
            gui.getTxn().setText(""+StockDB.getStockItem("TXN").getPrice());
            //gui.getOffertable().
        }
    }
        
    public static void addDetailsToTable(Offer offerToAdd){
        DefaultTableModel dtm = (DefaultTableModel)gui.getOffertable().getModel();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        String date=dateFormat.format(offerToAdd.getDate());
        String time=timeFormat.format(offerToAdd.getDate());
        Object newRow []={offerToAdd.getClientName(),offerToAdd.getSymbol(),date,time,offerToAdd.getPrice()};
        dtm.addRow(newRow);
    }
    

    @Override
    public void run() {
        loop();
    }
    
    
    
}
