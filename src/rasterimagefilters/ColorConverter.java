/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rasterimagefilters;

import java.awt.Color;
import org.w3c.dom.css.RGBColor;

/**
 *
 * @author Jedrek
 */
public class ColorConverter {
    private Color[][] editedPixArr;
    int width, height;
    private Color black = new Color(0,0,0), white= new Color(255, 255, 255);
    private void setupLocalImage(Color[][] pixArr){
        width = pixArr.length;
        height = pixArr[0].length;
        editedPixArr = new Color[width][height];
    }
    
    
    public Color[][] ConvertToGrayScaleAvg(Color[][] pixArr){
        int avg;
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                avg = (pixArr[i][j].getRed() + pixArr[i][j].getGreen() + pixArr[i][j].getBlue())/3;
                editedPixArr[i][j] = new Color(avg, avg, avg);
            }
        }
        return editedPixArr;
    }
    public Color[][] ConvertToGrayScaleLuminosity(Color[][] pixArr){
        int avg;
        setupLocalImage(pixArr);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                avg = (int) ((0.21 * pixArr[i][j].getRed()) + (0.72 * pixArr[i][j].getGreen()) + (0.07 * pixArr[i][j].getBlue()));
                editedPixArr[i][j] = new Color(avg, avg, avg);
            }
        }
        return editedPixArr;
    }
    public Color[][] UniformQuantisation(Color[][] pixArr, double kRed, double kGreen, double kBlue){
        setupLocalImage(pixArr);
        int r,g,b, colRed, colGreen, colBlue, finalRed = 0, finalGreen = 0, finalBlue = 0;
        double mapRed, mapGreen, mapBlue;
        
        //splitting the RGB according to input data
        int[] colorsRed = getKColors((int)kRed); int[] colorsGreen = getKColors((int)kGreen); int[] colorsBlue = getKColors((int)kBlue);
        //mapping value
        mapRed =Math.floor(255/kRed);
        mapGreen =Math.floor(255/kGreen);
        mapBlue =Math.floor(255/kBlue);
        
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                r = pixArr[i][j].getRed(); g = pixArr[i][j].getGreen(); b = pixArr[i][j].getBlue();
                //getting the interval the color belongs to
                colRed = (int)Math.ceil((int)r/(int)mapRed);
                colGreen = (int)Math.ceil((int)g/(int)mapGreen);
                colBlue = (int)Math.ceil((int)b/(int)mapBlue);
                
                //Choosing the interval a color belongs to.
                for(int interval = 1; interval <= kRed; interval++){
                    if(colRed == interval){
                        finalRed = colorsRed[interval-1];
                        break;
                    }
                }
                for(int interval = 1; interval <= kGreen; interval++){
                    if(colGreen == interval){
                        finalGreen = colorsGreen[interval-1];
                        break;
                    }
                }
                for(int interval = 1; interval <= kBlue; interval++){
                    if(colBlue == interval){
                        finalBlue = colorsBlue[interval-1];
                        break;
                    }
                }
                editedPixArr[i][j] = new Color(finalRed, finalGreen, finalBlue);
            }
        }
        
        return editedPixArr;
    }

    /*
    @param k - number of colors in available palette
    @param n - size of the matrix
    */
    public Color[][] OrderedDithering(Color[][] pixArr, int n, int k) throws Exception{
        setupLocalImage(pixArr);
        int[][] dithArr = getDitheringArray(n);
        double px = 0, colors = n*n+1, nMap = Math.ceil(255/colors), kMap = Math.ceil((double)(255/(double)k));
        int shade, grayShade, requiredShade, pxColorAvg, r, g, b;
        int[] kColorsValues = getKColors(k);
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                // to get the relative value to set the pixels
                r = pixArr[i][j].getRed(); g = pixArr[i][j].getGreen(); b = pixArr[i][j].getBlue();
                pxColorAvg = (r+g+b)/3;
                shade = (int)Math.ceil(pxColorAvg/(int)nMap);
                requiredShade = dithArr[i%n][j%n];
                
                if(shade <= requiredShade){
                    grayShade = (int)Math.ceil(pxColorAvg/kMap);                        
                    for(int sh = 1; sh <= k; sh++){
                        if(grayShade == sh){
                            editedPixArr[i][j] = new Color(kColorsValues[sh-1], kColorsValues[sh-1], kColorsValues[sh-1]);
                            break;
                        }
                    }
                }else{
                    editedPixArr[i][j] = white; 
                }
            }
        }
        return editedPixArr;
        
    }
    
    private int[] getKColors(int k) {
       int[] colors = new int[k];
       int step = (int)Math.ceil((double)((int)255/(int)(k-1)));
       for(int i=0; i< k-1 ; i++){
           colors[i] = (step*i);
       }
       return colors;
    }
    
    public Color[][] OrderedDitheringColor(Color[][] pixArr, int n) throws Exception{
        setupLocalImage(pixArr);
        int[][] dithArr = getDitheringArray(n);
        double px = 0, colors = n*n, nMap = 255/colors;
        int shade, requiredShade, pxColorAvg, r, g, b;
        Color black = new Color(0,0,0), white= new Color(255, 255, 255);
        
        for(int i=0; i< width; i++){
            for(int j=0; j<height; j++){
                // to get the relative value to set the pixels
                r = pixArr[i][j].getRed(); g = pixArr[i][j].getGreen(); b = pixArr[i][j].getBlue();
                pxColorAvg = (r+g+b)/3;
                shade = (int)Math.ceil(pxColorAvg/nMap);
                requiredShade = dithArr[i%n][j%n];
                
                if(shade <= requiredShade){
                        editedPixArr[i][j] = new Color(r, g, b); 
                }else{
                    editedPixArr[i][j] = white; 
                }
            }
        }
        return editedPixArr;
        
    }

    private int[][] getDitheringArray(int n) throws Exception {
        switch(n){
            case 1:
                return new int[][]{{1}};
            case 2:
                return new int[][]{{1, 3}, 
                                   {4, 2}};
            case 3:
                return new int[][]{{3, 7, 4},
                                   {6, 1, 9},
                                   {2, 8, 5}};
            case 4:
                return new int[][]{{1,9,3,11},
                                   {13, 5, 15, 7},
                                   {4, 12, 2, 10},
                                   {16, 8, 14, 6}};
            case 8:
                return new int[][]{{1,49,13,61,4,52,16,64},
                                   {33,17,45,29,36,20,48,32},
                                   {9,57,5,53,12,60,8,56}, 
                                   {41,25,37,21,44,28,40,24},
                                   {3,51,15,63,2,50,14,62},
                                   {35,19,47,31,34,18,46,30},
                                   {11,59,7,55,10,58,6,54},
                                   {43,27,39,23,42,26,38,22}};
            default:
                throw new Exception("Such matrix was not prepared.");
        }
    }

}
