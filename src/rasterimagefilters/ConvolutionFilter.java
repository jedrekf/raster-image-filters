/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rasterimagefilters;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jedrek
 */
public class ConvolutionFilter {
    private int[][] kernel = null;
    int kernelWidth, kernelHeight;
    private Color[][] editedPixArr;
    private int width, height;
    //ANCHOR CELL INDEXING STARTS FROM {1,1} !!!
    private Anchor anchor;
    //pixels to check around kernel anchor
    int kaxStart, kayStart, kaxEnd, kayEnd;
    public ConvolutionFilter(){}
    
    private void setupLocalImage(Color[][] pixArr){
        width = pixArr.length;
        height = pixArr[0].length;
        editedPixArr = new Color[width][height];
    }
    
    private void setupKernel(int[][] _kernel){
        kernelWidth = _kernel.length ;
        kernelHeight = _kernel[0].length ;
    }
    
    private int[][] createBlurKernel(int size) throws Exception{
        if(size%2 != 0 && size > 0 && size <= 9){
            kernel = new int[size][size];
            for(int i=0; i<size; i++){
                for(int j=0; j<size; j++){
                    kernel[i][j] = 1;
                }
            }
            setupKernel(kernel);
            return kernel;
        }
        else
            throw new Exception("Invalid size of the kernel.");
    }
    
    private void calculateKernelAnchorPixels(){
        kaxEnd = kernelWidth - anchor.x; 
        kayEnd = kernelHeight - anchor.y;
        kaxStart = kaxEnd - (kernelWidth-1);
        kayStart = kayEnd - (kernelHeight-1);
    }
    
    public Color[][] Blur(Color[][] pixArr, int kernelSize, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor  = _anchor;
        try {
            createBlurKernel(kernelSize);
        } catch (Exception ex) {
            Logger.getLogger(ConvolutionFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //can define custom kernel here
        
        //calculating kernel "radius" according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            //do the math here
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }

        return editedPixArr;
    }
    
    public Color[][] gaussianSmoothing(Color[][] pixArr, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor = _anchor;
        //kernel = new int[][]{{0,1,0}, {1,4,1}, {0,1,0}};
        kernel = new int[][]{{0,1,2,1,0}, {1,4,8,4,1}, {2,8,16,8,2}, {1,4,8,4,1}, {0,1,2,1,0}};
        setupKernel(kernel);
        //calculating pixels according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }

        return editedPixArr;
    }

    public Color[][] sharpen(Color[][] pixArr, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor = _anchor;
        //kernel = new int[][]{{0,-1,0}, {-1,5,-1}, {0,-1,0}};
        kernel = new int[][]{{-1,-1,-1}, {-1,9,-1}, {-1,-1,-1}};
        setupKernel(kernel);
        //calculating pixels according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }

        return editedPixArr;
    }
    
        public Color[][] edgeDetection(Color[][] pixArr, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor = _anchor;
        //horizontal
        //kernel = new int[][]{{0,-1,0}, {0,1,0}, {0,0,0}};
        //vertical
        //kernel = new int[][]{{0,0,0}, {-1,1,0}, {0,0,0}};
        //diagonal
        kernel = new int[][]{{-1,0,0}, {0,1,0}, {0,0,0}};
        setupKernel(kernel);
        //calculating pixels according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }

        return editedPixArr;
    }    
        
   public Color[][] emboss(Color[][] pixArr, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor = _anchor;
        //east
        //kernel = new int[][]{{-1,0,1}, {-1,1,1}, {-1,0,1}};
        //south
        kernel = new int[][]{{-1,-1,-1}, {0,1,0}, {1,1,1}};
        //south-east
        //kernel = new int[][]{{-1,-1,0}, {-1,1,1}, {0,1,1}};
        setupKernel(kernel);
        //calculating pixels according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }
        
        return editedPixArr;
    }     
   
    public Color[][] customFilter(Color[][] pixArr,int[][] kernel, Anchor _anchor, int offset){
        setupLocalImage(pixArr);
        anchor = _anchor;
        
        setupKernel(kernel);
        //calculating pixels according to anchor position
        calculateKernelAnchorPixels();
        
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){

                int[] sum = {0,0,0};
                int divisor = 0;
                int anchorX = 0;
                for(int x = kaxStart; x<=kaxEnd; x++){
                    int anchorY = 0;
                    for(int y = kayStart; y<=kayEnd; y++){
                        //check wether current cell of kernel is over the image
                        int tempx = i+x;
                        int tempy = j+y;
                        if(tempx >= 0 && tempx < width && tempy >= 0 && tempy < height){
                            //if it gets here it means that the current kernel cell is over the image
                            sum[0] = sum[0] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getRed();
                            sum[1] = sum[1] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getGreen();
                            sum[2] = sum[2] + kernel[anchorX][anchorY] * pixArr[i+x][j+y].getBlue();
                            divisor = divisor + kernel[anchorX][anchorY];
                        }
                        anchorY++;
                    }
                    anchorX++;
                }
                //division and adding and offset
                if(divisor == 0)
                    divisor = 1;
                for(int n=0; n<sum.length; n++){
                    sum[n] =offset + sum[n]/divisor;
                    if(sum[n] > 255)
                        sum[n] = 255;
                    if(sum[n] < 0)
                        sum[n] = 0;
                }
                editedPixArr[i][j] = new Color(sum[0], sum[1], sum[2]);
            }
        }
        return editedPixArr;
    }
}
