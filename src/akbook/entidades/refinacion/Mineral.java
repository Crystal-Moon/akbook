/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akbook.entidades.refinacion;

import akbook.entidades.base.Item;
import static akbook.entidades.complementarias.Calidad.orange;
import akbook.entidades.complementarias.Origen;
import akbook.entidades.complementarias.Ruta;
import akbook.entidades.complementarias.Value;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Perla
 */
public class Mineral extends Item {
    
    private ArrayList<Origen> entidades;
    private Value dung;
    private boolean comerciante;
    
    public ArrayList todaUbicacion() { return this.entidades; }
    public Value getDung(){ return dung; }
    public boolean isComerciante(){ return this.comerciante; }
    
    public Mineral() {}
    
    public static Mineral traerMineral(int id, Connection conn) throws SQLException, IOException{
        /*
        id - identificador unico en la db
        conn - conexion compartida entre toda la busqueda
        */
        Statement stmtConsulta=null;
        ResultSet rs = null;
        Mineral mineral=new Mineral();
    
        String laConsulta = "SELECT id, nombre, lvl, npc, "
                + "ubi1, ubi2, ubi3, ubi4, calidad, archivo "
                + "FROM minerales WHERE id="+id+";";
        stmtConsulta = conn.createStatement(); 
        rs = stmtConsulta.executeQuery(laConsulta); 
        
        while(rs.next()){ 
            mineral.id_base=rs.getInt("id");
            mineral.nombre[0]=rs.getString("nombre");
            mineral.lvl=rs.getInt("lvl");
            mineral.comerciante=rs.getBoolean("npc");
            for(int x=1; x<=4; x++){
                int idUbi=rs.getInt("ubi"+x);
                if(idUbi!=0){
                    Origen origen=Origen.traerOrigen(idUbi, conn);
                    mineral.entidades.add(origen);
                }
            } // en el archivo dungs pongo "instancias xx o superior" respetando los lvl esactos
            mineral.dung=Value.buscarUbicacion("dung-minerales.txt", rs.getInt("id"));
            mineral.colorBorde=orange;
            mineral.archivo=Ruta.minerales.getRuta()+rs.getString("archivo");
        }
        rs.close();
        stmtConsulta.close();
        
        return mineral;
    }
}