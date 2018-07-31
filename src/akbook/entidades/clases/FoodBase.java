/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.clases;

import akbook.entidades.complementarias.NPC;
import akbook.entidades.complementarias.Money;
import akbook.entidades.base.ItemConsumible;
import static akbook.entidades.enums.Calidad.white;
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
public class FoodBase extends ItemConsumible {

    private NPC npcVendedor;
    private Money costoCompra;

    public NPC getNpcVendedor() { return npcVendedor; }
    public Money getCostoCompra() { return costoCompra; }
    public FoodBase() {}

//------- DAO ---------
    public static FoodBase traerFoodBase(int id, Connection conn) {

        Statement stmtConsulta = null;
        ResultSet rs = null;
        FoodBase comidaBase = new FoodBase();
        try {
            String laConsulta = "SELECT id, nombre, lvl_requerido, npc_id, "
                    + "costo_g, costo_s, archivo FROM food_base WHERE id=" + id + ";";
            stmtConsulta = conn.createStatement();
            rs = stmtConsulta.executeQuery(laConsulta);

            while(rs.next()) {
                comidaBase.id_base = rs.getInt("id");
                comidaBase.nombre[0] =rs.getString("nombre");
                comidaBase.lvlRequerido = rs.getInt("lvl_requerido");
                comidaBase.npcVendedor = NPC.traerNpc(rs.getInt("npc_id"),conn);
                comidaBase.costoCompra = new Money(rs.getInt("costo_g"), rs.getInt("costo_s"));
                comidaBase.archivo = Ruta.foods.getRuta() + rs.getString("archivo");
            }
        } catch (SQLException ex) {
            System.out.println("error al traer Food Base de DB");
        } catch (Exception ex) {
            Logger.getLogger(FoodBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error al conectar");
        } finally {
            try {
                rs.close();
                stmtConsulta.close();
            } catch (SQLException e) {
                System.out.println("error al cerrar conexion");
            }
        }
        comidaBase.colorBorde = white;
        return comidaBase;
    }
}
