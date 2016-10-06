/**
 *
 */

package napakalaki;

import static java.lang.Math.min;
import java.util.ArrayList;

public class Player {
    private boolean dead = true;
    private int level=-1;
    private String name;
    private ArrayList<Treasure> HiddenTreasures;
    private ArrayList<Treasure> VisibleTreasures;
    private BadConsequence pendingBadConsequence;
    protected boolean cultist = false;
    
    @Override
    public String toString(){
        return name;
    }
    
    public Player(Player player){
        name = player.getName();
        level = player.getLevel();
        dead = player.isDead();
        HiddenTreasures = (ArrayList<Treasure>) player.getHiddenTreasures().clone();
        VisibleTreasures = (ArrayList<Treasure>) player.getVisibleTreasures().clone();
        pendingBadConsequence = player.getPendingBadConsequence();
    }
    
    public Player(String name){
        this.name = name;
        level = 1;
        dead = false;
        HiddenTreasures = new ArrayList<>();
        VisibleTreasures = new ArrayList<>();
        pendingBadConsequence = new BadConsequence("Primer Turno",0,0,0);
    }
    
    /*Devuelve la vida al jugador, modificando el atributo correspondiente.*/
    private void bringToLife(){
        dead = false;
    } 
    /*Incrementa el nivel del jugador en l niveles*/
    private void incrementLevels(int l){
        level = level + l;
    }
    /*Decrementa el nivel del jugador en l niveles*/
    private void decrementLevels(int l){
        level = level - l;
        if(level < 1) level = 1;
    }
   /*Asigna el mal rollo al jugador, dándole valor a su atributo 
    pendingBadConsequence.*/ 
    private void setPendingBadConsequence(BadConsequence bad) {
        pendingBadConsequence = bad;
    }
    //Mata al jugador
    private void die(){
        CardDealer cd = CardDealer.getInstance();
        for(int i = 0; i < VisibleTreasures.size(); i++){
            Treasure treasure = cd.nextTreasure();
            cd.giveTreasureBack(treasure);
        }
        VisibleTreasures.clear();
        for(int i = 0; i < HiddenTreasures.size(); i++){
            Treasure treasure = cd.nextTreasure();
            cd.giveTreasureBack(treasure);
        }
        HiddenTreasures.clear();
        level = 1;
        dead = true;
    }
    
    /*Si el jugador tiene equipado el tesoro tipo NECKLACE, se lo pasa al 
    CardDealer y lo elimina de sus tesoros visibles.*/
    private void discardNecklaceIfVisible(){
        CardDealer cd = CardDealer.getInstance();
        boolean have_necklace = false;
        int N = VisibleTreasures.size();
        
        for(int i = 0; i < N ; i++){
            if(VisibleTreasures.get(i).getType().equals(TreasureKind.NECKLACE)){
                cd.giveTreasureBack(VisibleTreasures.get(i));
                VisibleTreasures.remove(i);
            }
        }
    }
    
    /*Cambia el estado de jugador a muerto si no tiene ni tesoros visibles ni 
    ocultos, modificando el correspondiente atributo.*/
    private void dieIfNoTreasures(){
        if(VisibleTreasures.isEmpty() && HiddenTreasures.isEmpty())
            die();
    }
    
    /*Calcula y devuelve los niveles que puede comprar el jugador con la lista 
    t de tesoros.*/
    private int computeGoldCoinsValue(ArrayList<Treasure> t){
        int maxcoins = 0, N=t.size(),canbuylevels;
        for(int i = 0; i < N; i++){
            maxcoins += t.get(i).getGoldCoins();
        }
        canbuylevels = maxcoins/1000; //Division entera
        return canbuylevels;
    }
    
    /*Devuelve true si con los niveles que compra no gana la partida y 
    false en caso contrario.*/
    private boolean canIBuyLevels(int l){
        Napakalaki game = Napakalaki.getInstance();
        boolean stat;
        if(l > game.getCurrentMonster().getLevel()){
            stat = true;
        }
        else
            stat = false;
        return stat;
    }
    
    public void applyPrize(Prize p){
        int nlevels = p.getLevels();
        int nprize = p.getTreasures();
        Treasure t = null;
        incrementLevels(nlevels);
        for(int i = 0; i < min(nprize,4-HiddenTreasures.size()); i++){
            CardDealer cd = CardDealer.getInstance();
            t = cd.nextTreasure();
            HiddenTreasures.add(t);
        }
    }
    
/*El método combat() de la clase Player debe modificarse para que una vez que el
jugador activo haya combatido y en caso de que no haya podido huir pero siga 
vivo:
• Se envíe a sí mismo el mensaje shouldConvert()
• Si el mensaje devuelve true devuelva el valor LoseAndConvert*/
    
/*En el método combat() de Player se modificará para que, en vez de preguntar el
    nivel del monstruo al que se enfrenta al propio monstruo con el método 
    getLevel(), se preguntará a sí mismo con el método getOponentLevel(). El 
    método getOponentLevel() debe definirse en Player y redefinirse en 
    CultistPlayer para hacer uso de getBasicValue() y getSpecialValue() de la 
    clase Monster devolviendo respectivamente el nivel del monstruo al que se 
    enfrenta un jugador y un jugador sectario.*/
    
    public CombatResult combat(Monster m){
        CombatResult stat = null; 
        int myLevel = getCombatLevel();
        //int levelMonster = m.getLevel();
        int levelMonster = getOponentLevel();
        int dice_result;
        boolean amIdead;
        Dice dc = Dice.getInstance();
        if(myLevel>levelMonster){
            Prize prize = m.getPrize();
            applyPrize(prize);
            if(level<10){
                stat = CombatResult.WIN;
            }
            else
                stat = CombatResult.WINANDWINGAME;
        }
        else{
            dice_result = dc.nextNumber();
            boolean not_escape = dice_result<5;
            if(not_escape){
                BadConsequence bad = m.getBad();
                amIdead = bad.kills();
                if(amIdead==true){
                    die();
                    stat = CombatResult.LOSEANDDIE;
                }
                else{
                    applyBadConsequence(bad);
                    if(shouldConvert())
                        stat = CombatResult.LOSEANDCONVERT;                        
                    else
                        stat = CombatResult.LOSE;     
                }
            }
            else{
                stat=CombatResult.LOSEANDESCAPE;
            }
        }
        discardNecklaceIfVisible();
        return stat;
    }
    
    public void applyBadConsequence(BadConsequence bad){
        int nlevels = bad.getLevels();
        decrementLevels(nlevels);
        BadConsequence pendingBad = bad.adjustToFitTreasureLists(VisibleTreasures, 
                HiddenTreasures);
        setPendingBadConsequence(pendingBad);
    }
    
    public void makeTreasureVisible(Treasure t){
            getHiddenTreasures().remove(t);
            getVisibleTreasures().add(t);
    }
    
    /*Comprueba si el tesoro (t) se puede pasar de oculto a visible, según las 
    reglas del juego*/
    
    public boolean canMakeTreasureVisible(Treasure t){
        TreasureKind type = t.getType();
        boolean canMakeVisible=true; //Variable: -> Se puede hacer visible? 
        boolean isHandKind=false;
        int handCounter=0;
        boolean isEquipped=false;
        
        if(type.equals(TreasureKind.BOTHHANDS)||type.equals(TreasureKind.ONEHAND)){
            isHandKind = true;
        }
        
        if(isHandKind){
            for(int i=0; i<getVisibleTreasures().size() ; i++){
                if(getVisibleTreasures().get(i).getType().equals(TreasureKind.ONEHAND))
                    handCounter++;
                if(getVisibleTreasures().get(i).getType().equals(TreasureKind.BOTHHANDS))
                    handCounter += 2;
            }
            if(type.equals(TreasureKind.BOTHHANDS )){
                if(handCounter>0){
                    canMakeVisible=false;
                }
            }
            if(type.equals(TreasureKind.ONEHAND)){
               if(handCounter>1){
                    canMakeVisible=false;
                }
            }
        }
        else{
            for(int i=0; i<getVisibleTreasures().size(); i++){
                if(getVisibleTreasures().get(i).getType().equals(type)){
                    isEquipped=true;
                }
            }
        }
        return (canMakeVisible && !isEquipped);
    } 
    
    public void discardVisibleTreasure(Treasure t){
        CardDealer cd = CardDealer.getInstance();
        VisibleTreasures.remove(t);
        if((!pendingBadConsequence.isEmpty())){
            pendingBadConsequence.substractVisibleTreasure(t);
        }
        cd.giveTreasureBack(t);
        dieIfNoTreasures();
    }
    
    public void discardHiddenTreasure(Treasure t){
        CardDealer cd = CardDealer.getInstance();
        HiddenTreasures.remove(t);
        if((!pendingBadConsequence.isEmpty())){
            pendingBadConsequence.substractHiddenTreasure(t);
        }
        cd.giveTreasureBack(t);
        dieIfNoTreasures();
    }
    
    public boolean buyLevels(ArrayList<Treasure> visible,
            ArrayList<Treasure> hidden){
        int levels = computeGoldCoinsValue(visible);
        levels += computeGoldCoinsValue(hidden);
        boolean canI = canIBuyLevels(levels+getCombatLevel());
        if (canI){
            incrementLevels(levels);
            for(int i = 0; i < visible.size(); i++){
                discardVisibleTreasure(visible.get(i));
            }
            for(int i = 0; i < hidden.size(); i++){
                discardHiddenTreasure(hidden.get(i));
            }
        }
        return canI;
    }

    /*Devuelve el nivel de combate del jugador, que viene dado por su nivel más 
    los bonus que le proporcionan los tesoros que tenga equipados, según las 
    reglas del juego.*/
    
    public int getCombatLevel() {
        int totallevel = level;
        boolean haveNecklace = false;
        
        for(int i = 0; i < VisibleTreasures.size();i++){
            if(TreasureKind.NECKLACE.equals(VisibleTreasures.get(i).getType())){
                haveNecklace = true;
            }
        }
        
        for(int i = 0; i < VisibleTreasures.size();i++){
            if(haveNecklace)
                totallevel += VisibleTreasures.get(i).getMaxBonus();
            else{
                totallevel += VisibleTreasures.get(i).getMinBonus();
            }
        }
        return totallevel;
    }
    
    /*Devuelve true cuando el jugador no tiene ningún mal rollo que cumplir
(pendingBadConsequence.isEmpty() == true) y false en caso contrario.*/
    
    public boolean validState(){
        boolean isValid = true;
        if(!pendingBadConsequence.isEmpty()){
            isValid = false;
        }
        if(HiddenTreasures.size()>4){
            isValid = false;
        }        
        return isValid;
    }
    
    public void initTreasures(){
        CardDealer cd = CardDealer.getInstance();        
        Dice dice = Dice.getInstance();
        Treasure t;
        if(isDead()){
            bringToLife();
        }
        int number = dice.nextNumber();
        if(number == 1){
            t = cd.nextTreasure();
            HiddenTreasures.add(t);
        }
        else if(number < 6){
            for(int i = 1; i <= 2;i++){
                t = cd.nextTreasure();
                HiddenTreasures.add(t);
            }
        }
        else if(number == 6){
             for(int i = 1; i <= 3;i++){
                t = cd.nextTreasure();
                HiddenTreasures.add(t);
            }
        }
    }
    
    /*Devuelve true si el jugador está muerto, false en caso contrario.*/
    
    public boolean isDead() {
        return dead;
    }
    
    public boolean isCultist(){
        return cultist;
    }
    /*Devuelve true cuando el jugador tiene algún tesoro visible y false en 
    caso contrario.*/
    
    public boolean hasVisibleTreasures(){
        boolean hastreasure = false;
        if(VisibleTreasures.size() > 0)
            hastreasure = true;
        return hastreasure;   
    }

    public ArrayList<Treasure> getVisibleTreasures() {
        return VisibleTreasures;
    }
    
    public ArrayList<Treasure> getHiddenTreasures() {
        return HiddenTreasures;
    }
    
    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return level;
    }
    
    public BadConsequence getPendingBadConsequence(){
        return pendingBadConsequence;
    }
    
    public int getOponentLevel(){
        int mlevel;
        if (!isCultist())
            mlevel = Napakalaki.getInstance().getCurrentMonster().getBasicValue();
        else
            mlevel = Napakalaki.getInstance().getCurrentMonster().getSpecialValue();
        return mlevel;
    }
    
    /*En el método shouldConvert() de la clase Player se lanzará el dado y 
    devolverá true si se obtiene un 6 y false en caso contrario. Su redefinición
    en CultistPlayer devolverá siempre false.*/
    
    public boolean shouldConvert(){
        boolean convert = false;
        if(Dice.getInstance().nextNumber() == 6)
            convert = true;
        return convert;
    }
}