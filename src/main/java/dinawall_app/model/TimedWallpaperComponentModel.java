package dinawall_app.model;

public class TimedWallpaperComponentModel {

    private String urlImage;
    private String hour;
    private String minute;

    public TimedWallpaperComponentModel(){

    }
    public TimedWallpaperComponentModel(String url,
                                        String hour,
                                        String minute){
        this.urlImage = url;
        this.hour = hour;
        this.minute = minute;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTimed(){
        return hour+":"+minute;
    }
}
