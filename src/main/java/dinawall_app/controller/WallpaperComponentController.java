package dinawall_app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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
    private boolean pressed;
    private int totalPressed;
    
    private Image image;
    private String styleCss;

    @FXML
    void initialize() {
     
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void setdinaWallPreviewImage(Image image){
        this.image = image;
        this.totalPressed = 0;

        styleCss = "-fx-background-image:url("+image.getUrl()+");"+
                   "-fx-background-size: 100% 100%;";
        this.dinaWallComponent.setStyle(styleCss);
    }
    
    public void setdinaWallPreviewTitle(String title){
        this.dinaWallPreviewTitle.setText(title);
    }
    
    public void setBorderPaneColor(){
        this.dinaWallComponent.setStyle("-fx-border-color: black;");
    }
    
    public void setdinWallPreviewAutor(String author){
        this.dinWallPreviewAutor.setText(author);
    }
    
    public void setdinaWallPreviewUrl(String url){
        this.dinaWallPreviewUrl.setText(url);
    }

    public void setSelectedStyle(){

        String styleCss = "-fx-background-image:url("+image.getUrl()+");"
                        + "-fx-background-size: 100% 100%;" +
                          "-fx-border-color:#4361ee;" +
                          "-fx-border-width:3.5px";

        this.dinaWallComponent.setStyle(styleCss);
    }

    public void setUnSelectedStyle(){
        String styleCss = "-fx-background-image:url("+image.getUrl()+");"
                        + "-fx-background-size: 100% 100%;" +
                          "-fx-border-color:null;";

        this.dinaWallComponent.setStyle(styleCss);
    }
        
}
