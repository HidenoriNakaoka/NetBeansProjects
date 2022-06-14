
import ij.IJ;
import ij.ImagePlus;
import ij.gui.Roi;
import ij.plugin.filter.ExtendedPlugInFilter;
import ij.plugin.filter.PlugInFilter;
import ij.plugin.filter.PlugInFilterRunner;
import ij.plugin.frame.RoiManager;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nakaokahidenori
 */
public class CellFinder implements PlugInFilter{
    
    // Private fields
    ImagePlus imp_;
    ImagePlus imp_copy_;
    ByteProcessor bp_;
    ByteProcessor bp_copy_;
    
    // Theoretical typical cell area is 37.1 um^2, assuming that longitudal axis is 13 um and diameter is 3 um
    double scale_; // um/pxl, 1 um^2 = 1/scale_^2 pxl
    double TYPICALAREA; // in a unit of pixel area
    int MINAREA;
    int MAXAREA;
    
    RoiManager rm_;
    
    public CellFinder(ImagePlus imp, double scale){
        imp_ = imp;
        bp_ = (ByteProcessor)imp_.getProcessor();
        imp_copy_ = (ImagePlus)imp_.duplicate();
        bp_copy_ = (ByteProcessor)imp_copy_.getProcessor();
        scale_ = scale;
        TYPICALAREA = 37.1*(1/scale_/scale_);
        MINAREA = (int)(TYPICALAREA/10);
        MAXAREA = (int)(TYPICALAREA*5);
    }

    @Override
    public int setup(String string, ImagePlus imp) {
        if(imp.getBitDepth()!=8){
            IJ.showMessage("CellFinder: Image must be 8-bit!!");
            return 0;
        }
        return 1;
    }

    @Override
    public void run(ImageProcessor ip){
        
    }
    
    // Destructive method
    public void run() {
        // Find a white pixel, doWand, and erase it by black-filling
        for(int u = 0; u < imp_copy_.getWidth(); u++){
            for(int v = 0; v < imp_copy_.getHeight(); v++){
                if(bp_copy_.get(u,v)==255){
                    IJ.doWand(imp_copy_, u, v, 0.0, "Legacy");
                    Roi r = imp_copy_.getRoi();
                    IJ.setForegroundColor(0, 0, 0);
                    bp_copy_.fill(r);// Erase the Roi in the copied image
                    if(isCell(r)){
                        IJ.setForegroundColor(255,255,255);
                        bp_.fill(r);// Fill the Roi in the original image
                    }else{
                        IJ.setForegroundColor(0,0,0);
                        bp_.fill(r);// Erase the Roi in the original image
                    }
                }
            }
        }
    }
    
    public RoiManager run_with_roiManager(){
        // Find a white pixel, doWand, and erase it by black-filling
        RoiManager rm = new RoiManager(false);
        for(int u = 0; u < imp_copy_.getWidth(); u++){
            for(int v = 0; v < imp_copy_.getHeight(); v++){
                if(bp_copy_.get(u,v)==255){
                    IJ.doWand(imp_copy_, u, v, 0.0, "Legacy");
                    Roi r = imp_copy_.getRoi();
                    IJ.setForegroundColor(0, 0, 0);
                    bp_copy_.fill(r);// Erase the Roi in the copied image
                    if(isCell(r)) rm.addRoi(r);
                }
            }
        }
        return rm;
    }
    
    private boolean isCell(Roi r){
        int pxls = r.getContainedPoints().length;
        if(pxls>MINAREA && pxls<MAXAREA){
            //System.out.println("MAX:"+MAXAREA+",MIN:"+MINAREA+",CELL:"+pxls);
            return true;
        }else {
            //System.out.println("MAX:"+MAXAREA+",MIN:"+MINAREA+",NON-CELL:"+pxls);
            return false;
        }     
    }
    
    public ImagePlus getResultImage(){
        return imp_;
    }
    
    public ImagePlus getDummyImage(){
        return imp_copy_;
    }
    
}
