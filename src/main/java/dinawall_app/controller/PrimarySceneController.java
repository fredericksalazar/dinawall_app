/**
 * Copyright(C) Frederick Salazar Sanchez <fredefass01@gmail.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import javafx.scene.Node;
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

/**
 * This class ins a controller of the main UI DinaWall App
 * add support for all operations.
 */

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

    /**
     * Initializes the fundamental UI components
     */
    @FXML
    void initialize() {
        setDinaWallPaneComponent();
    }

    /**
     * Remove a DinaWallPaper selected
     * @param event
     */
    @FXML
    void deleteButtonPressed(ActionEvent event) {        
        this.dinawallcore.deleteDinaWallpaper(this.dinawallmodel.getSelectedWallpaper());
        this.flowPane.getChildren().remove(this.selectedComponent);
    }

    /**
     * When invoked then open process to install a new Dynamic Wallpaper
     * en json format
     * @param event
     */
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

    /**
     * Open a tool to create a new Dynamic Wallpaper using
     * UI of DinaWall
     */
    @FXML
    public void newDynamicWallpaper(){
        dinawallToolStg.show();
    }

    /**
     * Stop the daemon that execute cron jobs
     * to set wallpapers
     */
    @FXML
    public void stop_dinawall_engine(){
        try{
            dinawallcore.stop_daemon();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Start the daemon that execute cron jobs
     * to set wallpapers
     */
    @FXML
    public void start_dinawall_engine(){
        try{
            dinawallcore.start_daemon();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Restart the daemon that execute cron jobs
     * to set wallpapers
     */
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
                      updateSelectedComponent();
                      dinawallmodel.setSelectedWallpaper(component.getDinaWallpaperComponent());
                      dinawallcore.setCurrentDinaWallpaper(dinawallmodel.getSelectedWallpaper());
                    }
                });
                
                component.setEffect(new DropShadow(15, Color.GRAY));
                flowPane.getChildren().add(component);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is used to update border color of DynaWallpaperComponent
     * when is selected. then deselected all components and select the
     * new component.
     */
    private void updateSelectedComponent(){
        for (Node child : flowPane.getChildren()) {
            WallpaperComponent component = (WallpaperComponent) child;
            component.setUnselected();
        }
        selectedComponent.setSelected();
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

    /**
     * This method load de UI Tool to create a new Dynamic Wallpapers
     */
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

    /**
     * This method show about info in DynaWallApp
     */
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
