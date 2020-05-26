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
    
    public static DinaWallAppModel dinaApp_model;
    
    private DinaWallpaper selectedWallpaper;
    public DinaWallCore dinawall_core;
    private ArrayList<DinaWallpaper> dinawall_list;
    

    private DinaWallAppModel() { 
        System.out.println("Crando instancia de dina_model ...");
        dinawall_core = DinaWallCore.getInstance();
        dinawall_list = dinawall_core.get_dinawall_installed();
        System.err.println("lista de wallpaper model contiene -> "+dinawall_list.size());
    }
    
    
    /**
     * This method set a dinawall_list into the modelo
     * @param dinawall_list 
     */
    public void setDinaWallList(ArrayList<DinaWallpaper> dinawall_list){
        this.dinawall_list = dinawall_list;
    }
    
    /**
     * This method return a list of dynamic wallpapers installed, this list
     * is returned by dinawaal_core_lib
     * 
     * @return 
     */
    
    public ArrayList<DinaWallpaper> getDinaWallpaperList(){
        return this.dinawall_list;
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
                
        if(dinaApp_model == null){
            dinaApp_model = new DinaWallAppModel();
        }
        
        return dinaApp_model;
    }
    
    
}
