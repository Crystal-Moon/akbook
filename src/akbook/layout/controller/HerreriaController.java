/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.base.ErrorAK;
import akbook.entidades.base.Item;
import akbook.entidades.complementarias.Value;
import akbook.entidades.refinacion.Equipamiento;
import akbook.entidades.refinacion.Mineral;
import akbook.entidades.refinacion.Nucleo;
import akbook.entidades.refinacion.SemiFacturado;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class HerreriaController extends CtrlPrincipal implements Initializable {

    @FXML
    private ImageView item1;
    @FXML
    private ImageView item2;
    @FXML
    private ImageView item3;
    @FXML
    private ImageView item4;
    @FXML
    private ImageView item5;
    @FXML
    private ImageView arrow0;
    @FXML
    private ImageView arrow1;
    @FXML
    private ImageView arrow2;
    @FXML
    private ImageView arrow3;
    @FXML
    private ImageView arrow4;
    @FXML
    private Label nombreItem;
    @FXML
    private Label lblNvlItem;
    @FXML
    private Label lblStats;
    @FXML
    private Label lblSetCompleto;
    @FXML
    private ImageView imgNucleo;
    @FXML
    private ImageView imgBase;
    @FXML
    private AnchorPane anchorNucleo;
    @FXML
    private ImageView imgNucleoDer;
    @FXML
    private ImageView imgAleacionNucleo;
    @FXML
    private ImageView imgM1Nucleo;
    @FXML
    private ImageView imgM2Nucleo;
    @FXML
    private Label cAleacion;
    @FXML
    private Label lblCM1nucleo;
    @FXML
    private Label lblCM2nucleo;
    @FXML
    private Label lblCM3nucleo;
    @FXML
    private ImageView imgSF;
    @FXML
    private ImageView imgM1SF;
    @FXML
    private ImageView imgM2SF;
    @FXML
    private ImageView imgM3SF;
    @FXML
    private Label lblCM1SF;
    @FXML
    private Label lblCM2SF;
    @FXML
    private Label lblCM3SF;
    @FXML
    private Label lblParte;
    @FXML
    private ImageView imgDetalles;
    @FXML
    private Label nombreDetalles;
    @FXML
    private AnchorPane anchorInfo;
    @FXML
    private Label lblInfo;
    @FXML
    private AnchorPane anchorMineral;
    @FXML
    private Label lblNombreOrigen;
    @FXML
    private ImageView imgOrigen;
    @FXML
    private Button btnUbicacion;

    private static Equipamiento equipoDerecha;
    private static Equipamiento equipo0;
    private static Equipamiento equipo1;
    private static Equipamiento equipo2;
    private static Equipamiento equipo3;
    private static Equipamiento equipo4;
    private final ArrayList<ImageView> arrows = new ArrayList();
    @FXML
    private ImageView imgM3Nucleo;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnUbicacion.setDisable(true);
        clearAll();
        cargarItem(equipo0, 0);
        
    }

    @FXML
    private void handlerBtnSet(ActionEvent event) {
        ArrayList<Equipamiento> setCompleto = new ArrayList();
        int idSet = equipo0.getSet();
        String parteElegida = equipo0.getParte();
        try {
            setCompleto = Equipamiento.traerSet(idSet, parteElegida);
        } catch (SQLException ex) {
            ErrorAK.errorBaseDatos();
        } catch (Exception ex) {
            ErrorAK.errorGenerico();
        }
        if (!(setCompleto.isEmpty())) {
            equipo1 = setCompleto.get(0);
            equipo2 = setCompleto.get(1);
            equipo3 = setCompleto.get(2);
            equipo4 = setCompleto.get(3);
            cargarSet();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText("Este articulo no posee algun set conocido");
            alert.showAndWait();
        }
    }

    @FXML
    private void handlerBtnAtributos(ActionEvent event) {
        super.main.ventanaInterna("prefijos.fxml");
    }

    @FXML
    private void handlerBtnUbicacion(ActionEvent event) {
        super.main.ventanaInterna("map.fxml");
    }

    public static void setEquipo0(Equipamiento equipo) {
        HerreriaController.equipo0 = equipo;
    }

    private void cambiarStacke(AnchorPane pane) {
        anchorInfo.setVisible(false);
        anchorMineral.setVisible(false);
        anchorNucleo.setVisible(false);
        pane.setVisible(true);
    }

    private void cargarItem(Equipamiento equipo, int arrow) {

        if (equipo!=null) {
            Image imgItem1 = new Image(getClass().getResourceAsStream(equipo0.getArchivo()));
            this.item1.setImage(imgItem1);
            this.item1.setStyle(equipo0.getColorBorde().getStyle());
            equipoDerecha=equipo;
    //panel Izquierdo            
            this.nombreItem.setText(equipo.getNombre());
            this.lblParte.setText(equipo.getParte());
            verArrow(arrow);
            Value status = equipo.getStat();
            for (String atributo : status.atributo) {
                String stat = "";
                stat = stat + "\n" + atributo;
                lblStats.setText(stat);
            }
            Value statSetCompleto=null;
            try{
                statSetCompleto=Value.buscarStat("stats-set.txt", equipo.getSet());
                } catch (IOException ex) {
                ErrorAK.errorTxt("statsSets");
                }
            for (String atributo : statSetCompleto.atributo) {
                String statSet = "";
                statSet = statSet + "\n" + atributo;
                lblSetCompleto.setText(statSet);
            }   
            if(equipo.isRefinado()){
                Image nucleo = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getArchivo()));
                this.imgNucleo.setImage(nucleo);
                this.imgNucleo.setStyle(equipo.getNucleo().getColorBorde().getStyle());
                Image base = new Image(
                        getClass().getResourceAsStream(equipo.getBase().getArchivo()));
                this.imgBase.setImage(base);  
                this.imgBase.setStyle(equipo.getBase().getColorBorde().getStyle());
    //panel Derecho
        //--Nucleo
                Image nucleoDer = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getArchivo()));
                this.imgNucleoDer.setImage(nucleoDer);
                this.imgNucleoDer.setStyle(equipo.getNucleo().getColorBorde().getStyle());
                Image aleacion = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getAleacion().getArchivo()));
                this.imgAleacionNucleo.setImage(aleacion);
                this.imgAleacionNucleo.setStyle(equipo.getNucleo().getAleacion().getColorBorde().getStyle());
                Image nM1 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getMineral1().getArchivo()));
                this.imgM1Nucleo.setImage(nM1);
                this.imgM1Nucleo.setStyle(equipo.getNucleo().getMineral1().getColorBorde().getStyle());
                Image nM2 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getMineral2().getArchivo()));
                this.imgM2Nucleo.setImage(nM2);
                this.imgM2Nucleo.setStyle(equipo.getNucleo().getMineral2().getColorBorde().getStyle());
                Image nM3 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getMineral3().getArchivo()));
                this.imgM3Nucleo.setImage(nM3);
                this.imgM3Nucleo.setStyle(equipo.getNucleo().getMineral3().getColorBorde().getStyle());
                
                this.cAleacion.setText(String.valueOf(equipo.getNucleo().getCantAleacion()));
                this.lblCM1nucleo.setText(String.valueOf(equipo.getNucleo().getCantM1()));
                this.lblCM2nucleo.setText(String.valueOf(equipo.getNucleo().getCantM2()));
                this.lblCM3nucleo.setText(String.valueOf(equipo.getNucleo().getCantM3()));
            
          //-- Aleacion semifacturada
                Image sf = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getAleacion().getArchivo()));
                this.imgSF.setImage(sf);
                this.imgSF.setStyle(equipo.getNucleo().getAleacion().getColorBorde().getStyle());
                Image sfM1 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getAleacion().getArchivo()));
                this.imgM1SF.setImage(sfM1);
                this.imgM1SF.setStyle(equipo.getNucleo().getAleacion().getMineral1().getColorBorde().getStyle());
                Image sfM2 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getMineral1().getArchivo()));
                this.imgM2SF.setImage(sfM2);
                this.imgM2SF.setStyle(equipo.getNucleo().getAleacion().getMineral2().getColorBorde().getStyle());
                Image sfM3 = new Image(
                        getClass().getResourceAsStream(equipo.getNucleo().getMineral2().getArchivo()));
                this.imgM3SF.setImage(sfM3);
                this.imgM3SF.setStyle(equipo.getNucleo().getAleacion().getMineral3().getColorBorde().getStyle());

                this.lblCM1SF.setText(String.valueOf(equipo.getNucleo().getAleacion().getCantM1()));
                this.lblCM2SF.setText(String.valueOf(equipo.getNucleo().getAleacion().getCantM2()));
                this.lblCM3SF.setText(String.valueOf(equipo.getNucleo().getAleacion().getCantM3()));    
            } else{
                clearDer();
            }
        } else {
            clearAll();
        }
    }

    private void verArrow(int index) {
        arrows.forEach((arrow) -> {
            arrow.setVisible(false);
        });
        arrows.get(index).setVisible(true);
    }

    private void cargarSet() {
        try {
            if(equipo1!=null){
                Image set1 = new Image(getClass().getResourceAsStream(equipo1.getArchivo()));
                item2.setImage(set1);
                item2.setStyle(equipo1.getColorBorde().getStyle());
            } else item2.setStyle("");
            
            if(equipo2!=null){
                Image set2 = new Image(getClass().getResourceAsStream(equipo2.getArchivo()));
                item3.setImage(set2);
                item3.setStyle(equipo2.getColorBorde().getStyle());
            } else item3.setStyle("");
            
            if(equipo3!=null){
                Image set3 = new Image(getClass().getResourceAsStream(equipo3.getArchivo()));
                item4.setImage(set3);
                item4.setStyle(equipo3.getColorBorde().getStyle());
            } else item4.setStyle("");
            
            if(equipo4!=null){
                Image set4 = new Image(getClass().getResourceAsStream(equipo4.getArchivo()));
                item5.setImage(set4);
                item5.setStyle(equipo4.getColorBorde().getStyle());
            } else item5.setStyle("");
            
        } catch (NullPointerException ex) {          
            ErrorAK.errorGenerico();
        }
    }

    private void clearAll() {

        clearIzq();
        clearDer();
        arrows.add(0, arrow0);
        arrows.add(1, arrow1);
        arrows.add(2, arrow2);
        arrows.add(3, arrow3);
        arrows.add(4, arrow4);
        
        arrows.forEach((arrow) -> {
            arrow.setVisible(false);
        });
    }
    
    private void clearIzq(){
        ArrayList<ImageView> images = new ArrayList();
        images.add(item1);
        images.add(item2);
        images.add(item3);
        images.add(item4);
        images.add(item5);
        
        images.forEach((img) -> {
            img.setImage(null);
            img.setStyle("");
        });
         
        ArrayList<Label> labels = new ArrayList();
        labels.add(lblParte);
        labels.add(nombreItem);
        labels.add(lblNvlItem);
        labels.add(lblStats);
        labels.add(lblSetCompleto);
        
        labels.forEach((lbl) -> {
            lbl.setText("");
        });
    }
    
    private void clearDer(){
        ArrayList<ImageView> images = new ArrayList();
        images.add(imgNucleo);
        images.add(imgBase);
        images.add(imgNucleoDer);
        images.add(imgAleacionNucleo);
        images.add(imgM1Nucleo);
        images.add(imgM2Nucleo);
        images.add(imgM3Nucleo);
        images.add(imgSF);
        images.add(imgM1SF);
        images.add(imgM2SF);
        images.add(imgM3SF);
        images.add(imgDetalles);
        images.add(imgOrigen);
        
        images.forEach((img) -> {
            img.setImage(null);
            img.setStyle("");
        });
        
        ArrayList<Label> labels = new ArrayList();
        labels.add(cAleacion);
        labels.add(lblCM1nucleo);
        labels.add(lblCM2nucleo);
        labels.add(lblCM3nucleo);
        labels.add(nombreDetalles);
        labels.add(lblInfo);
        labels.add(lblNombreOrigen);
        labels.add(lblCM1SF);
        labels.add(lblCM2SF);
        labels.add(lblCM3SF);
        
        labels.forEach((lbl) -> {
            lbl.setText("");
        });
    }
    
    @FXML
    private void handlerNucleo(MouseEvent event) {
        Nucleo nucleo=new Nucleo();
        nucleo=equipoDerecha.getNucleo();
        cambiarStacke(anchorNucleo);
        setearDetalles(nucleo);
    }

    @FXML
    private void handlerBase(MouseEvent event) {
        if(equipoDerecha.isRefinado()){
            Equipamiento base=new Equipamiento();
            base=equipoDerecha.getBase();
            setearDetalles(base);
        }
    }

    @FXML
    private void handlerItem1(MouseEvent event) {
        cargarItem(equipo0, 0);
    }

    @FXML
    private void handlerItem2(MouseEvent event) {
        cargarItem(equipo1, 1);
    }

    @FXML
    private void handlerItem3(MouseEvent event) {
        cargarItem(equipo2, 2);
    }

    @FXML
    private void handlerItem4(MouseEvent event) {
        cargarItem(equipo3, 3);
    }

    @FXML
    private void handlerItem5(MouseEvent event) {
        cargarItem(equipo4, 4);
    }

    @FXML
    private void handlerSemiFacturado(MouseEvent event) {
        SemiFacturado sf=new SemiFacturado();
        cambiarStacke(anchorInfo);
        setearDetalles(sf);
    }

    @FXML
    private void handlerMineralNucleo1(MouseEvent event) {       
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getMineral1();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
        
    }

    @FXML
    private void handlerMineralNucleo2(MouseEvent event) {
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getMineral2();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
    }

    @FXML
    private void handlerMineralNucleo3(MouseEvent event) {
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getMineral3();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
    }
    
    @FXML
    private void handlerMineralSemi1(MouseEvent event) {
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getAleacion().getMineral1();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
    }

    @FXML
    private void handlerMineralSemi2(MouseEvent event) {
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getAleacion().getMineral2();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
    }

    @FXML
    private void handlerMineralSemi3(MouseEvent event) {
        Mineral mineral=new Mineral();
        mineral=equipoDerecha.getNucleo().getAleacion().getMineral3();
        cambiarStacke(anchorMineral);
        setearDetalles(mineral);
    }

    private void setearDetalles(Item item) {
        
        Image detalle=new Image(getClass().getResourceAsStream(item.getArchivo()));
        imgDetalles.setImage(detalle);
        imgDetalles.setStyle(item.getColorBorde().getStyle());
        nombreDetalles.setText(item.getNombre());
        nombreDetalles.setId(item.getColorBorde().name());
        
        if(item instanceof Nucleo){
            
        }else if(item instanceof SemiFacturado){
            
        }else if(item instanceof Mineral){
            
        }else if(item instanceof Equipamiento){
            
        }  
    }
    
}
