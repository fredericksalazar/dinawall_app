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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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
    
    @FXML
    private VBox lefVBox; 
    
    private ScrollPane scrollPane;    
    private FlowPane flowPane;
    
    private final FileChooser fileChooser;    
    private final DinaWallCore dinawall_core;
    private final DinaWallAppModel dinawall_model;
    private DinaWallpaperComponent selectedComponent;

    public PrimarySceneController() {
        this.dinawall_core = DinaWallCore.getInstance();
        this.dinawall_model = DinaWallAppModel.getInstance();
        
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
        this.dinawall_core.setCurrentDinaWallpaper(this.dinawall_model.getSelectedWallpaper());
        
    }

    @FXML
    void deleteButtonPressed(ActionEvent event) {        
        this.dinawall_core.deleteDinaWallpaper(this.dinawall_model.getSelectedWallpaper());
        this.flowPane.getChildren().remove(this.selectedComponent);
    }
    
    @FXML
    void installButtonPressed(ActionEvent event){
        File file = fileChooser.showOpenDialog(this.mainApp.getMainStage());
        DinaWallpaper installed = this.dinawall_core.install_dinawallpaper(file.getAbsolutePath());
        
        if(installed != null){
            DinaWallpaperComponent installed_component = new DinaWallpaperComponent();
            installed_component.setDinaWall(installed);
            this.addDinawallPreviewComponent(installed_component);        
            System.out.println("new .din file is -> ");
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
                      dinawall_model.setSelectedWallpaper(component.getDinaWallpaperComponent());
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
     * @param main_app 
     */
    
    public void setMain(DinawallApp main_app){
        this.mainApp = main_app;
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
