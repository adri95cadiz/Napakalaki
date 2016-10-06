#encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

  require_relative 'bad_consequence.rb'
  require_relative 'prize.rb'
  require_relative 'monster.rb'
  require_relative 'treasure_kind.rb'
  require_relative 'player.rb'
  require_relative 'combat_result.rb'
  require_relative 'card_dealer.rb'
  require "singleton"
  
module Napakalaki
  
    class Napakalaki
      
      include Singleton
      
      attr_reader :current_player, :current_monster, :players, :dealer
      
      private
      def init_players(names)
        @players = Array.new
        @dealer = CardDealer.instance()
        names.each() do |p|
          @players << Player.new(p) 
        end
      end
      
      def next_player()
        total_players = @players.size()-1
        if (@current_player == nil)
          next_index = rand(total_players)
        else
          current_player_index = @players.index(@current_player)
          if current_player_index == total_players
            next_index = 0
          else
            next_index = current_player_index + 1
          end
        end
        next_player = @players[next_index]
        player = next_player
        return player
      end
      
      def next_turn_allowed()        
        if @current_player == nil then
          allowed = true
        else
          allowed = @current_player.valid_state()
        end
        return allowed
      end
      
      public
      
      def develop_combat()
        combat = @current_player.combat(@current_monster)
        @dealer.give_monster_back(@current_monster)  
        if combat == CombatResult::LoseAndConvert
            @dealer = CardDealer.instance()
            cultist_card = dealer.next_cultist();
            c_player = new CultistPlayer.new(@current_player, cultist_card);
            @current_player = c_player;
            @players[@players.index(@current_player)] = c_player
        end
        return combat
      end
      
      def discard_visible_treasures(treasures)
        treasures.each() do |t|
          @current_player.discard_visible_treasure(t)
          @dealer.give_treasures_back(t)
        end
      end
      
      def discard_hidden_treasures(treasures)
        treasures.each() do |t|
          @current_player.discard_hidden_treasure(t)
          @dealer.give_treasures_back(t)
        end
      end
      
      def make_treasures_visible(treasures)
        treasures.each() do |t|
          @current_player.make_treasure_visible(t)
        end
      end
      
      def buy_levels(visible, hidden)
        return @current_player.buy_levels(visible, hidden)
      end
      
      def init_game(players)
        init_players(players)
        @dealer.init_cards()
        next_turn()
      end
            
      def next_turn()
        state = next_turn_allowed()
        if state
          @current_monster = @dealer.next_monster()
          @current_player = next_player()    
          if @current_player.is_dead()
            @current_player.init_treasures
          end
          else @current_monster = @dealer.next_monster()
        end
        return state
      end
      
      def end_of_game(result)
        if result == combat_result::WinAndWinGame
          return true
        else
          return false
        end
      end     
 
  end
end

