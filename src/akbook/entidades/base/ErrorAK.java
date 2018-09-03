
package akbook.entidades.base;

import javafx.scene.control.Alert;

/**
 *
 * @author Perla
 */
/*
Clase controladora de errores en tiempo de ejecucion.
 */
public abstract class ErrorAK extends Exception{
    
    //SQLExceptions
    public static void errorBaseDatos(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error al conectar");
        alert.setContentText("No se ha podido conectar el banco de datos. Revise la ruta de ejecucion del programa.\nUna correcta reinstalacion solucionaria el problema");
        alert.showAndWait();
    }
    //IOExceptions
    public static void errorTxt(String txt){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se encontro el archivo");
        alert.setContentText("No se ha podido encontrar el archivo '"+txt+"'. Revise la ruta de ejecucion del programa.\nUna correcta reinstalacion solucionaria el problema");
        alert.showAndWait();
    }
    //Exceptions
    public static void errorGenerico(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("No se encontro el archivo");
        alert.setContentText("Se ha producido un error indeterminado");
        alert.showAndWait();
    }
    //IOException | URISyntaxException
    public static void errorDeRed(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("No se ha podido encontrar la URL especificada o bien revise su coneccion a internet.");
        alert.showAndWait();
    }
    
}
