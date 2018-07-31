/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

import akbook.entidades.enums.Ruta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Perla
 */
public class Origen {
    
    private int id_base;
    private String nameMap1;
    private String nameMap2;
    private String objeto;
    private String mapa1;
    private String mapa2;
    private String fileObjeto;

    public String getNombreObj() { return objeto; }
    public String getFileObjeto(){ return fileObjeto; }
    public String getArchivoMapa1(){ return this.mapa1+this.objeto+".jpg"; }
    public String getArchivoMapa2(){ return this.mapa2+this.objeto+".jpg"; }
    public String getNameMap1(){ return this.nameMap1; }
    public String getNameMap2(){ return this.nameMap2; }
    public boolean map2Exist(){
        return !this.nameMap2.equals("");
    }
    
    public Origen() {}
    
//------- DAO ---------
    public static Origen traerOrigen(int id, Connection conn){
       
        Statement stmtConsulta=null;
        ResultSet rs = null;
        Origen elOrigen=new Origen();
    try{    
        String laConsulta = "SELECT id, objeto, name_map1, name_map2,"
                + " mapa1, mapa2, archivo FROM origen WHERE id="+id+";";
        stmtConsulta = conn.createStatement(); 
        rs = stmtConsulta.executeQuery(laConsulta); 
        
        while(rs.next()){
        elOrigen.id_base=rs.getInt("id");
        elOrigen.objeto=rs.getString("objeto");
        elOrigen.nameMap1=rs.getString("name_map1");
        elOrigen.nameMap2=rs.getString("name_map2");
        elOrigen.mapa1=Ruta.maps.getRuta()+rs.getString("mapa1");
        elOrigen.mapa2=Ruta.maps.getRuta()+rs.getString("mapa2");
        elOrigen.fileObjeto=Ruta.plants.getRuta()+rs.getString("archivo");
        }
    }catch (SQLException ex){
        System.out.println("error al traer datos de Origen de DB");
    }   catch (Exception ex) {
            Logger.getLogger(Origen.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error al conectar");
        }finally{
        try{
            rs.close();
            stmtConsulta.close();
        }catch (SQLException e){
            System.out.println("error al cerrar conexion");
        }
    }
    return elOrigen;
    }
}
