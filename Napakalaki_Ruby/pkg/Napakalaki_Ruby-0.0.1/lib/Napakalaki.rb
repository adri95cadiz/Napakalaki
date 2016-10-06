# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
  require_relative 'BadConsequence.rb'
  require_relative 'Prize.rb'
  require_relative 'Monster.rb'
  require_relative 'TreasureKind.rb'
  
module Napakalaki      
      if __FILE__== $0
      
      i=0
      n=19
      muerto = true
      malRollo=BadConsequence.newDeath("Te mueres",muerto)
      buenRollo=Prize.new(2,4)      
        
        monstruo=Monster.new("Cthulthu",25,malRollo,buenRollo)
      
        puts(monstruo.to_s)        
            
        monstruos = Array.new
        #rey de rosa
        badConsequence1 = BadConsequence.newLevel("pierdes 5 niveles y 3 tesoros visibles", 5, 3, 0)
        prize1 = Prize.new(4,2)
        monstruos << Monster.new("El rey de rosa", 13, badConsequence1, prize1)
        #3 yakhees de bonanza
        badConsequence2 = BadConsequence.newTreasure("Pierdes tu armadura visible y otra oculta", 0, TreasureKind::Armor, TreasureKind::Armor)
        prize2 = Prize.new(2,1)
        monstruos << Monster.new("3 Byakhees de bonanza", 8, badConsequence2, prize2)
        #chibithulhu
        badConsequence3 = BadConsequence.newTreasure("Embobados con el lindo primigenio te descartas de tu casco visible", 0, TreasureKind::Helmet, [])
        prize3 = Prize.new(1,1)
        monstruos << Monster.new("Chibithulhu", 2, badConsequence3, prize3)
        #El sopor de dunwich
        badConsequence4 = BadConsequence.newTreasure("El primordial bostezo contagioso. Pierdes el calzado visible", 0, TreasureKind::Shoe,[])
        prize4 = Prize.new(1,1)
        monstruos << Monster.new("El sopor de dunwich", 2, badConsequence4, prize4)
        #Angeles de la noche ibicenca
        badConsequence5 = BadConsequence.newTreasure("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo", 0, TreasureKind::OneHand, TreasureKind::OneHand)
        prize5 = Prize.new(4,1)
        monstruos << Monster.new("Angeles de la noche ibicenca",14, badConsequence5, prize5)
        #El gorron en el umbral
        badConsequence6 = BadConsequence.newLevel("Pierdes todos tus tesoros visibles", 0, 10, nil)
        prize6 = Prize.new(3,1)
        monstruos << Monster.new("El gorron en el umbral",10, badConsequence6, prize6)
        #H.P. Munchcraft
        badConsequence7 = BadConsequence.newTreasure("Pierdes la armadura visible", 0, TreasureKind::Armor,[])
        prize7 = Prize.new(2,1)
        monstruos << Monster.new("H.P. Munchcraft",6, badConsequence7, prize7)
        #Bichgooth
        badConsequence8 = BadConsequence.newTreasure("Sientes bichos bajo la ropa. Descarta la armadura visible", 0, TreasureKind::Armor, [])
        prize8 = Prize.new(1,1)
        monstruos << Monster.new("Bichgooth",2, badConsequence8, prize8)
        #La que redacta en las tiniebras
        badConsequence9 = BadConsequence.newLevel("Toses los pulmones y pierdes dos niveles", 2, 0, 0)
        prize9 = Prize.new(1,1)
        monstruos << Monster.new("La que redacta en las tinieblas",2, badConsequence9, prize9)
        #Los hondos
        badConsequence10 = BadConsequence.newDeath("Estos monstruos resultan bastante superficiales y te aburren mortalmente. estas muerto",muerto)
        prize10 = Prize.new(2,1)
        monstruos << Monster.new("Los hondos",8, badConsequence10, prize10)
        #Semillas Cthulhu
        badConsequence11 = BadConsequence.newLevel("Pierdes 2 niveles y 2 tesoros ocultos", 2, 0, 2)
        prize11 = Prize.new(2,1)
        monstruos << Monster.new("Semillas Cthulhu",4, badConsequence11, prize11)
        #Dameargo
        badConsequence12 = BadConsequence.newTreasure("te intentas escaquear pierdes una mano visible", 0, TreasureKind::OneHand, [])
        prize12 = Prize.new(2,1)
        monstruos << Monster.new("Dameargo",1, badConsequence12, prize12)
        #Pollipolipo volante
        badConsequence13 = BadConsequence.newLevel("Da mucho asquito pierdes 3 niveles", 3, 0, 0)
        prize13 = Prize.new(1,1)
        monstruos << Monster.new("Pollipolipo volante",3, badConsequence13, prize13)
        #Yskhtihyssg-Goth
        badConsequence14 = BadConsequence.newDeath("No le hace gracia que pronuncien mal su nombre. Estas muerto", muerto)
        prize14 = Prize.new(3,1)
        monstruos << Monster.new("Yskhtihyssg-Goth",12, badConsequence14, prize14)
        #Familia feliz
        badConsequence15 = BadConsequence.newDeath("La familia te atrapa. estas muerto", muerto)
        prize15 = Prize.new(4,1)
        monstruos << Monster.new("Familia feliz",1, badConsequence15, prize15)
        #Roboggoth
        badConsequence16 = BadConsequence.newTreasure("La quinta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visible", 2, TreasureKind::BothHand, [])
        prize16 = Prize.new(2,1)
        monstruos << Monster.new("Roboggoth", 8, badConsequence16, prize16)
        #El espia
        badConsequence17 = BadConsequence.newTreasure("te asusta en la noche pierdes un casco visible", 0, TreasureKind::Helmet, [])
        prize17 = Prize.new(1,1)
        monstruos << Monster.new("El espia", 5, badConsequence17, prize17)
        #El lenguas
        badConsequence18 = BadConsequence.newLevel("Menudo susto te llevas pierdes 2 niveles y 5 tesoros visibles", 2, 5, 0)
        prize18 = Prize.new(1,1)
        monstruos << Monster.new("El Lenguas", 20, badConsequence18, prize18)
        #Bicefalo
        badConsequence19 = BadConsequence.newTreasure("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos", 3, TreasureKind::BothHand, [])
        prize19 = Prize.new(1,1)
        monstruos << Monster.new("Bicefalo", 20, badConsequence19, prize19)
        
        
        while(i<n)
          puts(monstruos[i].to_s) 
          i = i+1
        end
    end
  end