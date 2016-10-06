/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MainPack;

/* 
//Imports para el modo texto

import napakalaki.CombatResult;
import napakalaki.Napakalaki;
import TextUI.TextUI;

*/

//Imports para el modo grafico
import napakalaki.Napakalaki;
import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;

/**
 *
 * @author Lothar
 */
public class Main {
 public static void main(String[] args){
     
        //MAIN PARA EL MODO GRAFICO

        Napakalaki napakalakiModel = Napakalaki.getInstance();
        NapakalakiView napakalakiView = new NapakalakiView();
        napakalakiView.showView();
        ArrayList<String> names = new ArrayList<>();
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        names = namesCapture.getNames();
        napakalakiModel.initGame(names);
        napakalakiView.setNapakalaki(napakalakiModel);
        napakalakiView.repaint();
        napakalakiView.revalidate();        
        
        /*      // MAIN PARA EL MODO TEXTO
        
        Napakalaki napakalakiModel = Napakalaki.getInstance();
        TextUI action = TextUI.getInstance();
        CombatResult state=CombatResult.LOSE;
        boolean canI;
        
        action.initplayers();
        
        
        while(!napakalakiModel.endOfGame(state)){
            boolean stateOK = false;
            action.dump();
            System.out.println("\nEs turno de: " + 
                    napakalakiModel.getCurrentPlayer().getName() + ", Tienes el nivel: " + 
                    napakalakiModel.getCurrentPlayer().getLevel());

            if(napakalakiModel.getCurrentPlayer().isDead()){
                System.out.println("¡¡Has revivido!!, lanzas el dado para "
                        + "obtener nuevas cartas: ");
                napakalakiModel.getCurrentPlayer().initTreasures();
            }
            System.out.println("Cartas Visibles: ");
            for(int i = 0; i < napakalakiModel.getVisibleTreasures().size(); i++){
            System.out.println("Treasure " + i + ": " + 
                    napakalakiModel.getCurrentPlayer().getVisibleTreasures().get(i));
            }
            System.out.println("Cartas ocultas: ");
            for(int i = 0; i < napakalakiModel.getHiddenTreasures().size(); i++){
            System.out.println("Treasure " + i + ": " + 
                    napakalakiModel.getCurrentPlayer().getHiddenTreasures().get(i));
            }
            
            
            canI = action.buylevels();
            if(canI){
                System.out.println("Has comprado niveles, te descartas de lo "
                        + "seleccionado.");
            }
            else{
                System.out.println("No puedes comprar niveles ahora.");
            }
            state = action.combat();
            action.dump();
            System.out.println("Cartas Visibles: ");
            for(int i = 0; i < napakalakiModel.getVisibleTreasures().size(); i++){
            System.out.println("Treasure " + i + ": " + 
                    napakalakiModel.getCurrentPlayer().getVisibleTreasures().get(i));
            }
            System.out.println("Cartas ocultas: ");
            for(int i = 0; i < napakalakiModel.getHiddenTreasures().size(); i++){
            System.out.println("Treasure " + i + ": " + 
                    napakalakiModel.getCurrentPlayer().getHiddenTreasures().get(i));
            }
            
            
            while(!stateOK){
                if(napakalakiModel.nextTurnAllowed()){
                    action.maketreasurevisible();
                    action.dump();
                }
                stateOK = napakalakiModel.nextTurn();

                if(!stateOK){
                   action.discardtreasures();
                }
            }
            System.out.println("HAS TERMINADO TU TURNO!!");
        }
    */
 }
}
