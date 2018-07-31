/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.base;

import akbook.start.AKRun;

/**
 *
 * @author Perla
 */
public abstract class CtrlPrincipal {
    
    protected AKRun main;
    
    public void setMain(AKRun windows){
        this.main=windows;
    }   
}
