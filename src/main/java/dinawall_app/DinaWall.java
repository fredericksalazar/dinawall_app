/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dinawall_app;

import dinawall_core.DinaWallCore;
import javafx.application.Application;

/**
 *
 * @author fredericksalazar
 */
public class DinaWall {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try{
            System.out.println("args ..."+args.length);
            
            if(args.length != 0){
                
                if(args[0].equals("- app")){
                    Application.launch(DinawallApp.class);
                }

                if(args[0].equals("- daemon")){
                    DinaWallCore.getInstance().init_dinawall_daemon();
                }
            }else{
                Application.launch(DinawallApp.class);
            }   
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
