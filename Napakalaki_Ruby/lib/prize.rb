# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki  
  
    class Prize

      attr_reader :treasure, :level
      
      def initialize(tesoro,nivel)
        @treasure = tesoro
        @level = nivel
      end
    
      def to_s()
        "Tesoros: #{@treasure}. Nivel: #{@level}."
      end
      
    end    
end
