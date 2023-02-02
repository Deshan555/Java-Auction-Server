
import java.util.Date;

/**
 *
 * @author Hishan Indrajith
 */
public class Offer {
    private String clientName;
    private String symbol;
    private Date date;
    private double price;

    public Offer(String clientName, String symbol, Date date, double price) {
        this.clientName = clientName;
        this.symbol = symbol;
        this.date = date;
        this.price = price;
    }

    public String getClientName() {
        return clientName;
    }

    public String getSymbol() {
        return symbol;
    }

    public Date getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }
    
    
}
