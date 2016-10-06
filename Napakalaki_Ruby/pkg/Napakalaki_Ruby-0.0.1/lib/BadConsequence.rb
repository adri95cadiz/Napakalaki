# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki
  
    class BadConsequence 
      
        attr_reader :text, :levels, :nVisibleTreasures, :nHiddenTreasures, :death
        
        specificHiddenTreasures = Array.new
        specificVisibleTreasures = Array.new
        
        def initialize(nombre, level, nVisibleTreasure, nHiddenTreasure, tVisible, tHidden, muerte)
            @text = nombre
            @levels = level
            @nVisibleTreasures = nVisibleTreasure
            @nHiddenTreasures = nHiddenTreasure
            @death = muerte  
            @specificHiddenTreasures = tHidden.clone
            @specificVisibleTreasures = tVisible.clone
        end
        def self.newLevel(nombre, level, nVisibleTreasure, nHiddenTreasure)
            newObj=allocate
            newObj.send(:initialize,nombre,level,nVisibleTreasure,nHiddenTreasure,[],[],false)
            newObj
        end            
            
        def self.newDeath(texto, muerte)
            newObj=allocate  
            newObj.send(:initialize,texto,nil,nil,nil,[],[],muerte)
            newObj
        end
                
        def self.newTreasure(nombre, level, tVisible, tHidden)
            newObj=allocate
            newObj.send(:initialize,nombre,level,nil,nil,tVisible,tHidden,false)
            newObj
        end
        
        def to_s()
          "Text =  #{@text}. Levels =  #{@levels}. VisibleTreasures =  #{@nVisibleTreasures}, #{@specificVisibleTreasures}. HiddenTreasures =  #{@nHiddenTreasures}, #{@specificHiddenTreasures}. Death = #{@death}."  
        end
    end
end
