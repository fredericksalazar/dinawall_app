/**
 * Copyright(C) Frederick Salazar Sanchez <fredefass01@gmail.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package dinawall_app;

import dinawall_core.DinaWallCore;

/**
 * This class is main input point for the DinaWall App
 * identify if execution is in UI Mode or console mode
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
                    DinawallApp.main(args);
                }

                if(args[0].equals("- daemon")){
                    DinaWallCore.getInstance().init_dinawall_daemon();
                }
            }else{
                DinawallApp.main(args);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
