/**
 
 */

package napakalaki;

import java.util.ArrayList;


/**
 * Clase BadConsequence: Representa el "mal rollo" de un monster
 * text:                     Explicacion del mal rollo
 * levels:                   Numero de niveles que pierdes
 * nVisibleTreasure:         Numero de tesoros visibles que pierdes
 * nHiddenTreasures:         Numero de tesoros ocultos que pierdes
 * death: true:              Si pierdes mueres / false: Si pierdes no mueres
 * specificHiddenTreasures:  Lista de tipos de tesoros ocultos que pierdes
 * specificVisibleTreasures: Lista de tipos de tesoros visibles que pierdes
 * maxTreasureLost:          Maximo numero de tesoros que podrías llegar a tener
 */
public class BadConsequence {
    private final String text;
    private final int levels;
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    private boolean death;
    private ArrayList<TreasureKind> specificHiddenTreasures;
    private ArrayList<TreasureKind> specificVisibleTreasures;
    private static final int maxTreasureLost = 999; 

/**
 * Constructor al que se le pasan los datos miembros: text, levels, nVisible, nHidden
 * Las listas de specificTreasures se quedan vacias y el valor de death es false 
 */
    
    public BadConsequence(String text, int levels, int nVisible, int nHidden){
        this.text = text;
        this.levels = levels;
        specificHiddenTreasures = new ArrayList<>();
        specificVisibleTreasures = new ArrayList<>();
        nHiddenTreasures = nHidden;
        nVisibleTreasures = nVisible;
        death = false;
    }
    
   /**
    * Constructor al que se le pasa el texto y si la carta es de muerte o no
    * Se pone true para indicar que mueres si te gana el monstruo
    * Se pone false para indicar que NO mueres si te gana el monstruo
    * El resto de parametros se inicializan a 0 y las listas de 
    * specificTreasures se quedan vacias
    */
    
    public BadConsequence(String text, boolean death){
        this.text = text;
        this.death = death;
        levels = 0;
        specificHiddenTreasures = new ArrayList<>();
        specificVisibleTreasures = new ArrayList<>();
        nHiddenTreasures = 0;
        nVisibleTreasures = 0;
    }
    
    /**
     * Constructor al que se le pasan los datos miembros: 
     * Parametros: text, levels, specificVisibleTreasures, specificHiddenTresasures
     * #El tipo muerte se inicializa a false
     */
    
    public BadConsequence(String text, int levels, ArrayList<TreasureKind> 
            tVisible, ArrayList<TreasureKind> tHidden){
        this.text = text;
        this.levels = levels;
        this.death = false;
        specificHiddenTreasures = (ArrayList<TreasureKind>) tHidden.clone();
        specificVisibleTreasures = (ArrayList<TreasureKind>) tVisible.clone();
        nHiddenTreasures = tHidden.size();
        nVisibleTreasures = tVisible.size();
    }
    
    /*Devuelve true cuando el mal rollo que tiene que cumplir el jugador está 
    vacío, eso significa que el conjunto de atributos del mal rollo indican que 
    no hay mal rollo que cumplir. Piensa qué valores deberán tener.*/
    
    public boolean isEmpty(){     
        return (nVisibleTreasures == 0 && nHiddenTreasures == 0 
                && death == false  && levels == 0);
    }
    
    /*Devuelve true si el mal rollo es muerte, false en caso contrario.*/
    
    public boolean kills(){
        return death;
    }
    
    public String getText(){
        return text;
    }
    
    public int getLevels(){
        return levels;
    }
    
    public int getnHiddenTreasures(){
        return nHiddenTreasures;
    }
    
    public int getnVisibleTreasures(){
        return nVisibleTreasures;
    }

    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return specificHiddenTreasures;
    }

    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return specificVisibleTreasures;
    }
    
    /*Actualiza el mal rollo que tiene que cumplir el jugador, en función del 
    tipo de tesoro de t y del tipo de mal rollo que tenga que cumplir el 
    jugador.*/
    
    public void substractVisibleTreasure(Treasure t){
        if(t.getType().equals(specificVisibleTreasures.get(0))){
            specificVisibleTreasures.remove(t.getType());
        }
        nVisibleTreasures--;
    }
    
    /*Actualiza el mal rollo que tiene que cumplir el jugador, en función del 
    tipo de tesoro de t y del tipo de mal rollo que tenga que cumplir el 
    jugador.*/
    
    public void substractHiddenTreasure(Treasure t){
        if(t.getType().equals(specificHiddenTreasures.get(0))){
            specificHiddenTreasures.remove(t.getType());
        }
        nHiddenTreasures--;
    }
    
    /*Recibe como parámetros los tesoros visibles y ocultos de los que dispone 
    el jugador y devuelve un nuevo objeto mal rollo que contiene una lista de 
    tipos tesoros visibles y ocultos de los que el jugador debe deshacerse. El 
    objeto de mal rollo devuelto por adjustToFitTreasureList solo contendrá 
    tipos de tesoros y en una cantidad adecuada a los que el jugador puede 
    llegar a deshacerse en función de los que dispone.*/
    
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> v, 
            ArrayList<Treasure> h){
        boolean typebad = false;
        ArrayList<TreasureKind> new_visible = new ArrayList<>(); 
        ArrayList<TreasureKind> new_hidden = new ArrayList<>();
        ArrayList<TreasureKind> vType = new ArrayList<>(); 
        ArrayList<TreasureKind> hType = new ArrayList<>();
        
        if(nVisibleTreasures != specificVisibleTreasures.size() 
                || nHiddenTreasures != specificHiddenTreasures.size()){
            typebad = true;
        }
        
        for(int i = 0; i < v.size(); i++){
            vType.add(v.get(i).getType());
        }
        for(int i = 0; i < h.size(); i++){
            hType.add(h.get(i).getType());
        }
        if(typebad){
                if(nVisibleTreasures != 0)
                    new_visible = (ArrayList<TreasureKind>) vType.clone();
                else if(nHiddenTreasures !=0)
                    new_hidden= (ArrayList<TreasureKind>) hType.clone();                    
        }
        else{
            for(int i = 0; i < nVisibleTreasures;i++){
                if(vType.contains(specificVisibleTreasures.get(i))){
                    new_visible.add(specificVisibleTreasures.get(i));
                }
            }
            for(int i = 0; i < nHiddenTreasures;i++){
                if(hType.contains(specificHiddenTreasures.get(i))){
                    new_hidden.add(specificHiddenTreasures.get(i));
                }
            }
        }
        BadConsequence new_bad = new BadConsequence("", 0, new_visible,
                new_hidden);
        return new_bad;
    }
    
    public boolean isDeath(){
        return death;
    }
    
    public static int getMaxTreasureLost(){
        return maxTreasureLost;
    }
    
    @Override
    public String toString(){
        return "Text = " + text + ", levels = " + Integer.toString(levels) 
                + ", HiddenTreasures = " + Integer.toString(nHiddenTreasures) 
                + ", VisibleTreasures = " + Integer.toString(nVisibleTreasures)
                + ", Death = " + Boolean.toString(death)
                + ", VisibleItems = " + specificVisibleTreasures
                + ", HiddenItems = " + specificHiddenTreasures;
    }
}