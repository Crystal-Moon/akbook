/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.layout.controller;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.base.Item;
import akbook.entidades.base.ErrorAK;
import akbook.entidades.food.FoodFinish;
import akbook.entidades.complementarias.Value;
import akbook.entidades.complementarias.Calidad;
import akbook.entidades.complementarias.Ruta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Perla
 */
public class BookController extends CtrlPrincipal implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private TabPane tabFood;

//------- tab 1 Buscar -----//
    @FXML
    private CheckBox checkDANrecibido;
    @FXML
    private CheckBox chackPRE;
    @FXML
    private CheckBox checkDAN;
    @FXML
    private CheckBox checkPS;
    @FXML
    private CheckBox checkDEF;
    @FXML
    private CheckBox checkVEL;
    @FXML
    private CheckBox checkEVA;
    @FXML
    private CheckBox checkDANobj;
    @FXML
    private CheckBox checkVELmov;
    @FXML
    private CheckBox checkATQ;
    @FXML
    private CheckBox checkCuracion;
    @FXML
    private CheckBox checkATQprob;
    @FXML
    private Button btnBusqueda;
    @FXML
    private CheckBox checkCRIT;
    @FXML
    private CheckBox checkDANcrit;
    @FXML
    private CheckBox checkCRITtasa;
    @FXML
    private CheckBox checkEXP;
    @FXML
    private ComboBox<String> menuLvl;
    @FXML
    private ComboBox<String> menuElementos;
    @FXML
    private TableView<FoodFinish> tableBuscar;
    @FXML
    private TableColumn<FoodFinish, String> columLvl;
    @FXML
    private TableColumn<FoodFinish, String> columNombreFood;
    @FXML
    private Label lblDetalles;

    private final ObservableList<FoodFinish> foodData = FXCollections.observableArrayList();
    private final ArrayList todoCheck = new ArrayList();
    private final HashSet<Integer> idsElegidos = new HashSet();
    private ArrayList<FoodFinish> paraSeleccionar=new ArrayList();

    @FXML
    private void handlerBtnBuscarLista(ActionEvent event) throws URISyntaxException {

        paraSeleccionar.clear();
        idsElegidos.clear();
        foodData.clear();
        
        String[] nivel = new String[3];
        String linea = menuLvl.getSelectionModel().getSelectedItem();
        int lvl=0;
        if(!(linea.equalsIgnoreCase("Todos"))){
            nivel = linea.split(" ");
            lvl = Integer.parseInt(nivel[0]);
        }
        try {
            paraSeleccionar= FoodFinish.traerFoodFinish(lvl);
        } catch (SQLException ex) {
            ErrorAK.errorBaseDatos();
        } catch (Exception ex) {
            ErrorAK.errorGenerico();
        }
        
        seleccionarIds();
        
        Iterator itFood= paraSeleccionar.iterator();
        while(itFood.hasNext()){
            FoodFinish food=(FoodFinish) itFood.next();
            if(idsElegidos.contains(food.getId_base())){
                foodData.add(food);
                lblDetalles.setVisible(true);
            }
        }
        
        tableBuscar.setItems(foodData);
        if(foodData.isEmpty()){
            foodData.clear();
            tableBuscar.setItems(foodData);
            lblDetalles.setVisible(false);
            mjeEmpty();
        }
    }

//------ Tab Cocina ----------------------------------------------------------------------------------//
    @FXML
    private Label imgIngrPrincipal;
    @FXML
    private Label nombreIngrPrincipal;
    @FXML
    private Label cantIngrPrincipal;
    @FXML
    private Label imgFoodFinish;
    @FXML
    private Label nombreFoodFinish;
    @FXML
    private Label imgFoodBase;
    @FXML
    private Label nombreFoodBase;
    @FXML
    private Label imgIngr2;
    @FXML
    private Label nombreIngr2;
    @FXML
    private Label cantIngr2;
    @FXML
    private Label silverMoneyIzq;
    @FXML
    private Label goldMoneyIzq;
    @FXML
    private Label nombreItemDer;
    @FXML
    private Label imgItemDer;
    @FXML
    private StackPane stackInfo;
    @FXML
    private AnchorPane anchorInfoFood;
    @FXML
    private Label lvlInfoFood;
    @FXML
    private Label lblStatGreen;
    @FXML
    private Label lblStatOrange;
    @FXML
    private Label lblStatPurple;
    @FXML
    private Label lblStatGold;
    @FXML
    private AnchorPane anchorInfoNpc;
    @FXML
    private Label lblInfoNpc;
    @FXML
    private Label silverMoneyDer;
    @FXML
    private Label goldMoneyDer;
    @FXML
    private AnchorPane anchorInfoIngr;
    @FXML
    private Label lblArbolIngr;
    @FXML
    private ImageView imgArbolIngr;
    @FXML
    private Button btnMapIngr;
    @FXML
    private Label lblStatWhite;
    //----------------- Izquierdo -----------------------
    private FoodFinish FoodElegida;

    @FXML
    private void handlerImgIngrPrincipal(MouseEvent event) {
        btnMapIngr.setDisable(false);
        cambiarStake(anchorInfoIngr);
        setearPanelDerecho(FoodElegida.getIngPrincipal());
        lblArbolIngr.setText(FoodElegida.getIngPrincipal().getUbicacion().getNombreObj());
        imgArbolIngr.setImage(new Image(FoodElegida.getIngPrincipal().getUbicacion().getFileObjeto()));
        MapController.setarUbicacion(FoodElegida.getIngPrincipal().getUbicacion());
    }

    @FXML
    private void handlerImgFoodFinish(MouseEvent event) {
        cambiarStake(anchorInfoFood);
        lvlInfoFood.setText("Nivel " + FoodElegida.getLvl() + " o superior.");
        setearPanelDerecho(FoodElegida);
        tabFood.getSelectionModel().select(0);
        alSeleccionarGreen(event);
    }

    @FXML
    private void handlerImgFoodBase(MouseEvent event) {
        cambiarStake(anchorInfoNpc);
        setearPanelDerecho(FoodElegida.getBase());
        String npcCompleto = FoodElegida.getBase().getNpcVendedor().getTitulo()
                + " " + FoodElegida.getBase().getNpcVendedor().getNombre()
                + "\nUbicacion: " + FoodElegida.getBase().getNpcVendedor().getMapa();
        lblInfoNpc.setText("NPC:\n"+npcCompleto);
        Value statWhite=null;
        try {
            statWhite = Value.buscarStat("food_base.txt", FoodElegida.getBase().getId_base());
        } catch (IOException ex) {
            ErrorAK.errorTxt("food_base.txt");
        }
        lblStatWhite.setText(statWhite.atributo[0]
                + "\n" + statWhite.atributo[1]);
        goldMoneyDer.setText(String.valueOf(FoodElegida.getBase().getCostoCompra().getGold()));
        silverMoneyDer.setText(String.valueOf(FoodElegida.getBase().getCostoCompra().getSilver()));
    }

    @FXML
    private void handlerImgIngr2(MouseEvent event) {
        if (FoodElegida.getCantBase2() != 0) {
            cambiarStake(anchorInfoNpc);
            setearPanelDerecho(FoodElegida.getBase2());
            String npcCompleto = FoodElegida.getBase2().getNpcVendedor().getTitulo()
                    + " " + FoodElegida.getBase2().getNpcVendedor().getNombre()
                    + "\nUbicacion: " + FoodElegida.getBase2().getNpcVendedor().getMapa();
            lblInfoNpc.setText("NPC:\n"+npcCompleto);
            Value statWhite=null;
            try {
                statWhite = Value.buscarStat("food_base.txt", FoodElegida.getBase2().getId_base());
            } catch (IOException ex) {
                ErrorAK.errorTxt("food_base.txt");
            }
            lblStatWhite.setText(statWhite.atributo[0]
                + "\n" + statWhite.atributo[1]);
            goldMoneyDer.setText(String.valueOf(FoodElegida.getBase2().getCostoCompra().getGold()));
            silverMoneyDer.setText(String.valueOf(FoodElegida.getBase2().getCostoCompra().getSilver()));
        } else {
            cambiarStake(anchorInfoIngr);
            setearPanelDerecho(FoodElegida.getIng2());
            lblArbolIngr.setText(FoodElegida.getIng2().getUbicacion().getNombreObj());
            imgArbolIngr.setImage(new Image(FoodElegida.getIng2().getUbicacion().getFileObjeto()));
            btnMapIngr.setDisable(false);
            MapController.setarUbicacion(FoodElegida.getIng2().getUbicacion());
        }
    }

    @FXML
    private void handlerBtnMapIngr(ActionEvent event) {
        super.main.ventanaInterna("map.fxml");
    }

    // ------------ Derecho ----------------  
    private void cambiarStake(AnchorPane pane) {
        anchorInfoFood.toBack();
        anchorInfoIngr.toBack();
        anchorInfoNpc.toBack();
        pane.toFront();
    }

    private void setearPanelDerecho(Item item) {
        imgItemDer.setGraphic(new ImageView(new Image(item.getArchivo())));
        imgItemDer.setStyle(item.getColorBorde().getStyle());
        nombreItemDer.setText(item.getNombre());
        nombreItemDer.setId(Calidad.white.name());
    }

    @FXML
    private void alSeleccionarGreen(Event event) {
        imgItemDer.setStyle(Calidad.green.getStyle());
        nombreItemDer.setText(FoodElegida.getNombreGreen());
        nombreItemDer.setId(Calidad.green.name());

        Value statGreen=null;
        try {
            statGreen = Value.buscarStat("food_finish_green.txt", FoodElegida.getId_base());
        } catch (IOException ex) {
            ErrorAK.errorTxt("food_finish_green.txt");
        }
        lblStatGreen.setText(statGreen.atributo[0]
                + "\n" + statGreen.atributo[1]
                + "\n" + statGreen.atributo[2]
                + "\n \nTiempo: 1hs.");
    }

    @FXML
    private void alSeleccionarOrange(Event event) {
        imgItemDer.setStyle(Calidad.orange.getStyle());
        nombreItemDer.setText(FoodElegida.getNombreOrange());
        nombreItemDer.setId(Calidad.orange.name());

        Value statOrange=null;
        try {
            statOrange = Value.buscarStat("food_finish_orange.txt", FoodElegida.getId_base());
        } catch (IOException ex) {
            ErrorAK.errorTxt("food_finish_orange.txt");
        }
        lblStatOrange.setText(statOrange.atributo[0]
                + "\n" + statOrange.atributo[1]
                + "\n" + statOrange.atributo[2]
                + "\n \nTiempo: 3hs.");
    }

    @FXML
    private void alSeleccionarPurple(Event event) {
        imgItemDer.setStyle(Calidad.purple.getStyle());
        nombreItemDer.setText(FoodElegida.getNombrePurple());
        nombreItemDer.setId(Calidad.purple.name());

        Value statPurple=null;
        try {
            statPurple = Value.buscarStat("food_finish_purple.txt", FoodElegida.getId_base());
        } catch (IOException ex) {
            ErrorAK.errorTxt("food_finish_purple.txt");
        }
        lblStatPurple.setText(statPurple.atributo[0]
                + "\n" + statPurple.atributo[1]
                + "\n" + statPurple.atributo[2]
                + "\n \nTiempo: 3hs.");
    }

    @FXML
    private void alSeleccionarGold(Event event) {
        imgItemDer.setStyle(Calidad.gold.getStyle());
        nombreItemDer.setText(FoodElegida.getNombreGold());
        nombreItemDer.setId(Calidad.gold.name());

        Value statGold=null;
        try {
            statGold = Value.buscarStat("food_finish_gold.txt", FoodElegida.getId_base());
        } catch (IOException ex) {
            ErrorAK.errorTxt("food_finish_gold.txt");
        }
        lblStatGold.setText(statGold.atributo[0]
                + "\n" + statGold.atributo[1]
                + "\n" + statGold.atributo[2]
                + "\n \nTiempo: 6hs.");
    }



//--------- Inicializar --------------------------------------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    // PRUEBAS---------------------------
//        FoodFinish comida=new FoodFinish();
//        comida=FoodFinish.traerFoodFinish(545);
//        FoodElegida=comida;
//        cargarItemSeleccionado(comida);

    //--------------------------------------  
//        tabPane.getSelectionModel().select(0);
        cargarTodo();
        btnMapIngr.setDisable(true);
        lblDetalles.setVisible(false);

        columLvl.setCellValueFactory(cellData -> cellData.getValue().getLvlProperty());
        columNombreFood.setCellValueFactory(cellData -> cellData.getValue().getNombreProperty());

        tableBuscar.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> cargarItemSeleccionado(newValue));
    }

//-------------------------Otros -----------------------------------------------------------------------//    
    
    private void cargarItemSeleccionado(FoodFinish a){
    
    FoodElegida = a; 
    try{
        if (a.getCantIngPrincipal() != 0) {
            cambioDeTab(1);
            // Articulo fusion
            Image imgIP = new Image(getClass().getResourceAsStream(a.getIngPrincipal().getArchivo()));
            this.imgIngrPrincipal.setGraphic(new ImageView(imgIP));
            this.nombreIngrPrincipal.setText(a.getIngPrincipal().getNombre());
            this.cantIngrPrincipal.setText(String.valueOf(a.getCantIngPrincipal()));
            // Resultado
            Image imgFF = new Image(getClass().getResourceAsStream(a.getArchivo()));
            this.imgFoodFinish.setGraphic(new ImageView(imgFF));
            this.nombreFoodFinish.setText(a.getNombre());
            // Ingredientes
            Image imgFB = new Image(getClass().getResourceAsStream(a.getBase().getArchivo()));
            this.imgFoodBase.setGraphic(new ImageView(imgFB));
            this.imgFoodBase.setStyle(Calidad.white.getStyle());
            this.nombreFoodBase.setText(a.getBase().getNombre());
            if (a.getCantBase2() != 0) {
                Image imgI2 = new Image(getClass().getResourceAsStream(a.getBase2().getArchivo()));
                this.imgIngr2.setGraphic(new ImageView(imgI2));
                this.imgIngr2.setStyle(Calidad.white.getStyle());
                this.nombreIngr2.setText(a.getBase2().getNombre());
                this.cantIngr2.setText(String.valueOf(a.getCantBase2()));
            } else {
                if (a.getCantIng2() != 0) {
                    Image imgI2 = new Image(getClass().getResourceAsStream(a.getIng2().getArchivo()));
                    this.imgIngr2.setGraphic(new ImageView(imgI2));
                    this.imgIngr2.setStyle(Calidad.blue.getStyle());
                    this.nombreIngr2.setText(a.getIng2().getNombre());
                    this.cantIngr2.setText(String.valueOf(a.getCantIng2()));
                } else {
                    this.imgIngr2.setGraphic(null);
                    this.nombreIngr2.setText("");
                    this.cantIngr2.setText("");
                    this.imgIngr2.setStyle("");
                }
            }
            // Money
            this.goldMoneyIzq.setText(String.valueOf(a.getCostoCocina().getGold()));
            this.silverMoneyIzq.setText(String.valueOf(a.getCostoCocina().getSilver()));
        } else {
            this.imgIngrPrincipal.setGraphic(null);
            this.nombreIngrPrincipal.setText("");
            this.cantIngrPrincipal.setText("");
            this.imgFoodFinish.setGraphic(null);
            this.nombreFoodFinish.setText("");
            this.imgFoodBase.setGraphic(null);
            this.nombreFoodBase.setText("");
            this.imgIngr2.setGraphic(null);
            this.nombreIngr2.setText("");
            this.cantIngr2.setText("");
            this.goldMoneyIzq.setText("");
            this.silverMoneyIzq.setText("");
        }
        handlerImgIngrPrincipal(null);
    } catch(NullPointerException ex){
        ErrorAK.errorGenerico();
    }
  }

    private void cargarTodo() {
        todoCheck.add(checkDANrecibido);
        todoCheck.add(chackPRE);
        todoCheck.add(checkDAN);
        todoCheck.add(checkPS);
        todoCheck.add(checkDEF);
        todoCheck.add(checkVEL);
        todoCheck.add(checkEVA);
        todoCheck.add(checkVELmov);
        todoCheck.add(checkATQ);
        todoCheck.add(checkCuracion);
        todoCheck.add(checkATQprob);
        todoCheck.add(checkCRIT);
        todoCheck.add(checkDANcrit);
        todoCheck.add(checkCRITtasa);
        todoCheck.add(checkEXP);

        menuElementos.getItems().add("Todos");
        menuElementos.getItems().add("Luz");
        menuElementos.getItems().add("Oscuridad");
        menuElementos.getItems().add("Fuego");
        menuElementos.getItems().add("Hielo");
        menuElementos.getItems().add("Relampago");
        menuElementos.getItems().add("Tormenta");
        menuElementos.getSelectionModel().selectFirst();
        menuElementos.setDisable(true);

        menuLvl.getItems().add("Todos");
        menuLvl.getItems().add("10 o superior");
        menuLvl.getItems().add("20 o superior");
        menuLvl.getItems().add("30 o superior");
        menuLvl.getItems().add("40 o superior");
        menuLvl.getItems().add("50 o superior");
        menuLvl.getItems().add("60 o superior");
        menuLvl.getItems().add("70 o superior");
        menuLvl.getItems().add("75 o superior");
        menuLvl.getItems().add("80 o superior");
        menuLvl.getSelectionModel().selectFirst();
    }

    @FXML
    private void seleccionCheckObjetivo(MouseEvent event) {
        if (!(checkDANobj.isSelected())) {
            menuElementos.setDisable(true);
        } else {
            menuElementos.setDisable(false);
        }
    }

    private void seleccionarIds(){
        Iterator itCheck = todoCheck.iterator();
        while (itCheck.hasNext()) {
            CheckBox check = (CheckBox) itCheck.next();
            if (check.isSelected()) {
                buscarEnTxt(check.getText());
            }
            if (checkDANobj.isSelected()) {
            buscarEnTxt(menuElementos.getSelectionModel().getSelectedItem());
            }
        }
    }

    private void buscarEnTxt(String atributo){
        InputStream input = this.getClass().getResourceAsStream(Ruta.text.getRuta()+"food_finish_id.txt");
        BufferedReader buffTxt = null;
        String[] laLinea = new String[2];
        int id = 0;
        boolean hash = true;
        try {
            Reader reader = new InputStreamReader(input);  
            buffTxt = new BufferedReader(reader);
            while (hash) {
                String linea = buffTxt.readLine();
                if (linea != null) {
                    if (linea.contains(atributo)) {
                        laLinea = linea.split("#");
                        id = Integer.parseInt(laLinea[0]);                        
                        idsElegidos.add(id);
                    }
                } else {
                    hash = false;
                }
            }
        } catch (IOException ex) {
            ErrorAK.errorTxt(atributo);
        } finally {
            try {
                buffTxt.close();
            } catch (IOException ex) {
                ErrorAK.errorTxt(atributo);
            }
        }
    }
    
    public void cambioDeTab(int tab){
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    private void handlerHelp(MouseEvent event) {
        super.main.ventanaInterna("help.fxml");
    }

    private void mjeEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("");
        alert.setContentText("No se encontraron resultados con estas especificaciones.");
        alert.showAndWait();
    }
    
}
