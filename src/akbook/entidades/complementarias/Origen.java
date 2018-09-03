/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Perla
 */
/*
Ubicacion de cada item recolectable.
Son entidades que existen en distintos mapas.
*/
public class Origen {
    
    private int id_base;
    private String nombre;
    
    private String nameMap1;
    private String mapa1;
    
    private String mapa2;
    private String nameMap2;
    
    private String mapa3;
    private String nameMap3;
    
    private String mapa4;
    private String nameMap4;
    private String fileObjeto;

    public String getNombreObj() { return nombre; }
    public String getFileObjeto(){ return fileObjeto; }
    
    public String getNameMap1(){ return this.nameMap1; }
    public String getArchivoMapa1(){ return this.mapa1+this.nombre+".jpg"; }
    
    public String getNameMap2(){ return this.nameMap2; }
    public String getArchivoMapa2(){ return this.mapa2+this.nombre+".jpg"; }
    
    public String getNameMap3(){ return this.nameMap3; }
    public String getArchivoMapa3(){ return this.mapa3+this.nombre+".jpg"; }
    
    public String getNameMap4(){ return this.nameMap4; }
    public String getArchivoMapa4(){ return this.mapa4+this.nombre+".jpg"; }
    
    public boolean map2Exist(){
        return !this.nameMap2.equals("");
    }
    public boolean map3Exist(){
        return !this.nameMap3.equals("");
    }
    public boolean map4Exist(){
        return !this.nameMap4.equals("");
    }
    
    
    public Origen() {}
    
//------- DAO ---------
    public static Origen traerOrigen(int id, Connection conn) throws SQLException{
       /*
        id - identificador unico en la db
        conn - conexion compartida entre toda la busqueda
        */
    Statement stmtConsulta=null;
    ResultSet rs = null;
    Origen elOrigen=new Origen();
     
    String laConsulta = "SELECT id, objeto, "
            + "name_map1, name_map2,name_map3, name_map4, "
            + " mapa1, mapa2, mapa3, map4, archivo "
            + "FROM origen WHERE id="+id+";";
    stmtConsulta = conn.createStatement(); 
    rs = stmtConsulta.executeQuery(laConsulta); 
        
    while(rs.next()){
        elOrigen.id_base=rs.getInt("id");
        elOrigen.nombre=rs.getString("objeto");
        elOrigen.nameMap1=rs.getString("name_map1");
        elOrigen.nameMap2=rs.getString("name_map2");
        elOrigen.nameMap3=rs.getString("name_map3");
        elOrigen.nameMap4=rs.getString("name_map4");
        elOrigen.mapa1=Ruta.maps.getRuta()+rs.getString("mapa1");
        elOrigen.mapa2=Ruta.maps.getRuta()+rs.getString("mapa2");
        elOrigen.mapa3=Ruta.maps.getRuta()+rs.getString("mapa3");
        elOrigen.mapa4=Ruta.maps.getRuta()+rs.getString("map4"); //no se puede ni cambiar nombre ni eliminar columnas
        elOrigen.fileObjeto=Ruta.fonts.getRuta()+rs.getString("archivo");
    }
    rs.close();
    stmtConsulta.close();
       
    return elOrigen;
    }
}
