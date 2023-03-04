/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_app.model;

import dinawall_core.DinaWallCore;
import dinawall_core.wallpaper.DinaWallpaper;
import java.util.ArrayList;

/**
 *
 * @author frederick
 */
public class DinaWallAppModel {
    
    public static DinaWallAppModel dinaAppmodel;
    
    private DinaWallpaper selectedWallpaper;
    public DinaWallCore dinawallcore;
    private ArrayList<DinaWallpaper> dinaWallList;
    

    private DinaWallAppModel() { 
        System.out.println("Crando instancia de dinamodel ...");
        dinawallcore = DinaWallCore.getInstance();
        dinaWallList = dinawallcore.get_dinawall_installed();
        System.err.println("lista de wallpaper model contiene -> "+ dinaWallList.size());
    }
    
    
    /**
     * This method set a dinawalllist into the modelo
     * @param dinawalllist 
     */
    public void setDinaWallList(ArrayList<DinaWallpaper> dinawalllist){
        this.dinaWallList = dinawalllist;
    }
    
    /**
     * This method return a list of dynamic wallpapers installed, this list
     * is returned by dinawaalcorelib
     * 
     * @return 
     */
    
    public ArrayList<DinaWallpaper> getDinaWallList(){
        return this.dinaWallList;
    }
    
    /**
     * This method return a selected dynamic wallpaper in the user interface
     * 
     * @return 
     */

    public DinaWallpaper getSelectedWallpaper() {
        return selectedWallpaper;
    }

    /**
     * This method set a dynamic wallpaper selected in the user interface
     * @param selectedWallpaper 
     */
    
    public void setSelectedWallpaper(DinaWallpaper selectedWallpaper) {
        System.err.println("Ha seleccionado el wallpaper -> "+selectedWallpaper.getName());
        this.selectedWallpaper = selectedWallpaper;
    }
    
    /**
     * This method create a uique instance of the model data
     * @return 
     */
    
    public static DinaWallAppModel getInstance(){
                
        if(dinaAppmodel == null){
            dinaAppmodel = new DinaWallAppModel();
        }
        
        return dinaAppmodel;
    }
    
    
}
