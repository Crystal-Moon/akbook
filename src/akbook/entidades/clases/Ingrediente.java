/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.clases;

import akbook.entidades.base.Item;
import akbook.entidades.complementarias.Origen;
import static akbook.entidades.enums.Calidad.blue;
import akbook.entidades.enums.Ruta;
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
public class Ingrediente extends Item{
    
    private Origen ubicacion;
   
    public Origen getUbicacion() { return ubicacion; }
    
    public Ingrediente() {}
    
    public static Ingrediente traerIngrediente(int id, Connection conn){

        Statement stmtConsulta=null;
        ResultSet rs = null;
        Ingrediente ingr=new Ingrediente();
    try{
        String laConsulta = "SELECT id, nombre, lvl_requerido, ubicacion_id, archivo FROM ingrediente WHERE id="+id+";";
        stmtConsulta = conn.createStatement(); 
        rs = stmtConsulta.executeQuery(laConsulta); 
        
        while(rs.next()){ 
        ingr.id_base=rs.getInt("id");
        ingr.nombre[0]=rs.getString("nombre");
        ingr.lvlRequerido=rs.getInt("lvl_requerido");
        ingr.ubicacion=Origen.traerOrigen(rs.getInt("ubicacion_id"),conn);
        ingr.archivo=Ruta.ings.getRuta()+rs.getString("archivo");
       }
    }catch (SQLException ex){
        System.out.println("error al traer datos de Ingrediente");
    }catch (Exception ex) {
            Logger.getLogger(Ingrediente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error al conectar");
    }finally{
        try{
            rs.close();
            stmtConsulta.close();
        }catch(SQLException e){
            System.out.println("error al cerrar conexion");
        }
    }
    ingr.colorBorde=blue;
    return ingr;       
    }
}
