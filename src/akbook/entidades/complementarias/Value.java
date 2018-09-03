/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
 */
package akbook.entidades.complementarias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author crystal
 */
/*
Manejo de archivos .txt, lectura y recopilacion de datos.
*/
public class Value {

    public String[] atributo;

    public Value() {
    }

    public static Value buscarStat(String txt, int id) throws IOException {
        /*
        txt - archivo a analizar (*.txt)
        id - identificador a buscar
        lectura linea a linea hasta hallar el id, recuperacion de datos asociados a dicho id.
        
        return String[] array echo objValue.
        */
        Value elStat = new Value();
        InputStream input = Value.class.getResourceAsStream(Ruta.text.getRuta() + txt);
        BufferedReader buffTxt = null;

        Reader reader = new InputStreamReader(input);
        boolean hash = true;
        buffTxt = new BufferedReader(reader);
        while (hash) {
            String linea = buffTxt.readLine();
            if (linea.startsWith(String.valueOf(id))) {
                linea = buffTxt.readLine();
                elStat.atributo = linea.split("#");
                hash = false;
            }
        }
        buffTxt.close();
        
        return elStat;
    }
    
    public static Value buscarUbicacion(String txt, int id) throws IOException{
        
        //revisar todo
        Value ubicacion = new Value();
        InputStream input = Value.class.getResourceAsStream(Ruta.text.getRuta() + txt);
        BufferedReader buffTxt = null;

        Reader reader = new InputStreamReader(input);
        boolean hash = true;
        buffTxt = new BufferedReader(reader);
        while (hash) {
            String linea = buffTxt.readLine();
            if (linea.startsWith(String.valueOf(id))) {
                linea = buffTxt.readLine();
                ubicacion.atributo = linea.split("#");
                hash = false;
            }
        }
        buffTxt.close();
        
        return ubicacion;
    }
  
}
