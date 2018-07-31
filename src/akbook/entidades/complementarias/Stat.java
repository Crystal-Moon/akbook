/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

import akbook.entidades.enums.Ruta;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author crystal
 */
public class Stat {

    public String[] stats;

    public Stat() {}

    public static Stat buscarStat(String txt, int id) {
        
        Stat elStat = new Stat();
        InputStream input = Stat.class.getResourceAsStream(Ruta.text.getRuta()+txt);
        BufferedReader buffTxt = null;
        try {
            Reader reader = new InputStreamReader(input);  
            boolean hash = true;
            buffTxt = new BufferedReader(reader);
            while (hash) {
                String linea = buffTxt.readLine();
                if (linea.startsWith(String.valueOf(id))) {    
                    linea = buffTxt.readLine();
                    elStat.stats = linea.split("#");
                    hash = false;
                }
            }
        } catch (IOException ex) {
            System.out.println("error al cargar archivo");
        } finally {
            try {
                buffTxt.close();
            } catch (IOException ex) {
                System.out.println("error en el finaly");
            }
        }
        return elStat;
    }
}
