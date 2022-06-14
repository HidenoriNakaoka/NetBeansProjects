
import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nakaokahidenori
 */

// Strategy
// 1. Background subtraction using Mode value of an image
// 2. Mexican Hat Filter
// 3. Conversion to 8 bit
// 4. Despeckle
// 5. Size filter

public class Binarizer {
    private ImagePlus imp_;
    private ImageProcessor ip_;
    private double mode_;
    private double mode_subtraction_level_;
    private int mexicanhat_radious_;
    private double scale_;//um/pxl (scale2)

    // Constructor
    public Binarizer(ImagePlus imp){
        imp_ = imp;
        ip_ = imp.getProcessor();
        mode_ = imp_.getAllStatistics().mode;
    }
    
    public Binarizer(ImagePlus imp, int mode_subtraction_level, int mexicanhat_radious, double scale){
        imp_ = imp;
        ip_ = imp.getProcessor();
        mode_ = imp_.getAllStatistics().dmode;
        mode_subtraction_level_ = (double)mode_subtraction_level;
        mexicanhat_radious_ = mexicanhat_radious;
        scale_ = scale;
    }
    
    public int getMode(){
        return (int)mode_;
    }
    
    public void setModeSubtractionLevel(int value){
        mode_subtraction_level_ = (double)value;
    }
    
    public void setMexicanHatFilterRadious(int value){
        mexicanhat_radious_ = value;
    }
    
    // Destructive method
    public void run(){
        int subtract = (int)(-1*mode_*(1+mode_subtraction_level_/10));
        ip_.add(subtract);
        MexicanHatFilter mhf = new MexicanHatFilter();
        mhf.setRadious(mexicanhat_radious_);
        FloatProcessor fp = ip_.convertToFloatProcessor();
        mhf.run(fp);
        ip_ = fp.convertToByteProcessor();
        int mode = (int)ip_.getStatistics().dmode;
        ip_.add(-1*mode);
        ip_.multiply(255);
        imp_.setProcessor(ip_);  
        //imp_.updateAndDraw();
        //imp_.show();
    }
    
    public void setImagePlus(ImagePlus imp){
        imp_ = imp;
    }
    
    public ByteProcessor getImageProcessor(){
        return (ByteProcessor)imp_.getProcessor();
    }
}
