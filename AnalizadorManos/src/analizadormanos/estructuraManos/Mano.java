/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.estructuraManos;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraCartas.enums.Numeros;
import analizadormanos.estructuraManos.jugadas.Jugadas;
import static analizadormanos.estructuraManos.jugadas.Jugadas.poker;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author vjacynycz
 */
public class Mano 
{

    private ArrayList<Carta> cartas = new ArrayList<>();

    private int[] arrayNums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    
    private int[] arrayPalos = new int[]{0, 0, 0, 0};

    private Jugadas jugada;

    private Numeros[] cartasJugada = new Numeros[2];

    private ArrayList<Numeros> kickers = new ArrayList<Numeros>();

    public String verbose = "No se ha calculado la jugada";

    public ArrayList<Jugadas> draws = new ArrayList<Jugadas>();
    

    public Mano(ArrayList<Carta> cartas) 
    {
        this.checkMano();
        if (cartas.size() > 2) // Me tienen que dar por lo menos 2 cartas. 
        {
            rellenarArrayCartas(cartas);
            }
         else {
            this.cartas = null;
        }
    }
    
    public void rellenarArrayCartas(ArrayList<Carta> cartas)
    {
            this.cartas = cartas;
            arrayNums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            arrayPalos = new int[]{0, 0, 0, 0};
            for (int i = 0; i < cartas.size(); i++) 
            {
                arrayNums[cartas.get(i).getNumero().ordinal()]++;
                arrayPalos[cartas.get(i).getPalo().ordinal()]++;
            }
    }

    public String toString() 
    {
        String s = "La mano es:\n";
        for (Carta carta : cartas) {
            s += carta.toString() + "\n";
        }
        s += Arrays.toString(this.arrayNums) + "\n";
        s += Arrays.toString(this.arrayPalos);
        return s;
    }

    public void calculaJugada() 
    {
        if (this.esEscaleraDeColor()) {
      
        } else if (this.esPoker()) {
           
        } else if (this.esFullhouse()) {
        
        } else if (this.esColor(true)) {
          
        } else if (this.esEscalera()) {
          
        } else if (this.esTrio()) {
            
        } else if (this.esDoblePareja()) {
           
        } else if (this.esPareja()) {
            
        } else {
            this.jugada = Jugadas.highCard;
            getKickers();
            this.verbose = "Carta más alta con" + this.kickers.toString();
        }

    }
    // Modificado.
    public boolean esEscaleraDeColor() 
    {
        boolean esEscaleraDeColor = false;
        
        if (esColor(false) && esEscalera()) 
        {
                this.jugada = Jugadas.escaleraDeColor;
                this.verbose = this.verbose.replace("Escalera", "Escalera de Color");
                esEscaleraDeColor = true;
        } 
     
        return esEscaleraDeColor;
    }
    // Adaptado
    public boolean esPoker() 
    {
        for (int i = arrayNums.length - 1; i >= 0; i--) 
        {
            if (arrayNums[i] >= 4) 
            {
                this.jugada = Jugadas.poker;
                this.cartasJugada[0] = Numeros.values()[i];
                this.getKickers();
                this.verbose = "poker de " + this.cartasJugada[0]
                        + " con kickers " + this.kickers.toString();
                return true;
            }
        }
        return false;
    }
    // Modificado
    public boolean esFullhouse() 
    {
        boolean pareja = false, trio = false;
        for (int i = arrayNums.length - 1; i >= 0; i--)
        {
            if ((arrayNums[i] == 3) && (!trio))
            {
                trio = true;
                this.cartasJugada[0] = Numeros.values()[i];
            }
            
            else if ((arrayNums[i] >= 2) && (!pareja)) 
            {
                pareja = true;
                this.cartasJugada[1] = Numeros.values()[i];
            }
            
            if (trio && pareja) 
            {
                this.jugada = Jugadas.fullHouse;
                this.verbose = "FullHouse de " + this.cartasJugada[0]
                        + " y " + this.cartasJugada[1];
                return true;
            }
        }
        return false;
    }
    //Falta comprobar para que las 5 sean del mismo pal para los kickers
    
    public boolean esColor(boolean comprobado) 
    {
        boolean hayColor = false;
        
         for (int i =0; i < arrayPalos.length; i++) 
        {
            if (arrayPalos[i] >= 5) {
                this.jugada = Jugadas.color;
                this.verbose = "Color de " + this.cartasJugada[0]
                        + " y " + this.cartasJugada[1];
                hayColor = true;
            }
            else if (i == 4 && comprobado) {
                this.draws.add(Jugadas.color);
            }
        }
        return hayColor;
    }

       // Modifcado para preflop + board
    public boolean esEscalera() 
    {
        int[] lowEscalera = {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        
        if (Arrays.equals(lowEscalera, this.arrayNums)) 
        {
            this.jugada = Jugadas.escalera;
            this.cartasJugada[0] = Numeros.cinco;
            this.verbose = "Escalera de as a 5";
            return true;
        }
        int numLinked = 0;
        Numeros kicker = null;
        for (int i = arrayNums.length - 1; i >= 1; i--) 
        {
             if ((arrayNums[i] >= 1) && arrayNums[i] < 4) 
             {
                if (arrayNums[i - 1] >= 1 && arrayNums[i-1] < 4) 
                {
                    if (numLinked == 3) 
                    {
                        this.jugada = Jugadas.escalera;
                        this.cartasJugada[0] = kicker;
                        this.cartasJugada[1] = Numeros.values()[i - 1];
                        this.verbose = "Escalera de " + this.cartasJugada[1]
                                + " a " + this.cartasJugada[0];
                        return true;
                    } 
                    else if (numLinked == 0) 
                    {
                        kicker = Numeros.values()[i];
                        numLinked++;
                    } 
                    else 
                    {
                        numLinked++;
                    }
                } 
                else 
                {
                    return false;

                }
            }
        }
        return false;
    }

    public boolean esTrio() 
    {
        for (int i = arrayNums.length - 1; i >= 0; i--) 
        {
            if (arrayNums[i] >= 3) {
                this.jugada = Jugadas.trio;
                this.cartasJugada[0] = Numeros.values()[i];
                this.getKickers();
                this.verbose = "trio de " + this.cartasJugada[0]
                        + " con kickers " + this.kickers.toString();
                return true;
            }
        }
        return false;
    }

    public boolean esDoblePareja() {
        boolean pareja = false;
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if ((arrayNums[i] >= 2) && (!pareja)) {
                pareja = true;
                this.cartasJugada[0] = Numeros.values()[i];
            } else if ((arrayNums[i] >= 2) && (pareja)) {
                this.jugada = Jugadas.doblePareja;
                this.cartasJugada[1] = Numeros.values()[i];
                this.getKickers();
                this.verbose = "dobles parejas de " + this.cartasJugada[0]
                        + " y " + this.cartasJugada[1]
                        + " con kickers " + this.kickers.toString();
                return true;
            }
        }
        return false;
    }

    public boolean esPareja() {
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if (arrayNums[i] >= 2) {
                this.jugada = Jugadas.pareja;
                this.cartasJugada[0] = Numeros.values()[i];
                this.getKickers();
                this.verbose = "pareja de " + this.cartasJugada[0]
                        + " con kickers " + this.kickers.toString();
                return true;
            }
        }
        return false;
    }

    private void getKickers() 
    {
        int contador = 0;
         for (int i = arrayNums.length - 1; i >= 0; i--) 
         {
             switch(this.jugada)
             {
                 case poker:
                     if (arrayNums[i] >= 1 && arrayNums[i] < 4 && contador < 1){
                        this.kickers.add(Numeros.values()[i]);
                        contador++;
                     }
                 break;
                     
                 case doblePareja:
                     if (arrayNums[i] >= 1 && this.cartasJugada[1].ordinal() < Numeros.values()[i].ordinal() && contador < 1){
                         this.kickers.add(Numeros.values()[i]);
                          contador++;
                     }
                    break;
                 
                 case trio:
                     if (arrayNums[i] == 1 && contador < 2){
                        this.kickers.add(Numeros.values()[i]); 
                        contador++;
                     }
                     break;
                 case pareja:
                     if (arrayNums[i] == 1 && contador < 3){
                        this.kickers.add(Numeros.values()[i]); 
                        contador++;
                     }
                     break;
                     
                 default:
                     if (arrayNums[i] == 1)
                        this.kickers.add(Numeros.values()[i]); 
                     break;
                     
                 
             }
         }
         }
        /*
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if (arrayNums[i] >= 1) 
            {
                this.kickers.add(Numeros.values()[i]);
            }
        }
        */


    private void checkMano() {

    }

    public int esMejorMano(Mano m) {
        /*primero comparamos la jugada*/
        int comparacion = this.jugada.compareTo(m.jugada);
        
        /*si son iguales, pasamos a comprobar la carta de la jugada
          Ej: en pareja de ases, el As está almacenado en cartasJugada[0]
              en dobles parejas de As, Dieces el As esta en cartasJugada[0] 
                                           y el Diez en cartasJugada[1]
        */
        
        /*Ene el caso de que sean color, pasa directamente a los kickers*/
        if (comparacion == 0) {
            comparacion = this.cartasJugada[0].compareTo(m.cartasJugada[0]);
            if (comparacion == 0) {
                /*Si es trio o poker cartasJugada[1] = null así que pasamos a los kickers*/
                if (this.cartasJugada[1]==null){
                    comparacion = 0;
                }
    
                else comparacion = this.cartasJugada[1].compareTo(m.cartasJugada[1]);
                
                if (comparacion == 0) {
                    int i = 0;
                    /*comprobamos los kickers de mayor a menor*/
                    while(i<this.kickers.size()){
                        comparacion = this.kickers.get(i).compareTo(
                            m.kickers.get(i)
                        );
                        /*si alguno de los kickers no son iguales, gana el mayor*/
                        if(comparacion != 0 ) return comparacion;
                    }
                }
            }
        }

        return comparacion;
    }
    
    public int[] getArrayNums() {
        return arrayNums;
    }

    public int[] getArrayPalos() {
        return arrayPalos;
    }
    
    public void listanumeros(){
        System.out.print("[");
        for(int i = 0; i < arrayNums.length;i++){
            System.out.print(arrayNums[i]+",");
        }
        System.out.print("]");
    }
    
    public void listaPalos(){
        System.out.print("[");
        for(int i = 0; i < arrayPalos.length;i++){
            System.out.print(arrayPalos[i]+",");
        }
        System.out.print("]");
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Jugadas getJugada() {
        return jugada;
    }

    public Numeros[] getCartasJugada() {
        return cartasJugada;
    }

    public ArrayList<Numeros> getKickersList() {
        return kickers;
    }

    public String getVerbose() {
        return verbose;
    }

    public ArrayList<Jugadas> getDraws() {
        return draws;
    }
    
    
}
