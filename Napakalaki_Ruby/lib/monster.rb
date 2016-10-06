# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

  require_relative 'bad_consequence.rb'
  require_relative 'prize.rb'
  
module Napakalaki
  
  class Monster
    attr_reader :name, :combat_level, :buen_rollo, :mal_rollo, :level_change_against_cultist_player
    
    def initialize(nombre, level, malR, buenR, levelC)
      @name=nombre
      @combat_level=level
      @mal_rollo=malR
      @buen_rollo=buenR
      @level_change_against_cultist_player=levelC
    end
    
    def to_s()      
      "Nombre:  #{@name}. Nivel:  #{@combat_level} Nivel contra sectarios: #{get_special_value}.\n Buen Rollo:  #{@buen_rollo.to_s}\n Mal Rollo:  #{@mal_rollo.to_s}"   
    end
    
    def kills()
      return mal_rollo.death()
    end
    
    def get_levels_gained()
      return buen_rollo.level()
    end
    
    def get_treasures_gained()      
      return buen_rollo.treasure()
    end
    
    def get_basic_value() 
        return @combat_level
    end

    def get_special_value() 
        return @combat_level + @level_change_against_cultist_player
    end
    
  end  
end
