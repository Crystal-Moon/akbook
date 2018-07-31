/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.start.AKRun;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class CoverController extends CtrlPrincipal implements Initializable {

    @FXML
    private ImageView close;
    @FXML
    private Label lblVersion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //TODO
         lblVersion.setText("Version: 1.0.4");
    }

    @FXML
    private void handlerCover(MouseEvent event) {
        super.main.ventanaInterna("book.fxml");
        AKRun.closePrimary();
    }
    
    @FXML
    private void handlerClose(MouseEvent event) {
        Stage thisS = (Stage) close.getScene().getWindow();
        thisS.close();        
    }

    @FXML
    private void handlerHelp(MouseEvent event) {
        super.main.ventanaInterna("help.fxml");
    }
    
}
