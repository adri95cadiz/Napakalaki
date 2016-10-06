# encoding: utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

#última

require_relative "napakalaki"
require_relative "command"
require_relative "combat_result"
require "singleton"

module Napakalaki

class GameTester

  include Singleton
 
  def play(aGame, numberOfPlayers)
    
    @game = aGame
    players = getPlayerNames(numberOfPlayers)
    @game.init_game(players)
    
    begin #Mientras dure la partida
      begin #Mientras el jugador se decide a conocer al monstruo
        puts "******* ******* ******* ******* ******* ******* *******"
        puts "\n\n Turno de: " + @game.current_player().to_s() 
        command = getCommandBeforeKnowingMonster()
        command = processCommand(command)        
      end while (command != Command::EXIT && command != Command::SHOWMONSTER)
      if (command == Command::SHOWMONSTER) then
        begin #Mientras el jugador se decida a combatir 
          puts "******* ******* ******* ******* ******* ******* *******"
          puts "\n\n Turno de: " + @game.current_player().to_s()
          command = getCommandBeforeFighting()
          command = processCommand(command)
        end while (command != Command::EXIT && command != Command::COMBAT)
        if (command == Command::COMBAT) then
          combat_result = @game.develop_combat()
          case combat_result
          when CombatResult::WinAndWinGame then 
              puts "\n\n       " + @game.current_player().name + "\n\n HAS GANADO LA PARTIDA"
              #break está implícito            
            when CombatResult::Win then
              puts "\n\n Ganaste el combate"
            when CombatResult::Lose then
              puts "\n\n Has perdido el combate, te toca cumplir con el siguiente mal rollo"
              puts @game.current_player().pending_bad_consequence().to_s
            when CombatResult::LoseAndEscape then
              puts "\n\n Perdiste el combate pero has logrado escapar"
            when CombatResult::LoseAndDie then
              puts "\n\n Perdiste el combate y ademas estas muerto"
            when CombatResult::LoseAndConvert then
              puts "\n\n Perdiste el combate y te convertiste en sectario"
          end #case
          if (combat_result != CombatResult::WinAndWinGame) then
            begin #Hasta que se avance de turno 
              puts "******* ******* ******* ******* ******* ******* *******"
              puts "\n\n Turno de: " + @game.current_player().to_s()
              command = getCommandAfterFighting()
              command = processCommand(command)
            end while (command != Command::EXIT && command != Command::NEXTTURNALLOWED)
          else 
            command = Command::EXIT
          end #if Command::WINANDWINGAME  
        end #if COMBAT
    end  #if SHOWMONSTER
    end while (command != Command::EXIT) #mientras dure la partida

  end
  
  private
  
  def getCommandAfterFighting()
      commands = [Command::SHOWMONSTER, Command::SHOWVISIBLETREASURE, Command::SHOWHIDDENTREASURE, 
      Command::DISCARDVISIBLETREASURE, Command::DISCARDHIDDENTREASURE, Command::MAKETREASUREVISIBLE, 
      Command::NEXTTURN, Command::EXIT]
      manageMenu("Opciones antes de pasar turno", commands)
  end
  
  def getCommandBeforeFighting ()
      commands = [Command::SHOWMONSTER, Command::SHOWVISIBLETREASURE, Command::SHOWHIDDENTREASURE, 
      Command::COMBAT, Command::EXIT]
      manageMenu("Opciones antes de combatir", commands)
  end
  
  def getCommandBeforeKnowingMonster () 
      commands = [Command::SHOWMONSTER,Command::SHOWVISIBLETREASURE, Command::SHOWHIDDENTREASURE, 
      Command::MAKETREASUREVISIBLE,  Command::BUYLEVELS, Command::EXIT]      
      manageMenu("Opciones antes de conocer al monstruo", commands)
  end
  
  def getPlayerNames (numberOfPlayers) 
    names = Array.new
    for i in 1..numberOfPlayers
      puts "Escribe el nombre del jugador #{i}: "
      names << gets.chomp
    end
    names;
  end

  def getTreasure (howMany) 
    
    begin #Hasta que la entrada sea válida
      valid_input = true
      option = -1
      puts "\n Elige un tesoro: "
      capture = gets.chomp
      
      begin #tratar la excepción
        option = Integer(capture)
      rescue Exception => e  
        valid_input = false
      end
      
      if valid_input then
        if (option < -1 || option > howMany) then #no se ha escrito un entero en el rango permitido
          valid_input = false
        end
      end  
      if (! valid_input) then
        inputErrorMessage() 
      end
    end while (! valid_input)
    option
  end
  
  def inputErrorMessage () 
    puts "\n\n ERROR !!! \n\n Selección errónea. Inténtalo de nuevo.\n\n"
  end
  
  def manageBuyLevels (aPlayer)
    commands = [Command::BUYWITHVISIBLES, Command::BUYWITHHIDDEN, Command::BUYLEVELS]
    
    visible_treasures_to_buy_levels = Array.new (aPlayer.visible_treasures())
    hidden_treasures_to_buy_levels = Array.new (aPlayer.hidden_treasures())
    visible_shopping_cart = Array.new
    hidden_shopping_cart = Array.new

    begin #Hasta que se llene el carrito de la compra y se decida comprar niveles
      command = manageMenu("Opciones para comprar niveles", commands)
      case command 
        when Command::BUYWITHVISIBLES then
          manageTreasuresToBuyLevels(visible_shopping_cart, visible_treasures_to_buy_levels)
        when Command::BUYWITHHIDDEN then
          manageTreasuresToBuyLevels(hidden_shopping_cart, hidden_treasures_to_buy_levels)
      end 
    end while (command != Command::BUYLEVELS)
    aPlayer.buy_levels(visible_shopping_cart, hidden_shopping_cart)
  end
  
  def manageDiscardTreasures (visible, aPlayer)
     
    begin #Se descartan tesoros hasta que se vuelve al menú anterior
      if visible then
        how_many = showTreasures("Elige tesoros visibles para descartar", aPlayer.visible_treasures(), true)
      else 
        how_many = showTreasures("Elige tesoros ocultos para descartar", aPlayer.hidden_treasures(), true)
      end
      option = getTreasure (how_many)
      if (option > -1) then 
        if visible then
          aPlayer.discard_visible_treasure (aPlayer.visible_treasures()[option])
        else
          aPlayer.discard_hidden_treasure (aPlayer.hidden_treasures()[option])          
        end
      end
    end while (option != -1)  
  end
  
  def manageMakeTreasureVisible (aPlayer)
       
    begin #Se hacen tesoros visibles hasta que se vuelve al menÃº anterior
      how_many = showTreasures("Elige tesoros para intentar hacerlos visibles", aPlayer.hidden_treasures(), true)
      option = getTreasure (how_many);
      if (option > -1) then
        aPlayer.make_treasure_visible (aPlayer.hidden_treasures()[option])
      end
    end while (option != -1)
  end
  
  def manageMenu (message, menu) 
    menu_check = Hash.new #Para comprobar que se hace una selecciÃ³n vÃ¡lida
    menu.each do |c|
      menu_check [Command.getMenu(c)] = c
    end    
    begin #Hasta que se hace una selecciÃ³n vÃ¡lida
      valid_input = true
      option = -1
      puts ("\n\n------- ------ ------ ------ ------ ------ ------")
      puts "**** " + message + " ****\n"
      menu.each do |c| #se muestran las opciones del menÃº
        puts "#{Command.getMenu(c)}" + " : " + Command.getText(c)
      end
      puts "\n Elige una opción: "
      capture = gets.chomp
      begin
        option = Integer(capture)
      rescue Exception => e  #No se ha introducido un entero
        valid_input = false
      end  
      
      if valid_input then
        if (! menu_check.has_key?(option)) #No es un entero entre los vÃ¡lidos
          valid_input = false
        end
      end
      if (! valid_input) then
        inputErrorMessage()
      end
    end while (! valid_input)
    menu_check[option]    
  end
  
  def manageTreasuresToBuyLevels (shoppingCart, treasuresToBuyLevels) 
       
    begin #Mientras se aÃ±adan tesoros al carrito de la compra
      how_many = showTreasures("Elige tesoros para comprar niveles", treasuresToBuyLevels, true)
      option = getTreasure (how_many)
      if (option > -1) then
        shoppingCart << treasuresToBuyLevels[option]
        treasuresToBuyLevels.delete_at(option)
      end
    end while (option != -1)
  end
  
  def processCommand (command) 
    current_player = @game.current_player()
    case command 
      when Command::EXIT then 
        puts "exit"
        puts "pulsa enter para seguir"
        gets
      when Command::COMBAT then
        puts "combat"
        puts "pulsa enter para seguir"
        gets
      when  Command::SHOWMONSTER then 
        puts "\n------- ------- ------- ------- ------- ------- ------- "
        puts "El monstruo actual es:\n\n" + @game.current_monster().to_s()
        puts "pulsa enter para seguir"
        gets
      when Command::SHOWVISIBLETREASURE then
        showTreasures("Esta es tu lista de tesoros visibles", current_player.visible_treasures(), false)
        puts "pulsa enter para seguir"
        gets
      when Command::SHOWHIDDENTREASURE then
        showTreasures("Esta es tu lista de tesoros ocultos", current_player.hidden_treasures(), false)
        puts "pulsa enter para seguir"
        gets
      when Command::MAKETREASUREVISIBLE then
        manageMakeTreasureVisible (current_player)
        puts "pulsa enter para seguir"
        gets
      when Command::DISCARDVISIBLETREASURE then
        manageDiscardTreasures(true, current_player)
        puts "pulsa enter para seguir"
        gets
      when Command::DISCARDHIDDENTREASURE then
        manageDiscardTreasures(false, current_player)
        puts "pulsa enter para seguir"
        gets
      when Command::BUYLEVELS then
        manageBuyLevels (current_player)
        puts "pulsa enter para seguir"
        gets
      when Command::NEXTTURN then
        if (!@game.next_turn()) then
          puts "\n\n ERROR \n"  
          puts "No cumples las condiciones para pasar de turno."
          puts "O bien tienes más de 4 tesoros ocultos"
          puts "O bien te queda mal rollo por cumplir"          
          puts "tu mal rollo es:"
          puts current_player.pending_bad_consequence().to_s()
          puts "pulsa enter para seguir"
          gets
        else 
          command = Command::NEXTTURNALLOWED
          puts "pulsa enter para seguir"
          gets
        end
       else puts "ERROR" 
    end
    command
  end
  
  def showTreasures (message, treasures, menu)
    option_menu = -1

    puts "\n------- ------- ------- ------- ------- ------- -------"
    puts message 
    if menu then
      puts "\n" + Command.getMenu(Command::GOBACK).to_s() + " : " + Command.getText(Command::GOBACK)
    end
    treasures.each do |t|
      option_menu = option_menu + 1
      if (menu)
         puts "\n" + option_menu.to_s() + ":" + t.to_s()
      else 
         puts "\n" + t.to_s()
      end
     
    end
    option_menu
  end
 
 end #clase
end #modulo
