package dinawall_app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;

public class TimedWallpaperComponentController {

    @FXML
    private TextField tfImageUrl;

    @FXML
    private ComboBox cbHour;

    @FXML
    private ComboBox cbMinutes;


    public TimedWallpaperComponentController(){
    }

    @FXML
    void initialize() {
        configComponent();
    }

    private void configComponent() {

        //set the values to hours
        for(int h = 0; h < 24; h++){
            this.cbHour.getItems().add(String.format("%02d", h));
        }

        //set the values to minutes
        for(int m = 0; m < 60; m ++){
            this.cbMinutes.getItems().add(String.format("%02d", m));
        }
    }

    @FXML
    public void showFileDialog(){
        FileChooser imageSelector = new FileChooser();
        imageSelector.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files","*.jpeg","*.png", "*.jpg"));
        File file = imageSelector.showOpenDialog(null);

        if(file.getAbsolutePath() != null){
            this.tfImageUrl.setText(file.getAbsolutePath());
        }
    }

    public String getUrl(){
        return this.tfImageUrl.getText();
    }

    public String getHour(){
        return String.valueOf(this.cbHour.getSelectionModel().getSelectedItem());
    }

    public String getMinute(){
        return String.valueOf(this.cbMinutes.getSelectionModel().getSelectedItem());
    }

}
