/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.complementarias;

/**
 *
 * @author Perla
 */
/*
Cad 1000 plata en 1 oro. NO hay cobre en este caso.
*/
public class Money {
    
    private final int gold;
    private final int silver;
    
    public int getGold(){ return gold; }
    public int getSilver(){ return silver; }
    
    public Money(int oro, int plata){
        this.gold=oro;
        this.silver=plata;
    }  
}
