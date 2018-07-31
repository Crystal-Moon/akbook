/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.base;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Conexion {
    
    public Conexion() {}

    public static Connection getConnection() throws Exception
    {
        String base = "jdbc:sqlite::resource:"+Conexion.class.getResource("/akbook/recursos/database/data1");     
        
        Class.forName("org.sqlite.JDBC").newInstance();
        return DriverManager.getConnection(base);
    }
    
}
