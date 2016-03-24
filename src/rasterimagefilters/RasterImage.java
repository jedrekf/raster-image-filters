package rasterimagefilters;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jedrek
 */
public class RasterImage {
    
    private BufferedImage img;
    private Color[][] pixelArray;
    
    
    public RasterImage(BufferedImage img){
        this.img = img;
        pixelArray = takePixelArrayFromBuffered(img);
    }
    
    public RasterImage(Color[][] pixelArray){
        this.pixelArray = pixelArray;
    }
    
    public BufferedImage getBufferedImage(){
        return img;
    }
    
    public Color[][] getPixelArray(){
        return pixelArray;
    }
    
    private Color[][] takePixelArrayFromBuffered(BufferedImage img){
        int height = img.getHeight();
        int width = img.getWidth();
        Color[][] pixArr = new Color[width][height];
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                pixArr[i][j] = new Color(img.getRGB(i,j));
            }
        }
        
        return pixArr;
    }
}
