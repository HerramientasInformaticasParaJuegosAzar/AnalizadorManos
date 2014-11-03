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
        if(args.length!=3){
            System.out.println("Utilización: \n java –jar AnalizadorManos.jar <numejercicio> entrada.txt salida.txt");
            return;
        }
        if(args[0].equalsIgnoreCase("1")){
            MainParte1.main(args);
        } else if(args[0].equalsIgnoreCase("2")){
            MainParte2.main(args);
        }
             
    }
    
}
