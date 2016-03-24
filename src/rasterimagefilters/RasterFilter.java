/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rasterimagefilters;

import java.awt.Color;

/**
 *
 * @author Jedrek
 */
public class RasterFilter {
    Color[][] editedPixArr;
    int width, height;
    private double gamma = 0.5;
    
    public RasterFilter(){}
    
    public Color[][] inversion(Color[][] pixArr){
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                editedPixArr[i][j] = new Color(255 - pixArr[i][j].getRed(),
                        255- pixArr[i][j].getGreen(),255- pixArr[i][j].getBlue());
            }
        }
        return editedPixArr;
    }
    
    public Color[][] brightnessIncrease(Color[][] pixArr, int value){
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                editedPixArr[i][j] = new Color(addToRGB(pixArr[i][j].getRed(),value), 
                        addToRGB(pixArr[i][j].getGreen(),value), addToRGB(pixArr[i][j].getBlue(),value));
            }
        }
            
        return editedPixArr;
    }
    
    public Color[][] brightnessDecrease(Color[][] pixArr, int value){
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                editedPixArr[i][j] = new Color(subtractFromRGB(pixArr[i][j].getRed(),value), 
                        subtractFromRGB(pixArr[i][j].getGreen(),value), subtractFromRGB(pixArr[i][j].getBlue(),value));
            }
        }
            
        return editedPixArr;
    }
    
    public Color[][] gammaCorrection(Color[][] pixArr){
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                editedPixArr[i][j] = new Color(gammaCorrectionRGB(pixArr[i][j].getRed()), 
                        gammaCorrectionRGB(pixArr[i][j].getGreen()), gammaCorrectionRGB(pixArr[i][j].getBlue()));
            }
        }
            
        return editedPixArr;
    }
    
    public Color[][] contrastEnhancement(Color[][] pixArr){
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height;j++){
                editedPixArr[i][j] = new Color(contrastEnhanceRGB(pixArr[i][j].getRed()), 
                        contrastEnhanceRGB(pixArr[i][j].getGreen()), contrastEnhanceRGB(pixArr[i][j].getBlue()));
            }
        }
            
        return editedPixArr;
    }
    
     private void setupLocalImage(Color[][] pixArr){
        width = pixArr.length;
        height = pixArr[0].length;
        editedPixArr = new Color[width][height];
    }
    
    private int addToRGB(int color, int value){
        if((color = color + value) > 255)
            return 255;
        return color;
    }
    
    private int subtractFromRGB(int color, int value){
        if((color = color - value) < 0)
            return 0;
        return color;
    }
    
    private int contrastEnhanceRGB(int color){
        int y = 2*color - 80;
        if(y<0)
            return 0;
        if(y>255)
            return 255;
        
        return y;
    }
    
    private int gammaCorrectionRGB(int color){
        double result = 0;
        result = (Math.pow((double)color/255, 1/gamma))*(double)255;
        return (int) result;
    }
    
}
