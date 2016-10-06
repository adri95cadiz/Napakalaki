/**
 *
 */

package napakalaki;

/**
 * Enumerado TreasureKind: se encarga de definir un conjunto definido de objetos
 */
public enum TreasureKind {
    ARMOR, ONEHAND, BOTHHANDS, HELMET, SHOE, NECKLACE;
    
/**
 * Metodo getRandom(): Obtiene un elemento aleatorio del enumerado 
 */
    public static TreasureKind getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}