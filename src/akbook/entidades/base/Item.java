/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.base;

import akbook.entidades.enums.Calidad;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author crystal
 */
public abstract class Item {
    
    protected String[] nombre = new String[5];
    protected Calidad colorBorde;
    protected String archivo;
    protected int id_base;
    protected int lvlRequerido;

    public String getNombre() {return nombre[0];}  
    public String getArchivo() {return archivo;}
    public Calidad getColorBorde() {return colorBorde;}
    public int getId_base() {return id_base; }
    public int getLvlRequerido() {return lvlRequerido;}
    
    public StringProperty getNombreProperty(){
        String name=this.nombre[0];
        StringProperty nameP=new SimpleStringProperty(name);
        return nameP;
    }
    
    public StringProperty getLvlProperty(){
        String nivel=String.valueOf(this.lvlRequerido);
        StringProperty level=new SimpleStringProperty(nivel);
        return level;
    }
}
