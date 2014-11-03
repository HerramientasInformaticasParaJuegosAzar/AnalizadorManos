/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte3;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.jugador.Jugador;
import analizadormanos.mesa.Mesa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class ParserParte3 {
    
    
    public static ArrayList<Jugador> parse(String entrada)
        throws IOException{
        
        Carta cartas[] = new Carta[2];
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        ArrayList<Carta> mesa = new ArrayList<Carta>();
        Mesa board = null;
        String[] entradaA = entrada.split(";");
        
        int j = Integer.parseInt(entradaA[0]);
        for(int i = 0; i < 5; i++){
            
            mesa.add(new Carta(entradaA[j+1].substring(i * 2, i * 2 + 2)));
        } 
        board = new Mesa(mesa);
        
        for(int i = 1; i <= j; i++){
            
            cartas[0] = new Carta(entradaA[i].substring(2, 4));
            cartas[1] = new Carta(entradaA[i].substring(4, 6));
            
            jugadores.add(new Jugador(i, cartas, board));
        }
        return jugadores;
    }
}
