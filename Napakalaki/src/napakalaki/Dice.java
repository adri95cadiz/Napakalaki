package napakalaki;

public class Dice {
    //Datos Miembros
    private static final Dice instance = new Dice();
    
    //Constructor (es privado para que la clase sea singleton)
    private Dice(){
        //Constructor
    }
    public static Dice getInstance() {
        return instance;
    }
    public int nextNumber(){
        return (1+(int) (Math.random()*6));
    }
}