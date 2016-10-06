/**
 *
 */
package napakalaki;

import java.util.ArrayList;
import static java.util.Collections.shuffle;

public class CardDealer {
    //Datos Miembros
    private static CardDealer instance = new CardDealer();
   
    private ArrayList<Monster> unusedMonsters;
    private ArrayList<Monster> usedMonsters;
    private ArrayList<Treasure> unusedTreasures;
    private ArrayList<Treasure> usedTreasures;   
    private ArrayList<Cultist> unusedCultists;  //Nunca se descartan asique no necesitamos used y unused
    
    //Constructor (es privado para que la clase sea singleton);
    
    //Constructor (es privado para que la clase sea singleton)
    private CardDealer(){
        unusedMonsters = new ArrayList<>();
        usedMonsters = new ArrayList<>();
        unusedTreasures = new ArrayList<>();
        usedTreasures = new ArrayList<>();
        unusedCultists = new ArrayList<>();
    }
    
    //añadidos para comprobar el estado del juego.
    public ArrayList<Monster> getUnusedMonsters() {
        return unusedMonsters;
    }

    public ArrayList<Treasure> getUnusedTreasures() {
        return unusedTreasures;
    }
    
    /*
    Inicializa el mazo de Cartas de Tesoros (unusedTreasures)
    */
    private void initTreasureCardDeck(){
        Treasure item = new Treasure("¡Si mi amo!", 0, 4, 7, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("Botas de investigacion", 600, 3, 4, TreasureKind.SHOE);
        unusedTreasures.add(item);
        item = new Treasure("Capucha de Cthulu", 500, 3, 5, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("A prueba de babas verdes", 400, 3, 5, TreasureKind.ARMOR);
        unusedTreasures.add(item);
        item = new Treasure("Botas de lluvia acida", 800, 1, 1, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Casco minero", 400, 2, 4, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("Ametralladora Thompson", 600, 4, 8, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Camiseta de la UGR", 100, 1, 7, TreasureKind.ARMOR);
        unusedTreasures.add(item);
        item = new Treasure("Clavo de rail ferroviario", 400, 3, 6, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Cuchillo de sushi arcano", 300, 2, 3, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Fez alópodo", 700, 3, 5, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("Hacha prehistorica", 500, 2, 5, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("El aparato del Pr. Tesla", 900, 4, 8, TreasureKind.ARMOR);
        unusedTreasures.add(item);
        item = new Treasure("Gaita", 200, 1, 5, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Insecticida", 300, 2, 3, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Escopeta de tres cañones", 700, 4, 6, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Garabato místico", 300, 2, 2, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("La rebeca metalica", 400, 2, 3, TreasureKind.ARMOR);
        unusedTreasures.add(item);
        item = new Treasure("Mazo de los antigos", 200, 3, 4, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Necrocomicon", 100, 1, 1, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Necronomicon", 800, 5, 7, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Linterna a 2 manos", 400, 3, 6, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Necrognomicon", 200, 2, 4, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Necrotelecom", 300, 2, 3, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("Porra preternatural", 200, 2, 3, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        item = new Treasure("Tentaculo de pega", 200, 0, 1, TreasureKind.HELMET);
        unusedTreasures.add(item);
        item = new Treasure("Zapatilla deja-amigos", 500, 0, 1, TreasureKind.SHOE);
        unusedTreasures.add(item);
        item = new Treasure("Shogulador", 600, 1, 1, TreasureKind.BOTHHANDS);
        unusedTreasures.add(item);
        item = new Treasure("Varita de atizamiento", 400, 3, 4, TreasureKind.ONEHAND);
        unusedTreasures.add(item);
        //Objeto especial (La fuerza de Mr.T)
        item = new Treasure("La fuerza de Mr.T", 1000, 0, 0, TreasureKind.NECKLACE);
        unusedTreasures.add(item);
    }
    
    /*
    Inicializa el mazo de Cartas de Monstruos (unusedMonster)
    */
    private void initMonsterCardDeck(){
        ArrayList<TreasureKind> tvp = new ArrayList();
        ArrayList<TreasureKind> top = new ArrayList();
        
        top.add(TreasureKind.ARMOR);
        tvp.add(TreasureKind.ARMOR);
        BadConsequence n_bad = new BadConsequence("Pierdes tu armadura visible "
                + "y otra oculta.", 0, tvp, top);
        Prize n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("3 Byakhees de bonanza", 8, n_bad, n_prize, 0));
        tvp.clear();
        top.clear();
        
        tvp.add(TreasureKind.HELMET);
        n_bad = new BadConsequence("Embobados con el linfo "
                + "primigenio te descrtas de tu casco visible.", 5, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Chibithulu", 2, n_bad, n_prize,0));
        tvp.clear();
        
        tvp.add(TreasureKind.SHOE);
        n_bad = new BadConsequence("El primordial bostezo "
                + "contagioso.Pierdes el calzado visible.", 0, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("El sopor de Dunwich", 2, n_bad, n_prize, 0));
        tvp.clear();
        
        tvp.add(TreasureKind.ONEHAND);
        top.add(TreasureKind.ONEHAND);
        n_bad = new BadConsequence("Te atrapan para llevarte de"
                + " fiesta y te dejan caer en la mitad del vuelo. Descarta 1 "
                + "mano visible y 1 mano oculta.", 0, tvp, top);
        n_prize = new Prize(4,1);
        unusedMonsters.add(new Monster("Angeles de la noche ibicenca", 14, n_bad, 
                n_prize, 0));
        top.clear();
        tvp.clear();
        // Todos los tesoros <=> BadConsequence.getMaxTreasureLost()
        n_bad = new BadConsequence("Pierdes todos tus tesoros "
                + "visibles.", 0, BadConsequence.getMaxTreasureLost(), 0);
        n_prize = new Prize(3,1);
        unusedMonsters.add(new Monster("El gorron en el umbral", 10, n_bad,
                n_prize, 0));
        tvp.clear();
        
        tvp.add(TreasureKind.ARMOR);
        n_bad = new BadConsequence("Pierdes la armadura "
                + "visible.", 0, tvp, top);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("H.P. Munchcraft", 6, n_bad, n_prize, 0));
        tvp.clear();
        
        tvp.add(TreasureKind.ARMOR);
        n_bad = new BadConsequence("Sientes bichos bajo la "
                + "ropa descarta la armadura visible.", 0, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Bichgooth", 2, n_bad, n_prize, 0));
        tvp.clear();
        
        n_bad = new BadConsequence("Pierdes 5 niveles y 3 "
                + "tesoros visibles.", 5, 3, 0);
        n_prize = new Prize(4,2);
        unusedMonsters.add(new Monster("El rey de rosa", 13, n_bad, n_prize, 0));
        tvp.clear();
        
        n_bad = new BadConsequence("Tose los pulmones y pierdes"
                + " 2 niveles.", 2, 0, 0);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("La que cose en las sombras", 13, n_bad, 
                n_prize,0));
        
        n_bad = new BadConsequence("Estos monstruos resultan "
                + "bastante superficiales y te aburren mortalmente. Estas "
                + "muerto.", true);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Los hondos verdes", 7, n_bad, n_prize, 0));
        
        n_bad = new BadConsequence("Pierdes 2 niveles y 2"
                + " tesoros ocultos.", 2, 0, 2);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Semillas Cthulhu", 4, n_bad,n_prize, 0));
        top.clear();
        
        tvp.add(TreasureKind.ONEHAND);
        n_bad = new BadConsequence("Te intentas escaquear. "
                + "Pierdes una mano visible.", 0, tvp, top);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Dameargo", 1, n_bad, n_prize, 0));
        tvp.clear();
        
        n_bad = new BadConsequence("Da mucho asquito. Pierdes"
                + " 3 niveles.", 3, 0, 0);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Pollipolipo volante", 3, n_bad, n_prize, 0));
        
        n_bad = new BadConsequence("No le hace mucha gracia "
                + "que pronuncien mal si nombre. Estas muerto", true);
        n_prize = new Prize(3,1);
        unusedMonsters.add(new Monster("Yskhtihyssg-Goth", 12, n_bad, n_prize, 0));
        
        n_bad = new BadConsequence("La familia te atrapa. "
                + "Estas muerto.", true);
        n_prize = new Prize(4,1);
        unusedMonsters.add(new Monster("Familia feliz", 1, n_bad, n_prize, 0));
        
        tvp.add(TreasureKind.BOTHHANDS);
        n_bad = new BadConsequence("La quinta directiva "
                + "primaria te obliga a perder 2 niveles y un tesoro 2 manos "
                + "visible", 0, tvp, top);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Roboggoth", 8, n_bad, n_prize, 0));
        tvp.clear();
        
        tvp.add(TreasureKind.HELMET);
        n_bad = new BadConsequence("Te asusta en la noche. "
                + "Pierdes un casco visible", 0, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("El espia ciego", 4, n_bad, n_prize, 0));
        tvp.clear();
        
        n_bad = new BadConsequence("Menudo suste te llevas. "
                + "Pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("El lenguas", 20, n_bad, n_prize, 0));
        tvp.clear();
        
        tvp.add(TreasureKind.ONEHAND);
        tvp.add(TreasureKind.BOTHHANDS);
        n_bad = new BadConsequence("Te faltan manos para tanta"
                + " cabeza. Pierdes 3 niveles y tus tesoros visibles de las "
                + "manos", 3, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Bicefalo", 20, n_bad, n_prize, 0));
        tvp.clear();
        
        //Cartas Monstruos CON sectarios
        
        tvp.add(TreasureKind.ONEHAND);
        n_bad = new BadConsequence("Pierdes 1 mano visible", 3, tvp, top);
        n_prize = new Prize(3,1);
        unusedMonsters.add(new Monster("El mal indecible "
                + "impronunciable", 10, n_bad, n_prize,-2));
        tvp.clear();
        
        n_bad = new BadConsequence("Pierdes tus tesoros visibles. "
                + "Jajaja.",  0, 10, 0);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Testigos Oculares", 6, n_bad, n_prize, 2));
        
        n_bad = new BadConsequence("Hoy no es tu dia de suerte. Mueres.", true);
        n_prize = new Prize(2,5);
        unusedMonsters.add(new Monster("El gran cthulhu", 20, n_bad, n_prize,4));
        
        n_bad = new BadConsequence("Tu gobierno te recorta 2 niveles.", 2, 0, 0);
        n_prize = new Prize(2,1);
        unusedMonsters.add(new Monster("Serpiente Politico", 8, n_bad, n_prize,-2));
        
        tvp.add(TreasureKind.HELMET);
        tvp.add(TreasureKind.ARMOR);
        top.add(TreasureKind.ONEHAND);
        n_bad = new BadConsequence("Pierdes tu casco y tu armadura visible. "
                + "Pierdes tus manos ocultas.", 3, tvp, top);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Felpuggoth", 2, n_bad, n_prize,5));
        tvp.clear();
        top.clear();
        
        n_bad = new BadConsequence("Pierdes 2 niveles.", 2, 0, 0);
        n_prize = new Prize(4,2);
        unusedMonsters.add(new Monster("Shoggoth", 16, n_bad, n_prize,-4));
        
        n_bad = new BadConsequence("Pintalabios negro."
                + " Pierdes 2 niveles.", 2, 0, 0);
        n_prize = new Prize(1,1);
        unusedMonsters.add(new Monster("Lolitagooth", 2, n_bad, n_prize,3));
    }
    
    /*Baraja el mazo de cartas de tesoros unusedTreasures.*/
    private void shuffleTreasures(){
        shuffle(unusedTreasures);
    }
    /*Baraja el mazo de cartas de monstruos unusedMonsters.*/
    private void shuffleMonsters(){ 
        shuffle(unusedMonsters);
    }
    //Devuelve la unica instancia de la clase CardDealer (Singleton)
    public static CardDealer getInstance() {
        return instance;
    }
    
    /*Devuelve el siguiente tesoro que hay en el mazo de tesoros 
    (unusedTreasures) y lo elimina de él. Si el mazo está vacío, pasa el mazo de
    descartes (usedTreasures) al mazo de tesoros y lo baraja, dejando el mazo de
    descartes vacío.
    */
    
    public Treasure nextTreasure(){
        Treasure t = unusedTreasures.get(0);
        usedTreasures.add(t);
        unusedTreasures.remove(0);
        if (unusedTreasures.isEmpty()){
            initTreasureCardDeck();
            shuffleTreasures();
        }
        return t;
    }
    /*Devuelve el siguiente monstruo que hay en el mazo de monstruos 
    (unusedMonsters) y lo elimina de él. Si el mazo está vacío, pasa el mazo de
    descartes (usedMonsters) al mazo de tesoros y lo baraja, dejando el mazo de
    descartes vacío.
    */
    public Monster nextMonster(){
        Monster m = unusedMonsters.get(0);
        usedMonsters.add(m);
        unusedMonsters.remove(0);
        if (unusedMonsters.isEmpty()){
            initMonsterCardDeck();
            shuffleMonsters();
        }
        return m;
    }
    
    /*Introduce en el mazo de descartes de tesoros (usedTreasures) 
    el tesoro t.*/
    public void giveTreasureBack(Treasure t){
        usedTreasures.add(t);
    }
    /*Introduce en el mazo de descartes de monstruos (usedMonsters)
    el monstruo m.*/
    public void giveMonsterBack(Monster m){
      usedMonsters.add(m);
    }
    /*Inizializa el mazo de cartas de monstruos y el mazo para los tesoros 
    y los baraja.
    */
    public void initCards(){
        initTreasureCardDeck();
        initMonsterCardDeck();
        initCultistCardDeck();
        shuffleTreasures();
        shuffleMonsters();
        shuffleCultists();
    }    
    
    private void shuffleCultists(){ 
        shuffle(unusedCultists);
    }
    
    private void initCultistCardDeck() {
        //Cuatro cartas con +1
        for(int i = 1; i <=4; i++){
            unusedCultists.add(new Cultist ("+1 por cada sectario en juego "
                + "No puedes dejar de ser sectario", 1));
        }
        //Dos cartas con +2
        unusedCultists.add(new Cultist ("+2 por cada sectario en juego "
                + "No puedes dejar de ser sectario", 2));
        unusedCultists.add(new Cultist ("+2 por cada sectario en juego "
                + "No puedes dejar de ser sectario", 2));
    }
    
    public Cultist nextCultist(){
        return unusedCultists.get(CultistPlayer.getTotalCultistPlayers());
    } 
}