#encoding: utf-8
#  To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

  require_relative 'treasure.rb'
  require_relative 'dice.rb'
  
module Napakalaki
  
  class Player
    
    attr_reader :dead, :name, :level, :pending_bad_consequence, :hidden_treasures, :visible_treasures, :is_cultist
    
    def initialize(name)
      @name = name
      @dead = true
      @level = 1;
      @visible_treasures = Array.new
      @hidden_treasures = Array.new
      @is_cultist = false
    end
    
    def is_dead()
      return @dead
    end    
    
    def combat(monster)
      my_level = get_combat_level()
      if is_cultist()
        mlevel = monster.get_special_value
      else
        mlevel = monster.get_basic_value      
      end
      if my_level > mlevel then
        apply_prize(monster)
        if @level >= 10 then
          result = CombatResult::WinAndWinGame
        else
          result = CombatResult::Win
        end
      else
        dice = Dice.instance
        escape = dice.next_number()
        if escape < 5 then
          dead = monster.kills()
          if dead
            die()
            result = CombatResult::LoseAndDie
          else
            mal_r = monster.mal_rollo()            
            apply_bad_consequence(mal_r)
              if(shouldConvert())
                result = CombatResult::LoseAndConvert;
              else
                result = CombatResult::Lose   
              end
          end
          else
          result = CombatResult::LoseAndEscape
        end
      end
      discard_necklace_visible()
      return result
    end
    
    def make_treasure_visible(t)
      can_i = can_make_treasure_visible(t)
      if can_i then
        @visible_treasures << t
        @hidden_treasures.delete(t)
      end
    end
    
    def discard_visible_treasure(t)
      @visible_treasures.delete(t)
      if @pending_bad_consequence != nil && !@pending_bad_consequence.is_empty()
        @pending_bad_consequence.substract_visible_treasures(t)
        if(@pending_bad_consequence.n_visible_treasures() > 0)
          @pending_bad_consequence.n_visible_treasures(@pending_bad_consequence.n_visible_treasures()-1)
        end
      end
      die_if_no_treasures()
    end
    
    def discard_hidden_treasure(t)
      @hidden_treasures.delete(t)
      if @pending_bad_consequence != nil && !@pending_bad_consequence.is_empty() then
        @pending_bad_consequence.substract_hidden_treasures(t)
        if(@pending_bad_consequence.n_hidden_treasures() > 0)
          @pending_bad_consequence.n_hidden_treasures(@pending_bad_consequence.n_hidden_treasures()-1)
        end
      end
      die_if_no_treasures()
    end
    
    def buy_levels(visible, hidden)
      levels_i_can_buy = 0;
      levels_i_can_buy += compute_gold_coins_value(visible) 
      levels_i_can_buy += compute_gold_coins_value(hidden) 
      can_i = can_i_buy_levels(levels_i_can_buy)
      increment_levels(levels_i_can_buy) if can_i
      @visible_treasures.delete(visible) 
      @hidden_treasures.delete(hidden) 
      dealer = CardDealer.instance 
      visible.each() do |t|
        dealer.give_treasure_back(t) 
      end
      hidden.each() do |t|
        dealer.give_treasure_back(t) 
      end
      return can_i
    end
    
    def valid_state()
      v_state = false
      if @pending_bad_consequence == nil && @hidden_treasures.length() <= 4
      v_state = true
      else
        v_state = true if @pending_bad_consequence.is_empty() && @hidden_treasures.length() <= 4
      end
    end
    
    def init_treasures
      dealer = CardDealer.instance
      dice = Dice.instance
      bring_to_life()
      treasure = dealer.next_treasure
      @hidden_treasures << treasure
      number = dice.next_number
      if number == 6
        treasure = dealer.next_treasure
        @hidden_treasures << treasure
        treasure = dealer.next_treasure
        @hidden_treasures << treasure 
      else
        if number > 1
        treasure = dealer.next_treasure 
        @hidden_treasures << treasure 
        end
      end
    end
          
    def has_visible_treasures()
      return @visible_treasures.size() > 0
    end
    
    def to_s
    " #{@name}. Nivel: #{@level}. Nivel de Combate: #{get_combat_level()}. Muerto: #{@dead}."
    end
      
    private
    
    def bring_to_life()
      @dead = 0
    end
    
    def get_combat_level()
      l = 0
      collar = false
      @visible_treasures.each do |t|
        if t.type == TreasureKind::Necklace then
          collar = true
          break
        end
      end
      
      @visible_treasures.each do|t|
        if( collar == true )
          l += t.max_bonus
        else
          l += t.min_bonus  
        end        
      end
    return @level + l
    end
    
    def set_pending_bad_consequence(b)
      @pending_bad_consequence = b
    end
    
    def increment_levels(l)
      @level = @level + l
    end
    
    def decrement_levels(l)
      @level = @level - l
      @level = 1 if @level < 1 
    end
    
    def die_if_no_treasures()
      if( @hidden_treasures.size() == 0 && @visible_treasures.size() == 0 )
        @dead = 1
      end
    end
    
    def discard_necklace_visible()
      @visible_treasures.each do |t|       
        if t.type() == TreasureKind::Necklace
          CardDealer.give_treasure_back(t)
          @visible_treasures.delete_at(t) 
        end
      end
    end
  
    def die()
      @level = 1
      dealer = CardDealer.instance
      @visible_treasures.each do |t|
        dealer.give_treasure_back(t)
      end
      @visible_treasures.clear()
      @visible_treasures.each do |t| 
        dealer.give_treasure_back(t)
      end
      @hidden_treasures.clear
      die_if_no_treasures()
    end
    
    def compute_gold_coins_value(t)
      coins = 0
      t.each() do |k|
        coins = coins + k.gold_coins()
      end
      levels = coins / 1000
      return levels
    end    
   
    def can_i_buy_levels(l)        
      can_i = false
      can_i = true if @level + l < 10
      return can_i
    end
    
    def apply_prize(monster)
      n_levels = monster.buen_rollo().level()
      increment_levels(n_levels)
      n_treasures = monster.get_treasures_gained()
      if n_treasures > 0
        dealer = CardDealer.instance
        while n_treasures > 0
          t = dealer.next_treasure
          @hidden_treasures << t
          n_treasures = n_treasures - 1 
        end
      end
    end
    
    def apply_bad_consequence(bad_consequence)
      n_levels = bad_consequence.levels()      
      decrement_levels(n_levels)
      @pending_bad_consequence = bad_consequence.adjust_to_fit_treasure_list(@visible_treasures, @hidden_treasures) 
      set_pending_bad_consequence(@pending_bad_consequence)
    end
    
    def can_make_treasure_visible(t)
      result = true
          if (@visible_treasures.size() < 4) 
            type = t.type();
                if type == TreasureKind::OneHand 
                  vt = @visible_treasures.clone
                  num = 0
                  vt.each do |k| 
                    num = num + 1 if (k.type() == TreasureKind::OneHand)
                  end
                  result = false if (num == 2)
                  @visible_treasures.each do |k| 
                    result = false if k.type() == TreasureKind::BothHand                    
                  end
                else
                  num = 0
                  @visible_treasures.each do |k| 
                    result = false if (k.type() == t.type())
                  end
                end
          else
            result = false
          end
      return result
    end
     
    def how_many_visible_treasures(t_kind)
      cont = 0      
      @visible_treasures.each() do |i|
        t_kind.each() do |j|
          cont = cont + 1 if ( i == j )
        end
      end
      return cont
    end  
        
    def should_convert()
        convert = false
        dice = Dice.instance
        if dice.next_number == 6
            convert = true;
        end
        return convert;
    end
  end
end
