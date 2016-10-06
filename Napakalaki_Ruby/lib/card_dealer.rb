# encoding: utf-8
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


require "singleton"
require_relative 'cultist'

module Napakalaki
  
  class CardDealer
    include Singleton
    
    attr_accessor :used_monsters, :unused_monsters, :used_treasures, :unused_treasures, :unused_cultists
       
    private
    
    def init_treasure_card_deck()
      
        @used_treasures = Array.new
        @unused_treasures = Array.new        
        @unused_treasures << Treasure.new("¡Sí mi amo!", 0 , 4, 7, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("Botas de investigación", 600 , 3, 4, TreasureKind::Shoe)
        @unused_treasures << Treasure.new("Capucha de Cthulhu", 500 , 3, 5, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("A prueba de babas", 400 , 2, 5, TreasureKind::Armor)
        @unused_treasures << Treasure.new("Botas de lluvia ácida", 800 , 0, 1, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Casco minero", 400 , 2, 4, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("Ametralladora Thompson", 600 , 4, 8, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Camiseta de la UGR", 100 , 1, 7, TreasureKind::Armor)
        @unused_treasures << Treasure.new("Clavo de rail ferroviario", 400 , 3, 6, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Cuchillo de sushi arcano", 300 , 2, 3, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Fez Alópodo", 700 , 3, 5, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("Hacha prehistórica", 500 , 2, 5, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("El aparato del pr. Tesla", 900 , 4, 8, TreasureKind::Armor)
        @unused_treasures << Treasure.new("Gaita", 500 , 4, 5, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Insecticida", 300 , 2, 3, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Escopeta de 3 cañones", 700 , 4, 6, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Garabato místico", 300 , 2, 2, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("La Fuerza de Mr. T", 1000 , 0 , 0, TreasureKind::Necklace)
        @unused_treasures << Treasure.new("La rebeca metálica", 400 , 2, 3, TreasureKind::Armor)
        @unused_treasures << Treasure.new("Mazo de los antiguos", 200 , 3, 4, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Necroplayboycón", 300 , 3, 5, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Lanzallamas", 800 , 4, 8, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Necrocomicón", 100 , 1, 1, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Necronomicón", 800 , 5, 7, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Linterna a 2 manos", 400 , 3, 6, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Necrognomicón", 200 , 2, 4, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Necrotelecom", 300 , 2, 3, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("Porra preternatural", 200 , 2, 3, TreasureKind::OneHand)
        @unused_treasures << Treasure.new("Tentaculo de pega", 200 , 0, 1, TreasureKind::Helmet)
        @unused_treasures << Treasure.new("Zapato deja-amigos", 500 , 0, 1, TreasureKind::Shoe)
        @unused_treasures << Treasure.new("Shogulador", 600 , 1, 1, TreasureKind::BothHand)
        @unused_treasures << Treasure.new("Varita de atizamiento", 400 , 3, 4, TreasureKind::OneHand)
     
    end
    
    def init_monster_card_deck()
      
        @used_monsters = Array.new
        @unused_monsters = Array.new
        bad_consequence1 = BadConsequence.new_level("pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0)
        prize1 = Prize.new(4,2)
        @unused_monsters << Monster.new("El rey de rosa", 13, bad_consequence1, prize1, 0)
        bad_consequence2 = BadConsequence.new_treasure("Pierdes tu armadura visible y otra oculta", 0, TreasureKind::Armor, TreasureKind::Armor)
        prize2 = Prize.new(2,1)
        @unused_monsters << Monster.new("3 Byakhees de bonanza", 8, bad_consequence2, prize2, 0)
        bad_consequence3 = BadConsequence.new_treasure("Embobados con el lindo primigenio te descartas de tu casco visible", 0, TreasureKind::Helmet, [])
        prize3 = Prize.new(1,1)
        @unused_monsters << Monster.new("Chibithulhu", 2, bad_consequence3, prize3, 0)
        bad_consequence4 = BadConsequence.new_treasure("El primordial bostezo contagioso. Pierdes el calzado visible", 0, TreasureKind::Shoe,[])
        prize4 = Prize.new(1,1)
        @unused_monsters << Monster.new("El sopor de dunwich", 2, bad_consequence4, prize4, 0)
        bad_consequence5 = BadConsequence.new_treasure("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo", 0, TreasureKind::OneHand, TreasureKind::OneHand)
        prize5 = Prize.new(4,1)
        @unused_monsters << Monster.new("Angeles de la noche ibicenca",14, bad_consequence5, prize5, 0)
        bad_consequence6 = BadConsequence.new_level("Pierdes todos tus tesoros visibles", 0, 10, nil)
        prize6 = Prize.new(3,1)
        @unused_monsters << Monster.new("El gorron en el umbral",10, bad_consequence6, prize6, 0)
        bad_consequence7 = BadConsequence.new_treasure("Pierdes la armadura visible", 0, TreasureKind::Armor,[])
        prize7 = Prize.new(2,1)
        @unused_monsters << Monster.new("H.P. Munchcraft",6, bad_consequence7, prize7, 0)
        bad_consequence8 = BadConsequence.new_treasure("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, TreasureKind::Armor, [])
        prize8 = Prize.new(1,1)
        @unused_monsters << Monster.new("Bichgooth",2, bad_consequence8, prize8, 0)
        bad_consequence9 = BadConsequence.new_level("Toses los pulmones y pierdes dos niveles", 2, 0, 0)
        prize9 = Prize.new(1,1)
        @unused_monsters << Monster.new("La que redacta en las tinieblas",2, bad_consequence9, prize9, 0)
        bad_consequence10 = BadConsequence.new_death("Estos monstruos resultan bastante superficiales y te aburren mortalmente. estas muerto", true)
        prize10 = Prize.new(2,1)
        @unused_monsters << Monster.new("Los hondos",8, bad_consequence10, prize10, 0)
        bad_consequence11 = BadConsequence.new_level("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2)
        prize11 = Prize.new(2,1)
        @unused_monsters << Monster.new("Semillas Cthulhu",4, bad_consequence11, prize11, 0)
        bad_consequence12 = BadConsequence.new_treasure("te intentas escaquear pierdes una mano visible", 0, TreasureKind::OneHand, [])
        prize12 = Prize.new(2,1)
        @unused_monsters << Monster.new("Dameargo",1, bad_consequence12, prize12, 0)
        bad_consequence13 = BadConsequence.new_level("Da mucho asquito pierdes 3 niveles", 3, 0, 0)
        prize13 = Prize.new(1,1)
        @unused_monsters << Monster.new("Pollipolipo volante",3, bad_consequence13, prize13, 0)
        bad_consequence14 = BadConsequence.new_death("No le hace gracia que pronuncien mal su nombre. Estas muerto", true)
        prize14 = Prize.new(3,1)
        @unused_monsters << Monster.new("Yskhtihyssg-Goth",12, bad_consequence14, prize14, 0)
        bad_consequence15 = BadConsequence.new_death("La familia te atrapa. estas muerto", true)
        prize15 = Prize.new(4,1)
        @unused_monsters << Monster.new("Familia feliz",1, bad_consequence15, prize15, 0)
        bad_consequence16 = BadConsequence.new_treasure("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, TreasureKind::BothHand, [])
        prize16 = Prize.new(2,1)
        @unused_monsters << Monster.new("Roboggoth", 8, bad_consequence16, prize16, 0)
        bad_consequence17 = BadConsequence.new_treasure("te asusta en la noche pierdes un casco visible", 0, TreasureKind::Helmet, [])
        prize17 = Prize.new(1,1)
        @unused_monsters << Monster.new("El espia", 5, bad_consequence17, prize17, 0)
        bad_consequence18 = BadConsequence.new_level("Menudo susto te llevas pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0)
        prize18 = Prize.new(1,1)
        @unused_monsters << Monster.new("El Lenguas", 20, bad_consequence18, prize18, 0)
        bad_consequence19 = BadConsequence.new_treasure("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3, TreasureKind::BothHand, [])
        prize19 = Prize.new(1,1)
        @unused_monsters << Monster.new("Bicefalo", 20, bad_consequence19, prize19, 0)        
        bad_consequence20 = BadConsequence.new_treasure("Pierdes 1 mano visible", 3, TreasureKind::OneHand, [])
        prize20 = Prize.new(3,1)
        @unused_monsters << Monster.new("El mal indecible impronunciable", 10, bad_consequence20, prize20, -2)      
        bad_consequence21 = BadConsequence.new_level("Pierdes tus tesoros visibles. Jajaja.",  0, 10, 0)
        prize21 = Prize.new(2,1)
        @unused_monsters << Monster.new("Testigos Oculares", 6, bad_consequence21, prize21, 2)        
        bad_consequence22 = BadConsequence.new_death("Hoy no es tu dia de suerte. Mueres.", true)
        prize22 = Prize.new(2,5)
        @unused_monsters << Monster.new("El gran cthulhu", 20, bad_consequence22, prize22, 4)        
        bad_consequence23 = BadConsequence.new_level("Tu gobierno te recorta 2 niveles.", 2, 0, 0)
        prize23 = Prize.new(2,1)
        @unused_monsters << Monster.new("Serpiente Politico", 8, bad_consequence23, prize23, -2)
        bad_consequence24 =  BadConsequence.new_treasure("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas.", 3, [TreasureKind::Armor, TreasureKind::Helmet], TreasureKind::OneHand)
        prize24 = Prize.new(1,1)
        @unused_monsters << Monster.new("Felpuggoth", 2, bad_consequence24, prize24, 5)
        bad_consequence25 =  BadConsequence.new_level("Pierdes 2 niveles.", 2, 0, 0)
        prize25 = Prize.new(4,2);
        @unused_monsters << Monster.new("Shoggoth", 16, bad_consequence25, prize25, -4)        
        bad_consequence26 = BadConsequence.new_level("Pintalabios negro. Pierdes 2 niveles.", 2, 0, 0)
        prize26 = Prize.new(1,1);
        @unused_monsters << Monster.new("Lolitagooth", 2, bad_consequence26, prize26,3)
    end
    
    def shuffle_treasures()
        @unused_treasures.shuffle!
    end
    
    def shuffle_monsters()
        @unused_monsters.shuffle!
    end
    
    public
    
    def next_treasure()
      if @unused_treasures.size() == 0
        @used_treasures.each() do |t|
          @unused_treasures << t
        end
      shuffle_treasures()
      @used_treasures.clear()
      end
      t = @unused_treasures[0]
      @used_treasures << t
      @unused_treasures.delete(t)
      return t
    end
    
    def next_monster()
        if @unused_monsters.size() == 0
        @used_monsters.each() do |m|
          @unused_monsters << m
        end
      shuffle_monsters()
      @used_monsters.clear()
      end
      m = @unused_monsters[0]
      @used_monsters << m
      @unused_monsters.delete(m)
      return m
    end
    
    def give_treasure_back(t)
        @used_treasures << t
    end
    
    def give_monster_back(m)
        @used_monsters << m         
    end
    
    def init_cards()
      init_treasure_card_deck()
      shuffle_treasures()
      init_monster_card_deck()
      shuffle_monsters()
      init_cultist_card_deck()
      shuffle_cultists()
    end    
    
    def init_cultist_card_deck()
        @unused_cultists = Array.new
        for i in 1..4
            @unused_cultists << Cultist.new("+1 por cada sectario en juego. \nNo puedes dejar de ser sectario", 1)
        end
        @unused_cultists << Cultist.new("+2 por cada sectario en juego.\n No puedes dejar de ser sectario", 2)
        @unused_cultists << Cultist.new("+2 por cada sectario en juego.\n No puedes dejar de ser sectario", 2)        
    end
    
    def shuffle_cultists()
        @unused_cultists.shuffle!
    end
    
    def next_cultist()
        return @unused_cultists[CultistPlayer.total_cultist_players]
    end
  end
end
