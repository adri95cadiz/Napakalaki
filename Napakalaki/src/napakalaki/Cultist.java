/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package napakalaki;

/**
 *
 *
 */
public class Cultist implements Card{
    private String name;
    private int gainedLevels;
    
    //Constructor
    public Cultist(String name, int gainedLevels){
        this.gainedLevels = gainedLevels;
        this.name = name;
    }

    //Getters
    public String getName() {
        return name;
    }
    public int getGainedLevels() {
        return gainedLevels;
    }
    
    //Metodos sobreescritos:
    @Override
    public int getBasicValue() {
        return getGainedLevels();
    }
    @Override
    public int getSpecialValue() {
        return getBasicValue()*CultistPlayer.getTotalCultistPlayers();
    }
}
