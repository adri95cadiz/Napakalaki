#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Napakalaki

  class Treasure  
  
    attr_reader :name, :gold_coins, :min_bonus, :max_bonus, :type
    
    def initialize(n, g, min, max, t)
      @name = n
      @gold_coins = g
      @min_bonus = min
      @max_bonus = max
      @type = t
    end
    
    def to_s
      return " Nombre: #{@name}. Tipo: #{@type}. Valor: #{@gold_coins}. Bonus Mínimo: #{@min_bonus}. Bonus Máximo #{@max_bonus}."
    end
     
  end
end