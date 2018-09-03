/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akbook.entidades.complementarias;

/**
 *
 * @author Perla
 */
public enum EnumsEquipo {
    
    Arma,Accesorio,Armadura;
    
    public static enum Arma{
        Arco,Baculo,Canon,Dagas_Gemelas,Espada_y_Escudo,
        Grimorio,Guadana,Hacha,Katana,Katar,Lira,Mandoble,Pistolas,Shuriken;  
        //Lanza -- prox
    }   
    
    public static enum Accesorio{
        Anillo,Collar,Espalda,Botin;
    }
    
    public static enum Armadura{
        Cabeza,Cuerpo,Cintura,Manos,Pies;
    }
    
}
