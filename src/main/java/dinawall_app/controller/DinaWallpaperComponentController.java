package dinawall_app.controller;

import dinawall_app.model.DinaWallAppModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class DinaWallpaperComponentController implements Initializable {

    @FXML
    private Pane dinaWallComponent;

    @FXML
    private ImageView dinaWallPreviewImage;

    @FXML
    private Label dinaWallPreviewTitle;

    @FXML
    private Label dinWallPreviewAutor;

    @FXML
    private Hyperlink dinaWallPreviewUrl;
    
    private Rectangle clip;
    

    @FXML
    void initialize() {
        assert dinaWallComponent != null : "fx:id=\"dinaWallComponent\" was not injected: check your FXML file 'dinawall_wallpaper_component.fxml'.";
        assert dinaWallPreviewImage != null : "fx:id=\"dinaWallPreviewImage\" was not injected: check your FXML file 'dinawall_wallpaper_component.fxml'.";
        assert dinaWallPreviewTitle != null : "fx:id=\"dinaWallPreviewTitle\" was not injected: check your FXML file 'dinawall_wallpaper_component.fxml'.";
        assert dinWallPreviewAutor != null : "fx:id=\"dinWallPreviewAutor\" was not injected: check your FXML file 'dinawall_wallpaper_component.fxml'.";
        assert dinaWallPreviewUrl != null : "fx:id=\"dinaWallPreviewUrl\" was not injected: check your FXML file 'dinawall_wallpaper_component.fxml'.";

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setdinaWallPreviewImage(Image image){
        this.dinaWallPreviewImage.setImage(image);
        clip = new Rectangle(image.getWidth(), image.getHeight());
        clip.setArcWidth(10);
        clip.setArcHeight(10);
        dinaWallPreviewImage.setClip(clip);
        
    }
    
    public void setdinaWallPreviewTitle(String title){
        this.dinaWallPreviewTitle.setText(title);
    }
    
    public void setBorderPaneColor(){
        this.dinaWallComponent.setStyle("-fx-border-color: black");
    }
    
    public void setdinWallPreviewAutor(String author){
        this.dinWallPreviewAutor.setText(author);
    }
    
    public void setdinaWallPreviewUrl(String url){
        this.dinaWallPreviewUrl.setText(url);
    }
        
}
