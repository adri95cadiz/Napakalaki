# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

  require_relative 'bad_consequence.rb'
  require_relative 'prize.rb'
  require_relative 'monster.rb'
  require_relative 'treasure_kind.rb'
  
module Napakalaki      
           
      i=0
      n=19
      muerto = true
      mal_rollo=BadConsequence.newDeath("Te mueres",muerto)
      buen_rollo=Prize.new(2,4)      
        
        monstruo=Monster.new("Cthulthu",25,mal_rollo,buen_rollo)
      
        puts(monstruo.to_s)        
            
        monstruos = Array.new
        #rey de rosa
        bad_consequence1 = BadConsequence.new_level("pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0)
        prize1 = Prize.new(4,2)
        monstruos << Monster.new("El rey de rosa", 13, bad_consequence1, prize1)
        #3 yakhees de bonanza
        bad_consequence2 = BadConsequence.new_treasure("Pierdes tu armadura visible y otra oculta", 0, TreasureKind::Armor, TreasureKind::Armor)
        prize2 = Prize.new(2,1)
        monstruos << Monster.new("3 Byakhees de bonanza", 8, bad_consequence2, prize2)
        #chibithulhu
        bad_consequence3 = BadConsequence.new_treasure("Embobados con el lindo primigenio te descartas de tu casco visible", 0, TreasureKind::Helmet, [])
        prize3 = Prize.new(1,1)
        monstruos << Monster.new("Chibithulhu", 2, bad_consequence3, prize3)
        #El sopor de dunwich
        bad_consequence4 = BadConsequence.new_treasure("El primordial bostezo contagioso. Pierdes el calzado visible", 0, TreasureKind::Shoe,[])
        prize4 = Prize.new(1,1)
        monstruos << Monster.new("El sopor de dunwich", 2, bad_consequence4, prize4)
        #Angeles de la noche ibicenca
        bad_consequence5 = BadConsequence.new_treasure("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo", 0, TreasureKind::OneHand, TreasureKind::OneHand)
        prize5 = Prize.new(4,1)
        monstruos << Monster.new("Angeles de la noche ibicenca",14, bad_consequence5, prize5)
        #El gorron en el umbral
        bad_consequence6 = BadConsequence.new_level("Pierdes todos tus tesoros visibles", 0, 10, nil)
        prize6 = Prize.new(3,1)
        monstruos << Monster.new("El gorron en el umbral",10, bad_consequence6, prize6)
        #H.P. Munchcraft
        bad_consequence7 = BadConsequence.new_treasure("Pierdes la armadura visible", 0, TreasureKind::Armor,[])
        prize7 = Prize.new(2,1)
        monstruos << Monster.new("H.P. Munchcraft",6, bad_consequence7, prize7)
        #Bichgooth
        bad_consequence8 = BadConsequence.newTreasure("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, TreasureKind::Armor, [])
        prize8 = Prize.new(1,1)
        monstruos << Monster.new("Bichgooth",2, bad_consequence8, prize8)
        #La que redacta en las tiniebras
        bad_consequence9 = BadConsequence.new_level("Toses los pulmones y pierdes dos niveles", 2, 0, 0)
        prize9 = Prize.new(1,1)
        monstruos << Monster.new("La que redacta en las tinieblas",2, bad_consequence9, prize9)
        #Los hondos
        bad_consequence10 = BadConsequence.new_death("Estos monstruos resultan bastante superficiales y te aburren mortalmente. estas muerto",muerto)
        prize10 = Prize.new(2,1)
        monstruos << Monster.new("Los hondos",8, bad_consequence10, prize10)
        #Semillas Cthulhu
        bad_consequence11 = BadConsequence.new_level("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2)
        prize11 = Prize.new(2,1)
        monstruos << Monster.new("Semillas Cthulhu",4, bad_consequence11, prize11)
        #Dameargo
        bad_consequence12 = BadConsequence.new_treasure("te intentas escaquear pierdes una mano visible", 0, TreasureKind::OneHand, [])
        prize12 = Prize.new(2,1)
        monstruos << Monster.new("Dameargo",1, bad_consequence12, prize12)
        #Pollipolipo volante
        bad_consequence13 = BadConsequence.new_level("Da mucho asquito pierdes 3 niveles", 3, 0, 0)
        prize13 = Prize.new(1,1)
        monstruos << Monster.new("Pollipolipo volante",3, bad_consequence13, prize13)
        #Yskhtihyssg-Goth
        bad_consequence14 = BadConsequence.new_death("No le hace gracia que pronuncien mal su nombre. Estas muerto", muerto)
        prize14 = Prize.new(3,1)
        monstruos << Monster.new("Yskhtihyssg-Goth",12, bad_consequence14, prize14)
        #Familia feliz
        bad_consequence15 = BadConsequence.new_death("La familia te atrapa. estas muerto", muerto)
        prize15 = Prize.new(4,1)
        monstruos << Monster.new("Familia feliz",1, bad_consequence15, prize15)
        #Roboggoth
        bad_consequence16 = BadConsequence.new_treasure("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, TreasureKind::BothHand, [])
        prize16 = Prize.new(2,1)
        monstruos << Monster.new("Roboggoth", 8, bad_consequence16, prize16)
        #El espia
        bad_consequence17 = BadConsequence.new_treasure("te asusta en la noche pierdes un casco visible", 0, TreasureKind::Helmet, [])
        prize17 = Prize.new(1,1)
        monstruos << Monster.new("El espia", 5, bad_consequence17, prize17)
        #El lenguas
        bad_consequence18 = BadConsequence.new_level("Menudo susto te llevas pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0)
        prize18 = Prize.new(1,1)
        monstruos << Monster.new("El Lenguas", 20, bad_consequence18, prize18)
        #Bicefalo
        bad_consequence19 = BadConsequence.new_treasure("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3, TreasureKind::BothHand, [])
        prize19 = Prize.new(1,1)
        monstruos << Monster.new("Bicefalo", 20, bad_consequence19, prize19)
        
        
        while(i<n)
          puts(monstruos[i].to_s) 
          i = i+1
        end
              
        def nivel_combate(monstruos) 
        
            mons = Array.new
            
            for i in 0..monstruos.size()
            
                if(monstruos[i].CombatLevel() > 10 ) 
                    mons << monstruos[i]          
                
                end
            end
            return mons;
        end
        
        
        def pierde_nivel(monstruos) 
        
            mons = Array.new
            
            for i in 0..monstruos.size()
            
                if(monstruos[i].MalRollo.levels() > 0)
                  if((monstruos[i].malRollo.n_hidden_treasures() == 0) && (monstruos[i].malRollo().n_visible_treasures() == 0))
                    if((monstruos[i].malRollo.specific_hidden_treasures.first == []) && (monstruos[i].malRollo.specific_visible_treasures == []))
                      mons << monstruos[i]           
                
                end
            end
            return mons;
        end        
                  
        
        def gana_nivel(monstruos) 
        
            mons = Array.new
            
            for i in 0..monstruos.size()
            
                if(monstruos[i].malRollo.levels() > 1 ) 
                    mons << monstruos[i]         
                
                end
            end
            return mons;
        end        
               
        def pierde_tesoros(monstruos) 
        
            mons = Array.new
            
            for i in 0..monstruos.size()
            
                if((monstruos[i].malRollo.specific_hidden_treasures.first != []) && (monstruos[i].malRollo.specific_visible_treasures.first != []))
                      mons << monstruos[i]           
                
                end
            end
            return mons;
        end  
        
            end
        end
end
