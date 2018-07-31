/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crystal
 */
public class NPC {
    
    private int id_base;
    private String titulo;
    private String nombre;
    private String mapa;

    public String getTitulo(){ return titulo; }
    public String getNombre() { return nombre; }
    public String getMapa() { return mapa; }

    public NPC() {}
    
//------- DAO ---------
    public static NPC traerNpc(int id, Connection conn){
       
        Statement stmtConsulta=null;
        ResultSet rs = null;
        NPC elNpc=new NPC();
    try{
        String laConsulta = "SELECT id, titulo, nombre, mapa FROM npc WHERE id="+id+";";
        stmtConsulta = conn.createStatement(); 
        rs = stmtConsulta.executeQuery(laConsulta); 
        
        while(rs.next()){
            elNpc.id_base=rs.getInt("id");
            elNpc.titulo=rs.getString("titulo");
            elNpc.nombre=rs.getString("nombre");
            elNpc.mapa=rs.getString("mapa");
        }
    }catch (SQLException ex){
        System.out.println("error al traer datos de NPC");
    }   catch (Exception ex) {
            Logger.getLogger(NPC.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error al conectar");
        }finally{
        try{
            rs.close();
            stmtConsulta.close();
        }catch (SQLException e){
            System.out.println("error al cerrar conexion");
        }
    }
    return elNpc;
    }
}
