/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraCartas.enums.Numeros;
import analizadormanos.estructuraCartas.enums.Palos;

/**
 *
 * @author vjacynycz
 */
public class AnalizadorManos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        for(int i = 1; i<=13;i++){
            for(int j = 1; j <=4 ; j++){
                
                System.out.println((new Carta(i,j)).toString());
            }
        }
        Carta as = new Carta(Numeros.as,Palos.corazones); // as de corazones
        Carta rey = new Carta(Numeros.rey,Palos.corazones); //rey de corazones
        System.out.println(as.esMejorCarta(rey));
        System.out.println((new Carta("Ad")).toString());
    }
    
}
