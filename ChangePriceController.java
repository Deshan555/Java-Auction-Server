
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;


public class ChangePriceController {
    private ChangePriceScreen screen;
    
    public ChangePriceController() {
        screen = new ChangePriceScreen();
        screen.setVisible(true);
        screen.setResizable(false);
        screen.setSize(817,438);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.getSearchbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBtnAction();
            }
        });
        screen.getSubmitbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitBtnAction();
            }
        });
    }
    
    private void searchBtnAction(){
       String symbol = screen.getSearchtxt().getText();
       screen.getSymbolTxt().setText(symbol);
       if(!StockDB.isExist(symbol)){
           screen.getNameTxt().setText("This Stock Item is Unavailable");
           screen.getNameTxt().setForeground(new java.awt.Color(255,204,204));
       }else{
           screen.getNameTxt().setText(StockDB.getStockItem(symbol).getSecurityName());
           screen.getOldPriceTxt().setText(""+StockDB.getStockItem(symbol).getPrice());
           screen.getNewPricetxt().setEnabled(true);
           screen.getSubmitbtn().setEnabled(true);
       }
    }
    
    private void submitBtnAction(){
        try{
        // get new price
        double newPrice=Double.parseDouble(screen.getNewPricetxt().getText());
        //set price of Map
        StockDB.setPrice(screen.getSymbolTxt().getText(), newPrice); 
        Offer change =new Offer("By Server", screen.getSymbolTxt().getText(), new Date(), newPrice); //add new change as an offer
        OfferDB.addOffer(change); //add the new change to offer list
        screen.dispose();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Price entered is invalid"); //if error value given as input
        }
    }
    
}
