/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.complementarias.Origen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class MapController extends CtrlPrincipal implements Initializable {

    @FXML
    private ImageView imgMap1;
    @FXML
    private ImageView imgMap2;
    @FXML
    private TabPane paneMaps;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;

    private static Origen ubicacionElegida;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imgMap1.setImage(new Image(ubicacionElegida.getArchivoMapa1()));
        tab1.setText(ubicacionElegida.getNameMap1());
        if (ubicacionElegida.map2Exist()) {
            imgMap2.setImage(new Image(ubicacionElegida.getArchivoMapa2()));
            tab2.setText(ubicacionElegida.getNameMap2());
        } else {
            tab2.setText("No disponible");
            tab2.setDisable(true);
        }
        
        if(ubicacionElegida.map3Exist()) {
            Tab tab3 = new Tab(ubicacionElegida.getNameMap3());
            paneMaps.getTabs().add(tab3);
            if(ubicacionElegida.map4Exist()) {
                Tab tab4 = new Tab(ubicacionElegida.getNameMap4());
                paneMaps.getTabs().add(tab4);
            }
        }
    }

    public static void setarUbicacion(Origen ubi) {
        ubicacionElegida = ubi;
    }

}
