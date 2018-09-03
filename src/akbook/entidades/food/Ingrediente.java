/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.food;

import akbook.entidades.base.Item;
import akbook.entidades.complementarias.Origen;
import static akbook.entidades.complementarias.Calidad.blue;
import akbook.entidades.complementarias.Ruta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author crystal
 */
public class Ingrediente extends Item{
    
    private Origen ubicacion;
    private boolean comerciante;
   
    public Origen getUbicacion() { return ubicacion; }
    public boolean getComerciante() { return comerciante; }
    
    public Ingrediente() {}
    
    public static Ingrediente traerIngrediente(int id, Connection conn) throws SQLException{
        /*
        id - identificador unico en la db
        conn - conexion compartida entre toda la busqueda
        */
        Statement stmtConsulta=null;
        ResultSet rs = null;
        Ingrediente ingr=new Ingrediente();
    
        String laConsulta = "SELECT id, nombre, lvl_requerido, ubicacion_id, archivo FROM ingrediente WHERE id="+id+";";
        stmtConsulta = conn.createStatement(); 
        rs = stmtConsulta.executeQuery(laConsulta); 
        
        while(rs.next()){ 
        ingr.id_base=rs.getInt("id");
        ingr.nombre[0]=rs.getString("nombre");
        ingr.lvl=rs.getInt("lvl_requerido");
        ingr.ubicacion=Origen.traerOrigen(rs.getInt("ubicacion_id"),conn);
        ingr.archivo=Ruta.ings.getRuta()+rs.getString("archivo");
        ingr.colorBorde=blue;
        ingr.comerciante=rs.getBoolean("comerciante");
        }
        rs.close();
        stmtConsulta.close();
        
        return ingr;       
    }
}
