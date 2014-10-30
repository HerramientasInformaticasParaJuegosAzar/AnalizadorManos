/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte3;

import analizadormanos.Juego.Juego;
import analizadormanos.estructuraCartas.Carta;
import analizadormanos.jugador.Jugador;
import analizadormanos.mesa.Mesa;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class MainParte3 
{
    public static void main(String args[])
    {
        ArrayList<Carta> board = new ArrayList<>();
        board.add(new Carta("Ah"));
        board.add(new Carta("6c"));
        board.add(new Carta("7s"));
        board.add(new Carta("8s"));
        board.add(new Carta("2d"));
        
        Mesa mesa = new Mesa(board);
        
        Carta cartasPropias[] = new Carta[2];
        cartasPropias[0] = new Carta("As");
        cartasPropias[1] = new Carta("Kh");
        
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        
        listaJugadores.add(new Jugador(1,cartasPropias,mesa));
        
        cartasPropias[0] = new Carta("4d");
        cartasPropias[1] = new Carta("5c");
        
        listaJugadores.add(new Jugador(2,cartasPropias,mesa));
        
        cartasPropias[0] = new Carta("5h");
        cartasPropias[1] = new Carta("Ac");
        
        listaJugadores.add(new Jugador(3,cartasPropias,mesa));
        
        Juego juego = new Juego(listaJugadores);
        juego.ordenarJugadores();
        
        System.out.print("acabado");
    }
}
