/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraCartas.enums.Numeros;
import analizadormanos.estructuraCartas.enums.Palos;
import analizadormanos.estructuraManos.Mano;
import analizadormanos.parte1.MainParte1;
import analizadormanos.parte2.MainParte2;

/**
 *
 * @author vjacynycz
 */
public class AnalizadorManos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Mano m = new Mano(new Carta[]{
            new Carta("6c"),new Carta("6h"),new Carta("6d"),
            new Carta("8h"),new Carta("9h")
        }); 
        
        Mano m2 = new Mano(new Carta[]{
            new Carta("6c"),new Carta("6h"),new Carta("6d"),
            new Carta("Kh"),new Carta("9c")
        }); 
        
        if(m.calculaJugada())
            System.out.println(m.verbose);
        if(m2.calculaJugada())
            System.out.println(m2.verbose);
         System.out.println(m.esMejorMano(m2));
        if(args.length!=3) return;
        if(args[0].equalsIgnoreCase("1")){
            MainParte1.main(args);
        } else  if(args[1].equalsIgnoreCase("1")){
            MainParte2.main(args);
        }
    }
    
}
