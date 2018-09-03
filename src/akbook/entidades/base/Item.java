/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.base;

import akbook.entidades.complementarias.Calidad;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author crystal
 */
/*
Clase padre de todo el programa.
*/
public abstract class Item {
    
    protected int id_base;
    protected String[] nombre = new String[5];
    protected Calidad colorBorde;
    protected String archivo;
    protected int lvl;

    public String getNombre() {return nombre[0];}  
    public String getArchivo() {return archivo;}
    public Calidad getColorBorde() {return colorBorde;}
    public int getId_base() {return id_base; }
    public int getLvl() {return lvl;}
    
    public StringProperty getNombreProperty(){
        String name=this.nombre[0];
        StringProperty nameP=new SimpleStringProperty(name);
        return nameP;
    }
    
    public StringProperty getLvlProperty(){
        String nivel=String.valueOf(this.lvl);
        StringProperty level=new SimpleStringProperty(nivel);
        return level;
    }
    
}
