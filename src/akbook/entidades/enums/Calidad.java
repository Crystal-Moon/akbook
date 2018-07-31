/* 
    Created on : 25/07/2018, 21:03:11
    Author     : crystal
*/
package akbook.entidades.enums;

/**
 *
 * @author crystal
 */
public enum Calidad {

    white("-fx-border-color: #C3C3C3; -fx-border-radius:  5; -fx-border-width: 3; -fx-border-style: solid;"),
    blue("-fx-border-color: #1B62B1; -fx-border-radius:  5; -fx-border-width: 3; -fx-border-style: solid;"),
    green("-fx-border-color: #6DBC49; -fx-border-radius:  5; -fx-border-width: 3; -fx-border-style: solid;"),
    orange("-fx-border-color: #DC8030; -fx-border-radius:  5; -fx-border-width: 3; -fx-border-style: solid;"),
    purple("-fx-border-color: #8D46C9; -fx-border-radius:  5;  -fx-border-width: 3; -fx-border-style: solid;"),
    gold("-fx-border-color: #FFE363; -fx-border-radius:  5; -fx-border-width: 3; -fx-border-style: solid;");

    private final String style;

    public String getStyle() { return style; }

    Calidad(String stylo) { this.style = stylo; }

}
