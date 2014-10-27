/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.estructuraManos;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraCartas.enums.Numeros;
import analizadormanos.estructuraManos.jugadas.Jugadas;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author vjacynycz
 */
public class Mano {
    
    private Carta[] cartas = new Carta[5];
    
    private int[] arrayNums = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0};
    
    private int[] arrayPalos = new int[]{0,0,0,0};
    
    private Jugadas jugada;
    
    private Numeros[] cartasJugada = new Numeros[2];
    
    private ArrayList<Numeros> kickers = new ArrayList<Numeros>();

    public Mano(Carta[] cartas){
        if(cartas.length==5){
            this.cartas=cartas;
            for(int i = 0; i<cartas.length;i++){
                arrayNums[cartas[i].getNumero().ordinal()] ++;
                arrayPalos[cartas[i].getPalo().ordinal()] ++;
            }
        }
        else this.cartas=null;
    }
    
    public String toString(){
        String s="La mano es:\n";
        for (Carta carta : cartas) {
            s += carta.toString() + "\n";
        }
        s+=Arrays.toString(this.arrayNums)+"\n";
        s+=Arrays.toString(this.arrayPalos);
        return s;
    }
    
    public boolean esPareja(){
        for(int i = arrayNums.length-1;i>=0;i--){
            if(arrayNums[i] >= 2){
                this.jugada=Jugadas.pareja;
                this.cartasJugada[0]=Numeros.values()[i];
                this.getKickers();
                 System.out.println("pareja de " +this.cartasJugada[0]+
                           " con kickers "+ this.kickers.toString());
                return true;
            }
        }
        return false;
    }

    private void getKickers() {
        for(int i = arrayNums.length-1;i>=0;i--){
            if(arrayNums[i] == 1){
                this.kickers.add(Numeros.values()[i]);
            }
        }
    
    }
}
