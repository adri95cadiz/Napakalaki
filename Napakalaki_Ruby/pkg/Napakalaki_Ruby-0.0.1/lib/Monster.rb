# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
  require_relative 'BadConsequence.rb'
  require_relative 'Prize.rb'
  
module Napakalaki
  class Monster
    attr_reader :name, :combatLevel, :buenRollo, :malRollo
    
    def initialize(nombre, level, malR, buenR)
      @name=nombre
      @combatLevel=level
      @malRollo=malR
      @buenRollo=buenR
    end
    
    def to_s()      
      "Name =  #{@name}. CombatLevel =  #{@combatLevel}.\n BuenRollo:  #{@buenRollo.to_s}\n MalRollo:  #{@malRollo.to_s}"   
      
    end
  end
end
