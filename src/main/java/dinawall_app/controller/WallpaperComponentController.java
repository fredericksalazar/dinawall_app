package dinawall_app.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class WallpaperComponentController implements Initializable {

    @FXML
    private Pane dinaWallComponent;

    @FXML
    private Label dinaWallPreviewTitle;

    @FXML
    private Label dinWallPreviewAutor;

    @FXML
    private Hyperlink dinaWallPreviewUrl;
    
    private Rectangle clip;
    

    @FXML
    void initialize() {
     
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setdinaWallPreviewImage(Image image){
    
        this.dinaWallComponent.setStyle("-fx-background-image:url("+image.getUrl()+");"
                                      + "-fx-background-size: 100% 100%;");
        
        clip = new Rectangle(270, 170);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        dinaWallComponent.setClip(clip);
          
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
