/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.clases;

import akbook.entidades.base.Conexion;
import akbook.entidades.complementarias.Money;
import akbook.entidades.base.ItemConsumible;
import static akbook.entidades.enums.Calidad.green;
import akbook.entidades.enums.Ruta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author crystal
 */
public class FoodFinish extends ItemConsumible {

    private Ingrediente ingPrincipal;
    private int cantIngPrincipal;
    private FoodBase base;
    private Money costoCocina;
    private Ingrediente ing2;
    private int cantIng2;
    private FoodBase base2;
    private int cantBase2;

    @Override
    public String getNombre(){
        if(this.nombre[0].equals("")){
            return this.getBase().getNombre();
        }else{
            return this.nombre[0];
        }
    }
    @Override
    public StringProperty getNombreProperty(){
        String name=this.nombre[0];
        if(name.equals("")){
            name=this.getBase().getNombre();
        }else{
            name=this.nombre[0];
        }
        StringProperty nameP=new SimpleStringProperty(name);
        return nameP;
    }
    public String getNombreGreen() {  return nombre[0]+nombre[1]; }
    public String getNombreOrange() { return nombre[0]+nombre[2]; }
    public String getNombrePurple() { return nombre[0]+nombre[3]; }
    public String getNombreGold() { return nombre[0]+nombre[4]; }
    public Ingrediente getIngPrincipal() { return ingPrincipal; }
    public FoodBase getBase() { return base; }
    public Money getCostoCocina() { return costoCocina; }
    public Ingrediente getIng2() { return ing2; }
    public FoodBase getBase2() { return base2; }
    public int getCantIngPrincipal() { return cantIngPrincipal; }
    public int getCantIng2() { return cantIng2; }
    public int getCantBase2() { return cantBase2; }

    public FoodFinish() {}

    public static ArrayList traerFoodFinish(int lvl) {

        Connection laConexion = null;
        Statement stmtConsulta = null;
        ResultSet rs = null;
        ArrayList<FoodFinish> foods = new ArrayList();

        try {
            laConexion = Conexion.getConnection();
            if (laConexion == null) {
                System.out.println("vacio");
            }
            String laConsulta = "SELECT id, nombre, "
                    + "name_green, name_orange, name_purple, name_golden,"
                    + "lvl_requerido, ing_p, c_ingp, food_base_id, ing2, c_ing2 ,ing3, c_ing3, "
                    + "base2, c_base2, costo_g, costo_s, "
                    + "archivo FROM food_finish WHERE lvl_requerido>=" + lvl + ";";
            stmtConsulta = laConexion.createStatement();
            rs = stmtConsulta.executeQuery(laConsulta);

            while (rs.next()) {
                FoodFinish comidaFinal = new FoodFinish();
                comidaFinal.id_base = rs.getInt("id");
                comidaFinal.nombre[0] = rs.getString("nombre");
                comidaFinal.nombre[1] = rs.getString("name_green");
                comidaFinal.nombre[2] = rs.getString("name_orange");
                comidaFinal.nombre[3] = rs.getString("name_purple");
                comidaFinal.nombre[4] = rs.getString("name_golden");
                comidaFinal.lvlRequerido = rs.getInt("lvl_requerido");
                comidaFinal.ingPrincipal = Ingrediente.traerIngrediente(rs.getInt("ing_p"), laConexion);
                comidaFinal.cantIngPrincipal = rs.getInt("c_ingp");
                comidaFinal.base = FoodBase.traerFoodBase(rs.getInt("food_base_id"), laConexion);
                comidaFinal.ing2 = Ingrediente.traerIngrediente(rs.getInt("ing2"), laConexion);
                comidaFinal.cantIng2 = rs.getInt("c_ing2");
                comidaFinal.base2 = FoodBase.traerFoodBase(rs.getInt("base2"), laConexion);
                comidaFinal.cantBase2 = rs.getInt("c_base2");
                comidaFinal.costoCocina = new Money(rs.getInt("costo_g"), rs.getInt("costo_s"));
                comidaFinal.archivo = Ruta.foods.getRuta() + rs.getString("archivo");
                comidaFinal.colorBorde = green;
                foods.add(comidaFinal);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("error al traer Food Finish de DB");
        } catch (Exception ex) {
            Logger.getLogger(FoodBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getCause() + ex.getMessage());
            System.out.println("error al conectar");
        } finally {
            try {
                rs.close();
                stmtConsulta.close();
                laConexion.close();
            } catch (SQLException e) {
                System.out.println("error al cerrar conexion");
            }
        }
        return foods;
    }
}
