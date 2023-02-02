

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hishan Indrajith
 */
public class ListStockController {
    private StockDisplayScreen screen;
    public ListStockController() {
        screen = new StockDisplayScreen();
        screen.setVisible(true);
        screen.setResizable(false);
        screen.pack();
        screen.setLocationRelativeTo(null);
        initTable();
    }
    
    private void initTable(){
        DefaultTableModel dtm = (DefaultTableModel)screen.getStockListTable().getModel();
        //ArrayList<StockItem> offerList = (ArrayList<StockItem>) StockDB.getValues();
        for (StockItem itemArray1 : StockDB.getValues()) {
            Object[] newRow = {itemArray1.getSymbol(), itemArray1.getSecurityName(), itemArray1.getPrice()};
            dtm.addRow(newRow);
        }
        
    }
    
}
