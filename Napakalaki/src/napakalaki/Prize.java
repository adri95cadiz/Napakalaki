/**
 *
 */

package napakalaki;

/**
 * Clase Prize: Representa el "buen rollo" de un monster
 * treasures:   Numero de tesoros que ganas al vencer al monster
 * levels:      Numero de niveles que ganas al vencer al monster
 */

public class Prize {
    private final int treasures;
    private final int levels;
    
    /**
     * @param treasures Numero de tesoros.
     * @param levels Numero de niveles.
     */
    
    public Prize(int treasures, int levels){
        this.treasures = treasures;
        this.levels = levels;
    }
    
    //Getters
    
    public int getTreasures(){
        return treasures;
    }
    
    public int getLevels(){
        return levels;
    }
    
    //Metodo to String
    @Override
    public String toString(){
        return "Treasures = " + Integer.toString(treasures) 
                + ", levels = " + Integer.toString(levels);
    }
}
