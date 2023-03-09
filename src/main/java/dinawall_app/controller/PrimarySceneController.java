package dinawall_app.controller;

import dinawall_app.DinawallApp;
import dinawall_app.model.DinaWallAppModel;
import dinawall_app.ui.DinaWallpaperComponent;
import dinawall_core.DinaWallCore;
import dinawall_core.wallpaper.DinaWallpaper;
import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class PrimarySceneController {
    
    private DinawallApp mainApp;
    @FXML
    private Button applyButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button installButton;
    @FXML private MenuBar menuBar;
    @FXML private Menu menuEngine;
    @FXML private MenuItem miStartEngine;
    @FXML private MenuItem miStopEngine;
    @FXML private MenuItem miRestartEngine;
    @FXML
    private VBox lefVBox; 
    
    private ScrollPane scrollPane;    
    private FlowPane flowPane;
    
    private final FileChooser fileChooser;    
    private final DinaWallCore dinawallcore;
    private final DinaWallAppModel dinawallmodel;
    private DinaWallpaperComponent selectedComponent;

    public PrimarySceneController() {
        this.dinawallcore = DinaWallCore.getInstance();
        this.dinawallmodel = DinaWallAppModel.getInstance();
        
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files","*.json")
        );        
    }
    
    @FXML
    void initialize() {
        setDinaWallPaneComponent();
    }
    
    @FXML
    void applyButtonPressed(ActionEvent event) {
        this.dinawallcore.setCurrentDinaWallpaper(this.dinawallmodel.getSelectedWallpaper());
        
    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {        
        this.dinawallcore.deleteDinaWallpaper(this.dinawallmodel.getSelectedWallpaper());
        this.flowPane.getChildren().remove(this.selectedComponent);
    }
    
    @FXML
    void installButtonPressed(ActionEvent event){
        File file = fileChooser.showOpenDialog(this.mainApp.getMainStage());
        DinaWallpaper installed = this.dinawallcore.install_dinawallpaper(file.getAbsolutePath());
        
        if(installed != null){
            DinaWallpaperComponent installedcomponent = new DinaWallpaperComponent();
            installedcomponent.setDinaWall(installed);
            this.addDinawallPreviewComponent(installedcomponent);        
            System.out.println("new .din file is -> ");
        }
    }

    @FXML
    public void stop_dinawall_engine(){
        try{
            dinawallcore.stop_daemon();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void start_dinawall_engine(){
        try{
            dinawallcore.start_daemon();
        }catch (Exception e){

        }
    }

    @FXML
    public void restart_dinawall_engine(){
        try{
            stop_dinawall_engine();
            start_dinawall_engine();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * This method add a new DinawallPreviewComponent to the flowpane where
     * 
     * @param component 
     */
    
    public void addDinawallPreviewComponent(DinaWallpaperComponent component){        
        try{
            if(component != null){                
                component.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                      selectedComponent = component;
                      dinawallmodel.setSelectedWallpaper(component.getDinaWallpaperComponent());
                    }
                });
                
                component.setEffect(new DropShadow(15, Color.GRAY));
                
                this.flowPane.getChildren().add(component);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method set a main app into primary scene controller
     * 
     * @param mainapp 
     */
    
    public void setMain(DinawallApp mainapp){
        this.mainApp = mainapp;
    }

    
    /**
     * This method set a scrollpane and flowpae where are loaded the dynawallpapersCompoentes
     * by every .din file loaded
     * 
     */
    
    private void setDinaWallPaneComponent() {
        
        this.flowPane = new FlowPane();                    
        this.flowPane.setHgap(10);
        this.flowPane.setVgap(10);
        this.flowPane.setPadding(new Insets(5,5,5,5));
        
        scrollPane = new ScrollPane();
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(1000);
        scrollPane.viewportBoundsProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                flowPane.setPrefWidth(bounds.getWidth());
                flowPane.setPrefHeight(bounds.getHeight());
            }
        });
        
        this.scrollPane.setContent(flowPane);
        this.lefVBox.getChildren().add(scrollPane);
    }
    
}
