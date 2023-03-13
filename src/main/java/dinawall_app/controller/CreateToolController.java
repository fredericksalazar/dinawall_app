package dinawall_app.controller;

import dinawall_app.model.CreateToolModel;
import dinawall_app.ui.TimedWallpaperComponent;
import dinawall_core.DinaWallCore;
import dinawall_core.wallpaper.DinaWallpaper;
import dinawall_core.wallpaper.TimedWallpaper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;


public class CreateToolController {


    private final CreateToolModel createToolModel;
    private final DinaWallCore dinaWallCore;

    @FXML
    private TextField projectName;

    @FXML
    private TextField userName;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField urlProject;

    @FXML
    private TextField licenceProject;

    @FXML
    public VBox vBoxScrollPane;

    private int index = 0;
    private TimedWallpaperComponent twComponent;

    public CreateToolController(){
         createToolModel = CreateToolModel.getInstance();
         dinaWallCore = DinaWallCore.getInstance();
    }

    @FXML
    void initialize(){
        addDinaWallpaperConf();
    }

    @FXML
    public void addDinaWallpaperConf(){
        try{
            //validate that is first timedWallpaper configure
            if (index > 0){
                twComponent.setModelData();
                createToolModel.addNewTimedWallpaperComponent(twComponent);
                System.err.println("ADICION PRIMER COMPONENTE - TOTAL CLICK ADD -> "+index);
                System.err.println("COMPONENTE AGREGADO -> "+twComponent.getComponentModel().getUrlImage());
            }

            twComponent = new TimedWallpaperComponent();
            vBoxScrollPane.getChildren().add(twComponent);
            index += 1;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void removeDinaWallpaperConf(){
        if (vBoxScrollPane.getChildren().size()-1 > 0){
            vBoxScrollPane.getChildren().remove(vBoxScrollPane.getChildren().size()-1);
            createToolModel.removeTimedWallpaperComponent();
            index -= 1;
        }else {
            index = 0;
        }
    }

    public void createDinaWallpaper(){
        addDinaWallpaperConf();
        ArrayList<TimedWallpaper> listTimedWallpaper = new ArrayList<>();
        LocalDate date = LocalDate.now();

        for (TimedWallpaperComponent timedComponent : createToolModel.getListTimedComponents()) {
            timedComponent.setModelData();
            listTimedWallpaper.add(new TimedWallpaper(timedComponent.getComponentModel().getUrlImage(),
                                                      timedComponent.getComponentModel().getTimed()));

            System.out.println("Imagen a ajustar -> "+timedComponent.getComponentModel().getUrlImage());
        }

        DinaWallpaper dinaWallpaper = new DinaWallpaper(projectName.getText(),
                                                        urlProject.getText(),
                                                        String.valueOf(date),
                                                        userName.getText(),
                                                        userEmail.getText(),
                                                        licenceProject.getText(),
                                                        null,
                                                        listTimedWallpaper);

        System.out.println("ESTRUCTURA JSON -> \n"+dinaWallpaper.toJson());

        dinaWallCore.createNewDinaWallpaper(dinaWallpaper);

    }

}
