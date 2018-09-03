/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.base.ErrorAK;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class HelpController extends CtrlPrincipal implements Initializable {

    @FXML
    private Label lblHelp; 
    @FXML
    private Hyperlink database;
    @FXML
    private Hyperlink overfly;
    @FXML
    private Hyperlink puertoSkandia;
    @FXML
    private Hyperlink consejos;
    @FXML
    private Hyperlink pageOficial;
    @FXML
    private Label lblAutor;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblHelp.setText("Aura Kingdom - Recipe's Book\n\n" +
        "Seccion 'Buscar' - 'Alimentos'\n" +
        "Seleccione todos los status que desee encontrar. Precione el boton 'Busar' para visualizar una lista de todos los resultados que contengan los atributos seleccionados.\n" +
        "Seleccione cualquier item de la tabla para ver detalles de la preparacion en la seccion 'Cocina'.\n" +
        "\n" +
        "Seccion 'Buscar' - 'Mineria'\n" +
        "(Proximamente)\n" +
        "\n" +
        "Seccion 'Cocina'\n" +
        "Haga click en cualquiera de los items para ver detalles como ubicacion de los ingredientes, ubicacion de los NPC y detalles de los status otorgados por el alimento resultante.\n" +
        "\n" +
        "Seccion 'Herreria'\n" +
        "(Proximamente)\n\n");
        lblHelp.setFocusTraversable(true); //hacer foco en el primer label
        lblAutor.setText("Aura Kingdom Recipe's Book ©2018\nCreated by CrystalMoon.");
        
        overfly.setOnAction((ActionEvent e) -> {    
            try {
                Desktop.getDesktop().browse(new URI("http://overflyx.com/"));
            } catch (IOException | URISyntaxException e1) {
                ErrorAK.errorDeRed();
            }
        });
    
        database.setOnAction((ActionEvent e) -> {    
            try {
                Desktop.getDesktop().browse(new URI("http://www.aurakingdom-db.com/"));
            } catch (IOException | URISyntaxException e1) {
                ErrorAK.errorDeRed();
            }
        });
        
        puertoSkandia.setOnAction((ActionEvent e) -> {    
            try {
                Desktop.getDesktop().browse(new URI("http://es.portskandia.com/"));
            } catch (IOException | URISyntaxException e1) {
                ErrorAK.errorDeRed();
            }
        });
        
        consejos.setOnAction((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://sites.google.com/site/aurakingdomguia/"));
            } catch (IOException | URISyntaxException e1) {
                ErrorAK.errorDeRed();
            }
        });
        
        pageOficial.setOnAction((ActionEvent e) -> {
            try {
                Desktop.getDesktop().browse(new URI("https://es.aurakingdom.aeriagames.com/"));
            } catch (IOException | URISyntaxException e1) {
                ErrorAK.errorDeRed();
            }
        });
    }    

}
