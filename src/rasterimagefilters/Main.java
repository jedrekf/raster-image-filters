package rasterimagefilters;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jedrek
 */
public class Main {
        public static void main(String args[]){
        
            try {
                new RasterImagesWorkshop().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }   
        
    }
}
