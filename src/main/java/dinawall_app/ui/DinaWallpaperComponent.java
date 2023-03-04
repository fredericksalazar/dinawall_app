/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_app.ui;

import javafx.scene.layout.AnchorPane;
import dinawall_app.controller.DinaWallpaperComponentController;
import dinawall_core.wallpaper.DinaWallpaper;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;


/**
 *
 * @author frederick
 */
public class DinaWallpaperComponent extends AnchorPane{
        
    DinaWallpaperComponentController dinaWallpaperController;
    private DinaWallpaper dinawallpaper;

    public DinaWallpaperComponent() {
        super();
        init();
    }
    
    private void init(){
        try{            
            FXMLLoader loader = new FXMLLoader(DinaWallpaperComponentController.
                                               class.getResource("/dinawall_app/ui/dinawall_wallpaper_component.fxml"));
            
            dinaWallpaperController = new DinaWallpaperComponentController();
            loader.setController(dinaWallpaperController);
            
            Node component = loader.load();
            this.getChildren().add(component);
             
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method set a image inito a DinaWallpaperComponent Object, this
     * image correspond to dinawallpaper installed object .din, is returned
     * by dinawallcorelib
     * 
     * @param dinawallpaper 
     */
    
    public void setDinaWall(DinaWallpaper dinawallpaper){
        try{
            
            this.dinawallpaper = dinawallpaper;
            
            if(new File(dinawallpaper.getPreview()).exists()){
                File fimage = new File(dinawallpaper.getPreview());
                
                if(fimage.exists()){
                    Image previmage = new Image(fimage.toURI().toString(), 244, 181, false, false);
                    this.dinaWallpaperController.setdinaWallPreviewImage(previmage);
                }
                
            }
            this.dinaWallpaperController.setdinaWallPreviewTitle(dinawallpaper.getName());
            this.dinaWallpaperController.setdinWallPreviewAutor(dinawallpaper.getAutor());
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public DinaWallpaper getDinaWallpaperComponent(){
        return this.dinawallpaper;
    }
        
}
