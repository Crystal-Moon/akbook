/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akbook.entidades.refinacion;

import akbook.entidades.base.Item;
import static akbook.entidades.complementarias.Calidad.orange;
import akbook.entidades.complementarias.Ruta;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Perla
 */
public class Nucleo extends Item{
    
    private SemiFacturado aleacion;
    private int cantAleacion;
    private Mineral mineral1;
    private int cantM1;
    private Mineral mineral2;
    private int cantM2;
    private Mineral mineral3;
    private int cantM3;
    
    public SemiFacturado getAleacion() { return aleacion; }
    public int getCantAleacion() { return cantAleacion; }
    public Mineral getMineral1() { return mineral1; }
    public int getCantM1() { return cantM1; }
    public Mineral getMineral2() { return mineral2; }
    public int getCantM2() { return cantM2; }
    public Mineral getMineral3() { return mineral3; }
    public int getCantM3() { return cantM3; }
    
    public Nucleo() {}
    
    public static Nucleo traerNucleo(int id, Connection conn) throws SQLException, IOException{
        /*
        id - identificador unico en la db
        conn - conexion compartida entre toda la busqueda
        */
        Statement stmtConsulta = null;
        ResultSet rs = null;
        Nucleo nucleo=new Nucleo();

        String laConsulta = "SELECT id, nombre, lvl, "
                + "sf, c_sf, m1, c_m1, m2, c_m2, m3, c_m3, "
                + "archivo FROM nucleos WHERE id=" + id + ";";
        stmtConsulta = conn.createStatement();
        rs = stmtConsulta.executeQuery(laConsulta);

        while (rs.next()) {
            
            nucleo.id_base = rs.getInt("id");
            nucleo.nombre[0] = rs.getString("nombre");
            nucleo.lvl = rs.getInt("lvl");
            nucleo.aleacion=SemiFacturado.traerAleacion(rs.getInt("sf"),conn);
            nucleo.cantAleacion=rs.getInt("c_sf");
            nucleo.mineral1=Mineral.traerMineral(rs.getInt("m1"),conn);
            nucleo.cantM1=rs.getInt("c_m1");
            nucleo.mineral2=Mineral.traerMineral(rs.getInt("m2"),conn);
            nucleo.cantM2=rs.getInt("c_m2");
            nucleo.mineral3=Mineral.traerMineral(rs.getInt("m3"),conn);
            nucleo.cantM3=rs.getInt("c_m3");
            nucleo.archivo=Ruta.nucleos.getRuta()+rs.getString("archivo");
            nucleo.colorBorde=orange;
        }
        rs.close();
        stmtConsulta.close();
        
        return nucleo;
    } 
}
