/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package napakalaki;

import java.util.ArrayList;

/**
 *
 *
 */
public class CultistPlayer extends Player{    
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player player, Cultist myCultistCard){
        super(player);
        this.myCultistCard = myCultistCard;
        cultist = true;
    }
    
    public Cultist getMyCultistCard(){
        return myCultistCard;
    }
    
    /*El método getCombatLevel() se redefine en la clase CultistPlayer para 
    calcular su nivel de combate sumando el getCombatLevel() de Player más lo 
    que devuelve el método getSpecialValue() de su carta de sectario (Cultist).
    */
    @Override
    public int getCombatLevel(){
        return super.getCombatLevel() + myCultistCard.getSpecialValue();
    }
    
    /*En el método shouldConvert() de la clase Player se lanzará el dado y 
    devolverá true si se obtiene un 6 y false en caso contrario. Su redefinición
    en CultistPlayer devolverá siempre false.*/
    
    @Override
    public boolean shouldConvert(){
        return false;
    }
    
    @Override
    public int getOponentLevel(){
        int mlevel;
        mlevel = Napakalaki.getInstance().getCurrentMonster().getSpecialValue();
        return mlevel;
    }
    
    /*El método computeGoldCoinsValue() se redefine en CultistPlayer de manera 
    que las piezas de oro dupliquen su valor a la hora de canjearlas por 
    niveles.*/
    public int computeGoldCoinsValue(ArrayList<Treasure> t){
        int maxcoins = 0, N=t.size(),canbuylevels;
        for(int i = 0; i < N; i++){
            maxcoins += 2*t.get(i).getGoldCoins();
        }
        canbuylevels = maxcoins/1000;
        return canbuylevels;
    }
    
    public static int getTotalCultistPlayers(){
        return totalCultistPlayers;
    }
    
}
