package dinawall_app.ui;

import dinawall_app.controller.TimedWallpaperComponentController;
import dinawall_app.model.TimedWallpaperComponentModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class TimedWallpaperComponent extends AnchorPane {

    private final TimedWallpaperComponentModel timedWallpaperModel;
    private final  TimedWallpaperComponentController timedWallpaperControl;

    public TimedWallpaperComponent(){
        this.timedWallpaperModel = new TimedWallpaperComponentModel();
        this.timedWallpaperControl = new TimedWallpaperComponentController();
        init();
    }

    public void init(){
        try{
            FXMLLoader loader = new FXMLLoader(TimedWallpaperComponent.class.getResource("/dinawall_app/ui/dinawall_timedWallpaper_component.fxml"));
            loader.setController(timedWallpaperControl);
            Node component = loader.load();
            this.getChildren().add(component);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setModelData(){
        this.timedWallpaperModel.setUrlImage(timedWallpaperControl.getUrl());
        this.timedWallpaperModel.setHour(timedWallpaperControl.getHour());
        this.timedWallpaperModel.setMinute(timedWallpaperControl.getMinute());
    }

    public TimedWallpaperComponentModel getComponentModel(){
        return this.timedWallpaperModel;
    }

}
