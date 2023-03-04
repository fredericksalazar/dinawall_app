/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import dinawall_app.controller.PrimarySceneController;
import dinawall_app.model.DinaWallAppModel;
import dinawall_app.ui.DinaWallpaperComponent;
import dinawall_core.wallpaper.DinaWallpaper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

public class DinawallApp extends Application{
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private FXMLLoader loader;
    
    private PrimarySceneController controller;
    private DinaWallAppModel dinawallmodel;

    @Override
    public void start(Stage stage) throws Exception {
        
        Platform.setImplicitExit(false);
        
        dinawallmodel = DinaWallAppModel.getInstance();
        
        primaryStage = stage;
        primaryStage.setTitle("DinaWall");
                
        init_primary_scene();
        startListenerInstance();
        loadDinaWallpapers();
    }

    private void init_primary_scene() {
        try{
            loader = new FXMLLoader();
            loader.setLocation(DinawallApp.class.getResource("/dinawall_app/ui/dinaWall_app.fxml"));
            
            rootLayout = loader.load();
            
            controller = loader.getController();
            controller.setMain(this);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(false);
            //primaryStage.setMaximized(false);
            //primaryStage.setResizable(false);
            primaryStage.setOnCloseRequest(eh->{
                this.primaryStage.hide();
            });
            
            primaryStage.show();
            
        }catch(IOException e){
            System.err.println("error initprmaryscene -> "+e);
        }
    }
    
    /**
     * This method get all dinawallpaper installed and create a dinawallpapercomponent
     * to load in the main scene
     * 
     */
    private void loadDinaWallpapers(){
        try{
           if(this.dinawallmodel.getDinaWallList().size()>0){
               for(DinaWallpaper dinawallpaper : this.dinawallmodel.getDinaWallList()){
                   
                    System.err.println(dinawallpaper.toString());
                    DinaWallpaperComponent component = new DinaWallpaperComponent();
                    component.setDinaWall(dinawallpaper);
                    controller.addDinawallPreviewComponent(component);
                } 
           }
        }catch(Exception e){
            System.err.println("Error loaddinawallpapers -> "+e);
        }
    }
    
    public Stage getMainStage(){
        return this.primaryStage;
    }
    
    
    /**
     * This method is used to have a single instance of the app, when
     * dinawalldaemon has been started as service daemon and the user 
     * open a new instance of dinawallapp then the system validate and 
     * invoke a server to open UI.
     */
    
    private void startListenerInstance(){
        Thread instanceListener = new Thread(()->{
            
        try {
            System.out.println("creating listener instance  ...");
            
            ServerSocket server = new ServerSocket(35531);
            
            try{
                
                while(true){
                    Socket client = server.accept();

                    BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String request = input.readLine();

                    System.err.println("Request from client -> "+request);
                    
                    if(request.equals("start app")){
                    
                        System.err.println("show the dinawallapp from server ...");

                        Platform.runLater(() ->{
                            this.primaryStage.show();
                            this.primaryStage.toFront();
                            System.out.println("Launch the new ui instance ...");
                        });
                    }
                }
                
                
            }catch(IOException io){
                System.out.println("dinawallapp.controller.DinaWallServer.run()");
            }
        } catch(BindException e){
            try {
                System.err.println("Other instance is runing in this moment, then open the app ui ...");
                
                /**
                 * create a socket client to send message to other instance that
                 * show the app ui
                 */ 
                
                try (Socket clientSocket = new Socket("127.0.0.1", 35531); 
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {
                     out.println("start app");
                    }
            } catch (IOException ex) {
                System.out.println("dinawallapp.DinawallApp.startListenerInstance()");
            }
            
            System.exit(0);
            
        } catch (IOException ex) {
            System.out.println("dinawallapp.DinawallApp.startListenerInstance()");
        }
        
        },"dinawallserverinstance");
        
        instanceListener.setDaemon(true);
        instanceListener.start();
    }
        
}
