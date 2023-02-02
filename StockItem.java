
public class StockItem {

    private String symbol;
    private double price;
    private String securityName;

    public StockItem(String symbol, double price, String securityName) {
        this.symbol = symbol;
        this.price = price;
        this.securityName = securityName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
