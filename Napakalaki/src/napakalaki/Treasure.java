/**
 *
 */

package napakalaki;

public class Treasure implements Card{
    private final String name;
    private final int goldcoins;
    private final int minBonus;
    private final int maxBonus;
    private final TreasureKind type;
    
    public Treasure(String name, int goldcoins, int minBonus, int maxBonus, 
            TreasureKind type){
        this.name = name;
        this.goldcoins = goldcoins;
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getGoldCoins() {
        return goldcoins;
    }

    public int getMinBonus() {
        return minBonus;
    }

    public int getMaxBonus() {
        return maxBonus;
    }

    public TreasureKind getType() {
        return type;
    }
    
    @Override
    public String toString(){
        return "Name = " + name + ", goldcoins = " + goldcoins 
                + ", minBonus: " + minBonus + ", maxBonus: " 
                + maxBonus + ", type: " + type;
    }

    /*Treasure: devuelven el bonus mínimo para getBasicValue() y bonus máximo
para getSpecialValue(), llamando a los correspondiente consultores.*/
    
    @Override
    public int getBasicValue() {
        return getMinBonus();
    }

    @Override
    public int getSpecialValue() {
        return getMaxBonus();
    }
}
