package rasterimagefilters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Jedrek
 */
public class ImagePanel extends JPanel{
    
    private BufferedImage img;
    
    public ImagePanel(){
    }
    
    public ImagePanel(BufferedImage _img){
        img = _img;
        
    }
    
    public void setImage(BufferedImage img){
        this.img = img;
        this.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
    
    
}
