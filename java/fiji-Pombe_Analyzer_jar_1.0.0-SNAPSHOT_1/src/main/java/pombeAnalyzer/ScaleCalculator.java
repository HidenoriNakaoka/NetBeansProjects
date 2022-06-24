package pombeAnalyzer;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nakaokahidenori
 */
public class ScaleCalculator {
    // Private fields
    final private double objective_;
    final private double binning_;
    final private double aux_;
    final private double pxl_per_um_;
    final private double um_per_pxl_;
    
    // Empirical conversion factors
    // Note that these values are for binning = 2
    final private double c1 = 0.0774;
    final private double c2 = 12.88;
    // [pxl/um] = c1 * [objective magnification]
    // [um/pxl] = c2 * [objective magnification]^-1
    
    // Constructor
    ScaleCalculator(int objective, int binning, boolean aux){
        objective_ = (double)objective;
        binning_ = (double)binning;
        if(aux){
            aux_ = 1.5;
        }else{
            aux_ = 1.0;
        }
        
        pxl_per_um_ = scale_pxl_per_um();
        um_per_pxl_ = scale_um_per_pxl();
    }
    
    private double scale_pxl_per_um(){
        return c1*objective_*(2.0/binning_)*aux_;
    }
    
    private double scale_um_per_pxl(){
        return 1.0/(c1*objective_*(2.0/binning_)*aux_);
    }
    
    public double getScale_pxl_per_um(){
        return pxl_per_um_;
    }
    
    public double getScale_um_per_pxl(){
        return um_per_pxl_;
    }
    
}
