package dinawall_app.controller;

import dinawall_app.DinawallApp;
import dinawall_app.model.DinaWallAppModel;
import dinawall_app.ui.WallpaperComponent;
import dinawall_core.DinaWallCore;
import dinawall_core.wallpaper.DinaWallpaper;
import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimarySceneController {

    private DinawallApp mainApp;
    @FXML
    private VBox lefVBox;

    private FlowPane flowPane;
    
    private final FileChooser fileChooser;    
    private final DinaWallCore dinawallcore;
    private final DinaWallAppModel dinawallmodel;
    private WallpaperComponent selectedComponent;
    private Stage dinawallToolStg;

    public PrimarySceneController() {
        this.dinawallcore = DinaWallCore.getInstance();
        this.dinawallmodel = DinaWallAppModel.getInstance();
        this.loadWallpaperCreateTool();
        
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
            WallpaperComponent installedcomponent = new WallpaperComponent();
            installedcomponent.setDinaWall(installed);
            this.addDinawallPreviewComponent(installedcomponent);        
            System.out.println("new .din file is -> ");
        }
    }

    @FXML
    public void newDynamicWallpaper(){
        dinawallToolStg.show();
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
    
    public void addDinawallPreviewComponent(WallpaperComponent component){
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

        ScrollPane scrollPane = new ScrollPane();
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
        
        scrollPane.setContent(flowPane);
        this.lefVBox.getChildren().add(scrollPane);
    }

    public void loadWallpaperCreateTool(){
        try{
            CreateToolController createToolController = new CreateToolController();
            FXMLLoader loader = new FXMLLoader(PrimarySceneController.class.getResource("/dinawall_app/ui/dinawall_create_tool.fxml"));
            VBox vCreateTool = loader.load();
            dinawallToolStg = new Stage();
            dinawallToolStg.setResizable(false);
            createToolController.addDinaWallpaperConf();
            dinawallToolStg.setScene(new Scene(vCreateTool));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showAbout(){
        try{
            FXMLLoader loader = new FXMLLoader(PrimarySceneController.class.getResource("/dinawall_app/ui/dinawall_about.fxml"));
            BorderPane aboutPane = loader.load();
            Stage dinawallAboutStg = new Stage();
            dinawallAboutStg.setResizable(false);
            dinawallAboutStg.setScene(new Scene(aboutPane));
            dinawallAboutStg.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
