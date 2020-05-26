/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import dinawall_app.controller.PrimarySceneController;
import dinawall_app.model.DinaWallAppModel;
import dinawall_app.ui.DinaWallpaperComponent;
import dinawall_core.wallpaper.DinaWallpaper;


/**
 *
 * @author frederick
 */
public class DinawallApp extends Application{
    
    private Stage primaryStage;
    private HBox rootLayout;
    private FXMLLoader loader;
    
    private PrimarySceneController controller;
    private DinaWallAppModel dinawall_model;

    @Override
    public void start(Stage stage) throws Exception {
        
        dinawall_model = DinaWallAppModel.getInstance();
        
        this.primaryStage = stage;
        this.primaryStage.setTitle("DinaWall");
                
        init_primary_scene();
        loadDinaWallpapers();
    }

    private void init_primary_scene() {
        try{
            loader = new FXMLLoader();
            loader.setLocation(DinawallApp.class.getResource("/dinawall_app/ui/primary_scene.fxml"));
            
            rootLayout = loader.load();
            
            controller = loader.getController();
            controller.setMain(this);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * This method get all dinawallpaper installed and create a dinawallpaper_component
     * to load in the main scene
     * 
     */
    private void loadDinaWallpapers(){
        try{
           if(this.dinawall_model.getDinaWallpaperList().size()>0){
               for(DinaWallpaper dinawallpaper : this.dinawall_model.getDinaWallpaperList()){
                   
                    System.err.println(dinawallpaper.toString());
                    DinaWallpaperComponent component = new DinaWallpaperComponent();
                    component.setDinaWall(dinawallpaper);
                    controller.addDinawallPreviewComponent(component);
                } 
           }
        }catch(Exception e){
         e.printStackTrace();
        }
    }
    
    public Stage getMainStage(){
        return this.primaryStage;
    }
        
}
