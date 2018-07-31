/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.enums;

/**
 *
 * @author crystal
 */
public enum Ruta {

//----- imgs
    ings("/akbook/recursos/imgs/ings/"),
    foods("/akbook/recursos/imgs/foods/"),
    maps("/akbook/recursos/imgs/maps/"),
    plants("/akbook/recursos/imgs/plants/"),
//----- text stats
    text("/akbook/recursos/values/"),
//----- layout
    fxml("/akbook/layout/fxmls/"),
    contoller("/akbook/layout/controller/");

    private final String ruta;

    public String getRuta() { return ruta; }

    Ruta(String path) { this.ruta = path; }
    
}
