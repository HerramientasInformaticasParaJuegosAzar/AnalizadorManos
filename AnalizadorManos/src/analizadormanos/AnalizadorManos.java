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
import java.util.ArrayList;

/**
 *
 * @author vjacynycz
 */
public class AnalizadorManos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Carta> cartas = new ArrayList<>();
        cartas.add(new Carta("8h"));
        cartas.add(new Carta("8s"));
        cartas.add(new Carta("8c"));
        cartas.add(new Carta("Kc"));
        cartas.add(new Carta("Th"));
        cartas.add(new Carta("Jh"));
        cartas.add(new Carta("Qh"));
        
        Mano m = new Mano(cartas);
        m.calculaJugada();
        System.out.println(m.verbose);
        m.listanumeros();
        m.listaPalos();
        /*
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
                */
    }
    
}
