package dinawall_app.model;

import dinawall_app.ui.TimedWallpaperComponent;
import dinawall_core.wallpaper.TimedWallpaper;

import java.util.ArrayList;

public class CreateToolModel {

    public static CreateToolModel createTooModel;

    private ArrayList<TimedWallpaperComponent> listTimedComponents;

    private CreateToolModel(){
            this.listTimedComponents = new ArrayList<>();
    }

    public void addNewTimedWallpaperComponent(TimedWallpaperComponent component){
        this.listTimedComponents.add(component);
        System.out.println("(Add) New size to TimedWallpaperComponent list -> "+listTimedComponents.size());
    }

    public void removeTimedWallpaperComponent(){
        this.listTimedComponents.remove(this.listTimedComponents.size()-1);
        System.out.println("(Rem) New size to TimedWallpaperComponent list -> "+listTimedComponents.size());
    }

    public ArrayList<TimedWallpaperComponent> getListTimedComponents(){
        return this.listTimedComponents;
    }

    public static CreateToolModel getInstance(){
        if (createTooModel == null){
            createTooModel = new CreateToolModel();
        }

        return createTooModel;
    }
}
