/**
 *
 */

package napakalaki;


/**
 * Clase Monster: Representa la estructura principal de cada una de las cartas 
 * de monstruos que se van a usar, contiene un "buen rollo" y un "mal rollo" 
 * ademas de un nombre y un nivel para el mismo
 */

public class Monster implements Card{
    private final String name;
    private final int level;
    private final Prize prize;
    private final BadConsequence bad;
    private final int levelChangeAgainstCultistPlayer;
    
    /**
     * @param name Nombre del Monstruo
     * @param level Nivel del Monstruo
     * @param bad "mal rollo" del Monstruo
     * @param prize "buen rollo" del Monstruo
     * @param levelChangeAgainstCultistPlayer
     */
    public Monster(String name, int level, BadConsequence bad, Prize prize, 
            int levelChangeAgainstCultistPlayer){
        this.name = name;
        this.level = level;
        this.bad = bad;
        this.prize = prize;
        this.levelChangeAgainstCultistPlayer = levelChangeAgainstCultistPlayer;
    }
    
    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return level;
    }
    
    public BadConsequence getBad() {
        return bad;
    }

    public Prize getPrize() {
        return prize;
    }

    public int getLevelChangeAgainstCultistPlayer() {
        return levelChangeAgainstCultistPlayer;
    }
    
    @Override
    public String toString(){
        return "Name = " + name + ", level = " + Integer.toString(level) 
                + ", \nBad Consequence: " + bad.toString() + ", \nPrize: " 
                + prize.toString();
    }
    
    /*Monster: getBasicValue() llama al método getLevel() y getSpecialValue()
devuelve el getLevel() + levelChangeAgainstCultistPlayer.*/
    
    @Override
    public int getBasicValue() {
        return getLevel();
    }

    @Override
    public int getSpecialValue() {
        return getLevel()+getLevelChangeAgainstCultistPlayer();
    }
}
