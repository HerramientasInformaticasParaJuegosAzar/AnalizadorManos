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

    private int[] arrayNums = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int[] arrayPalos = new int[]{0, 0, 0, 0};

    private Jugadas jugada;

    private Numeros[] cartasJugada = new Numeros[2];

    private ArrayList<Numeros> kickers = new ArrayList<Numeros>();

    public String verbose = "No se ha calculado la jugada";

    public Mano(Carta[] cartas) {
        if (cartas.length == 5) {
            this.cartas = cartas;
            for (int i = 0; i < cartas.length; i++) {
                arrayNums[cartas[i].getNumero().ordinal()]++;
                arrayPalos[cartas[i].getPalo().ordinal()]++;
            }
        } else {
            this.cartas = null;
        }
    }

    public String toString() {
        String s = "La mano es:\n";
        for (Carta carta : cartas) {
            s += carta.toString() + "\n";
        }
        s += Arrays.toString(this.arrayNums) + "\n";
        s += Arrays.toString(this.arrayPalos);
        return s;
    }

    public boolean calculaJugada() {
        if (this.esEscaleraDeColor()) {
            return true;
        } else if (this.esPoker()) {
            return true;
        } else if (this.esFullhouse()) {
            return true;
        } else if (this.esColor()) {
            return true;
        } else if (this.esEscalera()) {
            return true;
        } else if (this.esTrio()) {
            return true;
        } else if (this.esDoblePareja()) {
            return true;
        } else if (this.esPareja()) {
            return true;
        } else {
            this.jugada = Jugadas.highCard;
            getKickers();
            this.verbose = "Carta más alta con" + this.kickers.toString();
            return true;
        }

    }

    public boolean esEscaleraDeColor() {
        boolean hayColor = false;
        for (int i : arrayPalos) {
            if (i == 5) {
                hayColor = true;
            }
        }
        if(!hayColor) return false;
        else{
            if(esEscalera()){
                this.jugada = Jugadas.escaleraDeColor;
                this.verbose=this.verbose.replace("Escalera", "Escalera de Color");
                return true;
            }
        }
        return false;
    }

    public boolean esPoker() {
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if (arrayNums[i] >= 4) {
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

    public boolean esFullhouse() {
        boolean pareja = false, trio = false;
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if ((arrayNums[i] == 2) && (!pareja)) {
                pareja = true;
                this.cartasJugada[1] = Numeros.values()[i];
            }
            if ((arrayNums[i] == 3) && (!pareja)) {
                trio = true;
                this.cartasJugada[0] = Numeros.values()[i];
            }
            if (trio && pareja) {
                this.jugada = Jugadas.fullHouse;
                this.getKickers();
                this.verbose = "FullHouse de " + this.cartasJugada[0]
                        + " y " + this.cartasJugada[1];
                return true;
            }
        }
        return false;
    }

    public boolean esColor() {
        for (int i : arrayPalos) {
            if (i == 5) {
                this.jugada = Jugadas.color;
                this.getKickers();
                this.verbose = "Color con kickers " + this.kickers.toString();
                return true;
            }
        }
        return false;
    }

    public boolean esEscalera() {
        int[] lowEscalera = {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        if (Arrays.equals(lowEscalera, this.arrayNums)) {
            this.jugada = Jugadas.escalera;
            this.cartasJugada[0] = Numeros.cinco;
            this.verbose = "Escalera de as a 5";
            return true;
        }
        int numLinked = 0;
        Numeros kicker = null;
        for (int i = arrayNums.length - 1; i >= 1; i--) {
            if (arrayNums[i] > 1) {
                return false;
            } else if ((arrayNums[i] == 1)) {
                if (arrayNums[i - 1] == 1) {
                    if (numLinked == 3) {
                        this.jugada = Jugadas.escalera;
                        this.cartasJugada[0] = kicker;
                        this.cartasJugada[1] = Numeros.values()[i - 1];
                        this.verbose = "Escalera de " + this.cartasJugada[1]
                                + " a " + this.cartasJugada[0];
                        return true;
                    } else if (numLinked == 0) {
                        kicker = Numeros.values()[i];
                        numLinked++;
                    } else {
                        numLinked++;
                    }
                } else {
                    return false;

                }
            }
        }
        return false;
    }

    public boolean esTrio() {
        for (int i = arrayNums.length - 1; i >= 0; i--) {
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
                this.jugada = Jugadas.pareja;
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

    private void getKickers() {
        for (int i = arrayNums.length - 1; i >= 0; i--) {
            if (arrayNums[i] == 1) {
                this.kickers.add(Numeros.values()[i]);
            }
        }

    }
}
