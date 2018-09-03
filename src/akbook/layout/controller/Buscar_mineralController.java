/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
 */
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.base.ErrorAK;
import akbook.entidades.complementarias.EnumsEquipo;
import akbook.entidades.complementarias.EnumsEquipo.Accesorio;
import akbook.entidades.complementarias.EnumsEquipo.Arma;
import akbook.entidades.complementarias.EnumsEquipo.Armadura;
import akbook.entidades.refinacion.Equipamiento;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class Buscar_mineralController extends CtrlPrincipal implements Initializable {

    @FXML
    private Label lblInfoTable;
    @FXML
    private ComboBox<String> menuLvl;
    @FXML
    private ComboBox<String> menuArt;
    @FXML
    private ComboBox<String> menuArt2;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<Equipamiento> tableBuscar;
    @FXML
    private TableColumn<Equipamiento, String> columnLvl;
    @FXML
    private TableColumn<Equipamiento, String> columnName;
    @FXML
    private TableColumn<Equipamiento, String> columnColor;

    private final ObservableList<Equipamiento> equipoData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lblInfoTable.setVisible(false);
//        tabPane.getSelectionModel().select(tabBuscar);
        cargarTodo();

        columnLvl.setCellValueFactory(cellData -> cellData.getValue().getLvlProperty());
        columnName.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());
        columnColor.setCellValueFactory(cellData -> cellData.getValue().getColorProperty());

        tableBuscar.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> cargarItemSeleccionado(newValue));
    }

    private void cargarItemSeleccionado(Equipamiento item) {

        HerreriaController.setEquipo0(item);
        BookController c = new BookController();
        c.cambioDeTab(2);
        
        /*
        try{
            if (item.isRefinado()) {
                equipoSelec = Equipamiento.traerEquipamientoRefinado(item.getId_base(), parteSelec);
            }else{
                equipoSelec = Equipamiento.traerEquipamientoDropeable(item.getId_base(), parteSelec);
            }
            BookController c = new BookController();
            c.cambioDeTab(2);
        }catch (SQLException ex) {
                ErrorAK.errorBaseDatos();
        } catch (IOException ex) {
                ErrorAK.errorTxt("stat" + parteSelec);
        } catch (Exception ex) {
                ErrorAK.errorGenerico();
        }
        HerreriaController.setEquipo0(equipoSelec);
        */  
    }

    @FXML
    private void handlerBtnBuscar(ActionEvent event) {

        equipoData.clear();

        int lvl = Integer.parseInt(menuLvl.getSelectionModel().getSelectedItem());
        String parte = menuArt2.getSelectionModel().getSelectedItem();
        ArrayList<Equipamiento> elegidos = new ArrayList();

        if (!(menuArt.getSelectionModel().getSelectedItem().equals("Seleccione"))) {
            try {
                elegidos = Equipamiento.traerTodoEquipo(lvl, parte);
                equipoData.addAll(elegidos);
                lblInfoTable.setVisible(true);
            } catch (Exception ex) {
                ErrorAK.errorBaseDatos();
            }
            tableBuscar.setItems(equipoData);
        } else {
            mjeEmpty();
        }
    }

    @FXML
    private void handlerHelp(MouseEvent event) {
        super.main.ventanaInterna("help.fxml");
    }

    private void cargarTodo() {
        menuLvl.getItems().add("40");
        menuLvl.getItems().add("50");
        menuLvl.getItems().add("55");
        menuLvl.getItems().add("60");
        menuLvl.getItems().add("65");
        menuLvl.getItems().add("70");
        menuLvl.getItems().add("75");
        menuLvl.getItems().add("80");
        menuLvl.getItems().add("85");
        menuLvl.getItems().add("90");
        menuLvl.getItems().add("95");
        menuLvl.getSelectionModel().selectFirst();

        menuArt.getItems().add("Seleccione");
        for (EnumsEquipo arma : EnumsEquipo.values()) {
                menuArt.getItems().add(arma.name());
        }
        menuArt.getSelectionModel().selectFirst();

        menuArt2.setDisable(true);
    }

    @FXML
    private void handlerMenuArt(ActionEvent event) {

        if (menuArt.getSelectionModel().getSelectedItem().equalsIgnoreCase("Arma")) {
            menuArt2.setDisable(false);
            menuArt2.getItems().clear();
            for (Arma arma : Arma.values()) {
                menuArt2.getItems().add(arma.name());
            }
            menuArt2.getSelectionModel().selectFirst();

        } else if (menuArt.getSelectionModel().getSelectedItem().equalsIgnoreCase("Accesorio")) {
            menuArt2.setDisable(false);
            menuArt2.getItems().clear();
            for (Accesorio acc : Accesorio.values()) {
                menuArt2.getItems().add(acc.name());
            }
            menuArt2.getSelectionModel().selectFirst();

        } else if (menuArt.getSelectionModel().getSelectedItem().equalsIgnoreCase("Armadura")) {
            menuArt2.setDisable(false);
            menuArt2.getItems().clear();
            for (Armadura armor : Armadura.values()) {
                menuArt2.getItems().add(armor.name());
            }
            menuArt2.getSelectionModel().selectFirst();
        } else {
            menuArt2.getItems().clear();
            menuArt2.setDisable(true);
        }
    }

    private void mjeEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText("Por favor seleccione el articulo a buscar.");
        alert.showAndWait();
    }
}
