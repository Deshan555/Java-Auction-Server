
import java.util.ArrayList;

/**
 *
 * @author Hishan Indrajith
 */
public class OfferDB {
    private static ArrayList<Offer> offerList;
    
    public OfferDB() {
        offerList =new ArrayList<Offer>(); 
    }
    
    public static void addOffer(Offer offer){
        offerList.add(offer);
        ServerController.addDetailsToTable(offer);
    }

    public static ArrayList<Offer> getOfferList() {
        return offerList;
    }
    
}
