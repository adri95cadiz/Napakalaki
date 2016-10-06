/**
 * 
 */

package napakalaki;

import java.util.ArrayList;

public class Napakalaki {
    //Datos Miembros
    private static final Napakalaki instance = new Napakalaki();

    private Player currentPlayer;
    private int currentIndexPlayer;
    private Monster currentMonster;
    private ArrayList<Player> players;

    /*Constructor (es privado para que la clase sea singleton), no conocemos la
    implementación del constructor probablemente inicie a los players a traves 
    de una peticion, y posteriormente inicie el juego con initgame() por ello:
    */
    private Napakalaki(){
         Player currentPlayer = null;
         currentIndexPlayer = -1;
         currentMonster = null;
         players = null;
    }
    /*
    Inicializa la lista de jugadores a partir de sus nombres
    Ademas del indice de primer jugador y el jugador actual.
    */
    private void initPlayers(ArrayList<String> names){
        players = new ArrayList<>();
        if(names.size()>0){
            for(int i=0; i<names.size(); i++){
                Player newPlayer = new Player(names.get(i));
                newPlayer.initTreasures();
                players.add(newPlayer);
            }
            currentIndexPlayer = Dice.getInstance().nextNumber()%names.size();
            currentPlayer = players.get(currentIndexPlayer);
            currentMonster= CardDealer.getInstance().nextMonster();
        }
        else{
            System.out.println("DEPURACION DE ERROR: Lista de nombres vacia");
        }
    }
    
    public void initGame(ArrayList<String> players){
        CardDealer dealer = CardDealer.getInstance();
        dealer.initCards(); //Inicializa y baraja
        initPlayers(players);
    }
        
    /* Actualiza el jugador actual y su indice, teniendo en cuenta que despues
    del ultimo jugador le toca de nuevo al primero.
    */
    private Player nextPlayer(){
        currentIndexPlayer = (currentIndexPlayer+1) % players.size();
        currentPlayer = players.get(currentIndexPlayer);
        return currentPlayer;
    }

    /*Combat devuelve el resultado del combate entre el jugador actual y
    el monstruo actual.
    */
    public CombatResult combat(){
        CombatResult stat; 
        stat = currentPlayer.combat(currentMonster);
        
        //En caso de perder y convertirse el jugador actual se convierte en cultist
        if(stat==CombatResult.LOSEANDCONVERT){
            Cultist cultistCard = CardDealer.getInstance().nextCultist();
            CultistPlayer cPlayer = new CultistPlayer(currentPlayer, cultistCard);
            currentPlayer = cPlayer;
            players.set(currentIndexPlayer, cPlayer);
        }
        return stat;
    }
    /*
    El jugador descarta el tesoro t de su lista de tesoros visibles
    */
    public void discardVisibleTreasure(Treasure t){
        currentPlayer.discardVisibleTreasure(t);
    }
    /*
    El jugador descarta el tesoro t de su lista de tesoros ocultos
    */
    public void discardHiddenTreasure(Treasure t){
        currentPlayer.discardHiddenTreasure(t);
    }
     /*
    El jugador se equipa con uno de los tesoros ocultos (t) y lo pasa a la lista 
    de tesoros visibles.
    Devuelve true en el caso de que se haya podido hacer visible.
    */
    public boolean makeTreasureVisible(Treasure t){
        boolean canM;
        if(canMakeTreasureVisible(t)){
            currentPlayer.makeTreasureVisible(t);
            canM=true;
        }
        else
            canM = false;
        return canM;
    }

    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden){
        boolean canI = currentPlayer.buyLevels(visible, hidden);
        return canI;
    }


    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Monster getCurrentMonster(){
        return currentMonster;
    }

    public boolean canMakeTreasureVisible(Treasure t){
        boolean canM = currentPlayer.canMakeTreasureVisible(t);
        return canM;
    }

    public ArrayList<Treasure> getVisibleTreasures(){
        return currentPlayer.getVisibleTreasures();                         
    }

    public ArrayList<Treasure> getHiddenTreasures(){
        return currentPlayer.getHiddenTreasures();                          
    }

    public boolean nextTurn(){
        CardDealer cd = CardDealer.getInstance();
        boolean stateOK;                           
        boolean dead;
        stateOK = nextTurnAllowed();
        if(stateOK){
            currentMonster = cd.nextMonster();
            currentPlayer = nextPlayer();
            dead = currentPlayer.isDead();
            if(dead){
                currentPlayer.initTreasures();
            }
            return true;
        }
        else
            return false;
        
    }

    public boolean nextTurnAllowed(){
        return currentPlayer.validState();
    }
    public boolean endOfGame(CombatResult result){
        if(result == CombatResult.WINANDWINGAME){
            return true;
        }
        else
            return false;
    }

    //getter
    public static Napakalaki getInstance() {
        return instance;
    }
}
