/**
 * 
 */

package TextUI;

import java.util.ArrayList;
import napakalaki.CardDealer;
import napakalaki.CombatResult;
import napakalaki.Napakalaki;
import napakalaki.Treasure;
import static TextUI.IO.readInteger;
import static TextUI.IO.readString;

public class TextUI {
    private Napakalaki game = Napakalaki.getInstance();
    private static TextUI instance = new TextUI();
    
    private TextUI(){
    }
    
    public static TextUI getInstance() {
        return instance;
    }
    
    public void initplayers(){
        ArrayList<String> names = new ArrayList<>();
        int num_players = readInteger("Introduce Nº Jugadores: ");
        while(num_players <=0 || num_players > 3){
            num_players = readInteger("Introduce Nº Jugadores (menor que tres): ");
        }
        for(int i=0; i<num_players; i++){
            String name = readString("Jugador "+ i + ": ");
            names.add(name);
        }
        game.initGame(names);
    }
    
public boolean buylevels(){
        String option;
        boolean canI;
        int option_num;
        ArrayList<Treasure> sellVisible = new ArrayList<>();
        ArrayList<Treasure> sellHidden  = new ArrayList<>(); 
        if(!game.getCurrentPlayer().getVisibleTreasures().isEmpty()){
            option = readString("Quieres vender objetos visibles por niveles(s/n): ");
            while(!option.equals("n") && !(game.getVisibleTreasures().size() == sellVisible.size())){
                option_num = readInteger("¿Que objetos visibles quieres vender?(0,1,2...)");
                while(0 > option_num || option_num >= game.getVisibleTreasures().size() ){
                    option_num = readInteger("Da un numero entre 0 y el numero de objetos: ");
                }
                sellVisible.add(game.getVisibleTreasures().get(option_num));
                option = readString("Quieres vender otro objetos por niveles(s/n): ");
            }
        }
        if(!game.getCurrentPlayer().getHiddenTreasures().isEmpty()){
            option = readString("Quieres vender objetos ocultos por niveles(s/n): ");
            while(!option.equals("n") && !(game.getHiddenTreasures().size() == sellHidden.size())){
                option_num = readInteger("¿Que objetos ocultos quieres vender?(0,1,2...)");
                while(option_num < 0 || option_num >= game.getHiddenTreasures().size() ){
                    option_num = readInteger("Da un numero entre 0 y el numero de objetos: ");
                }
                sellHidden.add(game.getHiddenTreasures().get(option_num));
                option = readString("Quieres vender otro objeto por niveles(s/n): ");
            }
        }
        canI = game.buyLevels(sellVisible, sellHidden);
        return canI;
    }
    
    public CombatResult combat(){
        CombatResult state;
        System.out.println("\nVas a luchar contra el monstruo: " + game.getCurrentMonster().toString());
        state = game.combat();
        System.out.println("You " + state + "!!.");
        System.out.println("Sales del combate.\n");
        return state;
    }
    
    public void maketreasurevisible(){
        String option; int option_num;
        if(!game.getCurrentPlayer().getHiddenTreasures().isEmpty()){
            option = readString("¿Quieres hacer alguna carta visible?(s/n)");
            while(!option.equals("n") && game.getHiddenTreasures().size() != 0){
                option_num = readInteger("¿Que tesoro quieres?(0,1,2):");
                while(option_num < 0 || option_num >= game.getHiddenTreasures().size() ){
                    option_num = readInteger("Da un numero entre 0 y el numero de objetos: ");
                }
                game.makeTreasureVisible(game.getHiddenTreasures().get(option_num));
                option = readString("¿Quieres hacer alguna otra carta visible?(s/n)");
            }
        }
    }
    
    public void discardtreasures(){
        String option;
        int option_num;
        System.out.println("objetos a descartar: Visibles: " + 
                game.getCurrentPlayer().getPendingBadConsequence().getSpecificVisibleTreasures() +
                " Ocultos: " + game.getCurrentPlayer().getPendingBadConsequence().getSpecificHiddenTreasures());
        
        while(!game.nextTurnAllowed()){
            if(game.getCurrentPlayer().getPendingBadConsequence().getnVisibleTreasures() != 0){
                System.out.println("Cartas Visibles disponibles: ");
                for(int i = 0; i < game.getVisibleTreasures().size(); i++){
                System.out.println("Treasure " + i + ": " + 
                        game.getCurrentPlayer().getVisibleTreasures().get(i));
                }                
                option_num = readInteger("¿Que tesoro visible quieres descartar?(0,1,2...):");
                while(option_num < 0 || option_num >= game.getVisibleTreasures().size() ){
                    option_num = readInteger("Da un numero entre 0 y el numero de objetos: ");
                }
                for(int i = 0; i < game.getCurrentPlayer().getPendingBadConsequence().getSpecificVisibleTreasures().size(); i++){
                    if(game.getVisibleTreasures().get(option_num).getType().equals(
                            game.getCurrentPlayer().getPendingBadConsequence().getSpecificVisibleTreasures().get(i)))
                        game.discardVisibleTreasure(game.getVisibleTreasures().get(option_num));
                    else
                        System.out.println("No es el objeto correcto.");
                }
            }
            else{
                System.out.println("Cartas ocultas disponibles: ");
                for(int i = 0; i < game.getHiddenTreasures().size(); i++){
                System.out.println("Treasure " + i + ": " + 
                        game.getCurrentPlayer().getHiddenTreasures().get(i));
                }
                option_num = readInteger("¿Que tesoro oculto quieres descartar?(0,1,2...):");
                while(option_num < 0 || option_num >= game.getHiddenTreasures().size()){
                    option_num = readInteger("Da un numero entre 0 y el numero de objetos: ");
                }
                for(int i = 0; i < game.getCurrentPlayer().getPendingBadConsequence().getSpecificHiddenTreasures().size(); i++){
                    if(game.getHiddenTreasures().get(option_num).getType().equals(
                            game.getCurrentPlayer().getPendingBadConsequence().getSpecificHiddenTreasures().get(i)))
                        game.discardHiddenTreasure(game.getHiddenTreasures().get(option_num));
                    else
                        System.out.println("No es el objeto correcto.");
                }
           }
        }
    }
    
    public void dump(){
        CardDealer cd = CardDealer.getInstance();
        int N = game.getCurrentPlayer().getVisibleTreasures().size();
        int M = game.getCurrentPlayer().getHiddenTreasures().size();
        System.out.println("\n[Eres el jugador: " + 
                game.getCurrentPlayer().getName() + ", Level: " + 
                game.getCurrentPlayer().getLevel() + "]");
        if(game.getCurrentPlayer().isCultist()){
           System.out.println("Eres Sectario!!");
        }
        System.out.println("Lista de Tesoros visibles de "+ 
                game.getCurrentPlayer().getName());
        for(int i = 0; i < N; i++){
            System.out.println("Treasure " + i + ": " + 
                    game.getCurrentPlayer().getVisibleTreasures().get(i));
        }
        System.out.println("Lista de Tesoros ocultos de "+ 
                game.getCurrentPlayer().getName());
        for(int i = 0; i < M; i++){
            System.out.println("Treasure " + i + ": " + 
                    game.getCurrentPlayer().getHiddenTreasures().get(i));
        }
        
        System.out.println("Vas a luchar con el monstruo: " + 
                game.getCurrentMonster().toString());
        
        System.out.println("Cartas sin usar(M/T): (" + 
                cd.getUnusedMonsters().size() + "/" + cd.getUnusedTreasures().size() + ")\n");
    }
}
