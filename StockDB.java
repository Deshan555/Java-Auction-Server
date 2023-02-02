

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Hishan Indrajith
 */
public class StockDB {
    private static HashMap<String,StockItem> stockItemMap;
    private String csvFile = "stocks.csv";
    private FileReader fileRd=null; 
    private BufferedReader reader=null; 
    
    public StockDB() {
        
        stockItemMap =new HashMap<String,StockItem>();
        try {
            fileRd = new FileReader(csvFile); 
            reader = new BufferedReader(fileRd);
            String [] tokens;
            reader.readLine();
            for(String line = reader.readLine(); line != null; line = reader.readLine()) { 
                
		tokens = line.split(",");
                
                String price = tokens[tokens.length-1];
		StockItem item = new StockItem(tokens[0],Double.parseDouble(price),tokens[1]);
		stockItemMap.put(tokens[0],item); 
	    }
	    
	    if(fileRd != null) fileRd.close();
	    if(reader != null) reader.close();
        } catch (FileNotFoundException ex) {
        }catch (IOException ex) {
        }
    }
    
    public static StockItem getStockItem(String symbol){
        return stockItemMap.get(symbol);
    }
    
    public static void setPrice(String symbol,double price){
       StockItem item = stockItemMap.get(symbol);
       item.setPrice(price);
       stockItemMap.put(symbol, item);
    }
    
    public static boolean isExist(String symbol){
        return stockItemMap.containsKey(symbol);
    }
    
    public static Collection<StockItem> getValues(){
        return stockItemMap.values();
    }
    
    
    
}
