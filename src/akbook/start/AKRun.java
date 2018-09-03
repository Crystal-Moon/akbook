/* 
    Created on : 28/06/2018, 21:03:11
    Author     : crystal
*/
package akbook.start;

import akbook.entidades.base.CtrlPrincipal;
import akbook.entidades.base.ErrorAK;
import akbook.entidades.complementarias.Ruta;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author crystal
 */
/*
    Runneable.
*/
public class AKRun extends Application {
    
    private static Stage primary;
    private AnchorPane main;
    private AnchorPane root1;

    @Override
    public void start(Stage stage){
        /*
        Inicializacion del cover book.
        */
        AKRun.primary = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Ruta.fxml.getRuta() + "cover.fxml"));
        try {
            main = loader.load();
            Scene scene = new Scene(main);
            primary.setScene(scene);
            primary.setResizable(false);
            primary.initStyle(StageStyle.UNDECORATED);
            primary.getIcons().add(new Image("akbook/layout/frames/icon.png"));
            primary.show();
            CtrlPrincipal ven = loader.getController();
            ven.setMain(this);
        } catch (IOException ex) {
            ErrorAK.errorTxt("index.fmxl");
        }
    }

    public void ventanaInterna(String ventana) {
        /*
        Abre toda ventana nueva del book
        ventana - nombre del fxml.
        */
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(Ruta.fxml.getRuta() + ventana));
        try {
            root1 = loader1.load();
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(root1);
            stage1.setResizable(false);
            stage1.setScene(scene1);
            stage1.getIcons().add(new Image("akbook/layout/frames/icon.png"));
            if(ventana.equalsIgnoreCase("book.fxml")){
                stage1.setHeight(394);
                stage1.setWidth(601.5);
                stage1.setTitle("Aura Kingdom - Recipe's Book");
            } else{
                stage1.setMaxHeight(329);
                stage1.setMaxWidth(305);
                stage1.setTitle("");
            }
            stage1.show();
            CtrlPrincipal ven = loader1.getController();
            ven.setMain(this);
        } catch (IOException ex) {
            ErrorAK.errorTxt(ventana);
        }
    }

    public static void closePrimary() {
        primary.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
