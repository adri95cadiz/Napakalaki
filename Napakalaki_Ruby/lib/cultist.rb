# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


module Napakalaki
  
  class Cultist
    #Atributos
    attr_reader :name, :gained_levels

    #Constructor
    def initialize(name,gained_levels)
      @name = name
      @gained_levels = gained_levels

    end
    
    #Métodos
    def basic_value
        return @gained_levels;
    end

    def special_value
      return basic_value * CultistPlayer.total_cultist_players;
    end
    
    
    def to_s
      return " Nombre: #{@name} Tipo: #{@type}"
    end

  end
  
end
