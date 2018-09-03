/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

/**
 *
 * @author crystal
 */
public enum Ruta {

//----- imgs
    ings("/akbook/recursos/imgs/ings/"),
    minerales("/akbook/recursos/imgs/minerales/"), 
    foods("/akbook/recursos/imgs/foods/"),
    
    equipo("/akbook/recursos/imgs/equipo/"),
    nucleos("/akbook/recursos/imgs/nucleos/"),  
    
    maps("/akbook/recursos/imgs/maps/"),
    fonts("/akbook/recursos/imgs/fonts/"),
//----- text stats
    text("/akbook/recursos/values/"),
//----- layout
    fxml("/akbook/layout/fxmls/");
   // contoller("/akbook/layout/controller/");

    private final String ruta;

    public String getRuta() { return ruta; }

    Ruta(String path) { this.ruta = path; }
    
}
