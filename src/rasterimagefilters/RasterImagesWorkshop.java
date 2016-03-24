
package rasterimagefilters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class RasterImagesWorkshop extends javax.swing.JFrame {
    private int BRIGHTNESS_INCREASE =20, BRIGHTNESS_DECREASE =20, KERNEL_SIZE = 3, PANEL_WIDTH = 600, PANEL_HEIGHT = 600;
    private Anchor anchor = new Anchor();
    private ImagePanel imgPanel;
    private BufferedImage bufImg;
    private RasterImage rasterImg;
    private RasterFilter rasterFilter= new RasterFilter();
    private ConvolutionFilter convolutionFilter = new ConvolutionFilter();
    private int[][] kernel;
    private int offset;
    /** Creates new form Antenna */
    public RasterImagesWorkshop() throws Exception {
        initComponents();
        this.setTitle("Raster image filters.");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        initiateImagePanel();
    }
    
    
    
    private void initiateImagePanel(){
    bufImg = new BufferedImage(PANEL_WIDTH, PANEL_HEIGHT, TYPE_INT_RGB);
    imgPanel = new ImagePanel(bufImg);
    imgPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
    imgPanel.setVisible(true);
    
    this.add(imgPanel);
    this.repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonInversion = new javax.swing.JButton();
        buttonBrighten = new javax.swing.JButton();
        buttonDim = new javax.swing.JButton();
        buttonContrastEnhance = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonBlur = new javax.swing.JButton();
        buttonGaussianSmoothing = new javax.swing.JButton();
        buttonSharpen = new javax.swing.JButton();
        buttonEdgeDetection = new javax.swing.JButton();
        buttonEmboss = new javax.swing.JButton();
        buttonCustomFilter = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemOpen = new javax.swing.JMenuItem();
        menuItemExit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuItemCustomKernel = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Antenna");

        buttonInversion.setText("Inversion");
        buttonInversion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInversionActionPerformed(evt);
            }
        });

        buttonBrighten.setText("Brighten");
        buttonBrighten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBrightenActionPerformed(evt);
            }
        });

        buttonDim.setText("Dim");
        buttonDim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDimActionPerformed(evt);
            }
        });

        buttonContrastEnhance.setText("Contrast enhance");
        buttonContrastEnhance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonContrastEnhanceActionPerformed(evt);
            }
        });

        jButton2.setText("Gamma correction");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        buttonBlur.setText("Blur");
        buttonBlur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBlurActionPerformed(evt);
            }
        });

        buttonGaussianSmoothing.setText("Gaussian Smoothing");
        buttonGaussianSmoothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGaussianSmoothingActionPerformed(evt);
            }
        });

        buttonSharpen.setText("Sharpen");
        buttonSharpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSharpenActionPerformed(evt);
            }
        });

        buttonEdgeDetection.setText("Edge Detection");
        buttonEdgeDetection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdgeDetectionActionPerformed(evt);
            }
        });

        buttonEmboss.setText("Emboss");
        buttonEmboss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmbossActionPerformed(evt);
            }
        });

        buttonCustomFilter.setText("Custom Filter");
        buttonCustomFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomFilterActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(buttonSharpen, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonInversion, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonBrighten, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonDim, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonContrastEnhance, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonBlur, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonGaussianSmoothing, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonEdgeDetection, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonEmboss, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(buttonCustomFilter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(buttonInversion)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonBrighten)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonDim)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonContrastEnhance)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonBlur)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonGaussianSmoothing)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonSharpen)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonEdgeDetection)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonEmboss)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(buttonCustomFilter)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        menuItemOpen.setText("Open");
        menuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemOpen);

        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemExit);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("kernel");

        menuItemCustomKernel.setText("custom kernel");
        menuItemCustomKernel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCustomKernelActionPerformed(evt);
            }
        });
        jMenu3.add(menuItemCustomKernel);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(604, Short.MAX_VALUE)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonInversionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInversionActionPerformed
        updateImage(rasterFilter.inversion(getPixelArrayFromBufferedImage(bufImg)));
    }//GEN-LAST:event_buttonInversionActionPerformed

    private void buttonBrightenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBrightenActionPerformed
        updateImage(rasterFilter.brightnessIncrease(getPixelArrayFromBufferedImage(bufImg), BRIGHTNESS_INCREASE));
    }//GEN-LAST:event_buttonBrightenActionPerformed

    private void buttonDimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDimActionPerformed
        updateImage(rasterFilter.brightnessDecrease(getPixelArrayFromBufferedImage(bufImg), BRIGHTNESS_DECREASE));
    }//GEN-LAST:event_buttonDimActionPerformed

    private void buttonContrastEnhanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonContrastEnhanceActionPerformed
        updateImage(rasterFilter.contrastEnhancement(getPixelArrayFromBufferedImage(bufImg)));
    }//GEN-LAST:event_buttonContrastEnhanceActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateImage(rasterFilter.gammaCorrection(getPixelArrayFromBufferedImage(bufImg)));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void buttonBlurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBlurActionPerformed
        anchor.x = 2; anchor.y = 2;
        updateImage(convolutionFilter.Blur(getPixelArrayFromBufferedImage(bufImg), KERNEL_SIZE, anchor, 0));
    }//GEN-LAST:event_buttonBlurActionPerformed

    private void buttonGaussianSmoothingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGaussianSmoothingActionPerformed
        anchor.x = 3; anchor.y = 3;
        updateImage(convolutionFilter.gaussianSmoothing(getPixelArrayFromBufferedImage(bufImg), anchor, 0));
    }//GEN-LAST:event_buttonGaussianSmoothingActionPerformed

    private void buttonSharpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSharpenActionPerformed
        anchor.x = 2; anchor.y = 2;
        updateImage(convolutionFilter.sharpen(getPixelArrayFromBufferedImage(bufImg), anchor, 0));
    }//GEN-LAST:event_buttonSharpenActionPerformed

    private void buttonEdgeDetectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdgeDetectionActionPerformed
        anchor.x = 2; anchor.y = 2;
        updateImage(convolutionFilter.edgeDetection(getPixelArrayFromBufferedImage(bufImg), anchor, 0));
    }//GEN-LAST:event_buttonEdgeDetectionActionPerformed

    private void buttonEmbossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmbossActionPerformed
        anchor.x = 2; anchor.y = 2;
        updateImage(convolutionFilter.emboss(getPixelArrayFromBufferedImage(bufImg), anchor, 0));
    }//GEN-LAST:event_buttonEmbossActionPerformed

    private void menuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuItemExitActionPerformed

    private void menuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
            bufImg = imageResize(ImageIO.read(file));
            }catch(IOException ex){}
            imgPanel.setImage(bufImg);
            //updateImage(getPixelArrayFromBufferedImage(bufImg));
        }
    }//GEN-LAST:event_menuItemOpenActionPerformed

    private void menuItemCustomKernelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCustomKernelActionPerformed
        kernelInputDialog kid = new kernelInputDialog(this, rootPaneCheckingEnabled);
        kid.setVisible(true);
        kernel = kid.getKernel();
        anchor = kid.getAnchor();
        offset = kid.getOffset();
    }//GEN-LAST:event_menuItemCustomKernelActionPerformed

    private void buttonCustomFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomFilterActionPerformed
        if(kernel == null){
            JOptionPane.showMessageDialog(this,"Define custom kernel first","error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateImage(convolutionFilter.customFilter(getPixelArrayFromBufferedImage(bufImg), kernel, anchor, offset));
    }//GEN-LAST:event_buttonCustomFilterActionPerformed

    private Color[][] getPixelArrayFromBufferedImage(BufferedImage bufImg){
        rasterImg = new RasterImage(bufImg);
        return rasterImg.getPixelArray();
    }
    
    private void updateImage(Color[][] pa){
        for(int i=0; i< bufImg.getWidth(); i++){
            for(int j=0; j<bufImg.getHeight(); j++){
                bufImg.setRGB(i, j, pa[i][j].getRGB());
            }
        }
        this.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBlur;
    private javax.swing.JButton buttonBrighten;
    private javax.swing.JButton buttonContrastEnhance;
    private javax.swing.JButton buttonCustomFilter;
    private javax.swing.JButton buttonDim;
    private javax.swing.JButton buttonEdgeDetection;
    private javax.swing.JButton buttonEmboss;
    private javax.swing.JButton buttonGaussianSmoothing;
    private javax.swing.JButton buttonInversion;
    private javax.swing.JButton buttonSharpen;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem menuItemCustomKernel;
    private javax.swing.JMenuItem menuItemExit;
    private javax.swing.JMenuItem menuItemOpen;
    // End of variables declaration//GEN-END:variables

    private BufferedImage imageResize(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int ratio = width/height;
        int afterWidth = 0, afterHeight = 0;
        if((ratio= width/height) > 1){
            if(width > PANEL_WIDTH){
               afterWidth = PANEL_WIDTH;
            }
            else
                afterWidth = width;
            afterHeight = afterWidth/ratio;
        }
        else{
           if(height > PANEL_HEIGHT){
               afterHeight = PANEL_HEIGHT;
           }
           else
               afterHeight = height;
           afterWidth = afterHeight/ratio;
        }
        BufferedImage newImage = new BufferedImage(afterWidth, afterHeight, BufferedImage.TYPE_INT_RGB);
        
        Graphics g = newImage.createGraphics();
        g.drawImage(img, 0, 0, afterWidth, afterHeight, null);
        g.dispose();
        return newImage;
    }
    
}
