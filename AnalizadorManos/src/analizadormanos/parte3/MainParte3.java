/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte3;

import analizadormanos.Juego.Juego;
import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraManos.Mano;
import analizadormanos.jugador.Jugador;
import analizadormanos.mesa.Mesa;
import analizadormanos.parte2.ParserParte2;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Krnx
 */
public class MainParte3 {

    private static String stringEntrada="";
    
    public static void main(String args[]) {
        File entrada = null;
        Scanner sc = null;

        FileWriter salida = null;
        PrintWriter pw = null;

        ArrayList<Jugador> listaJugadores = null;
        
        try {

            entrada = new File(args[1]);
            sc = new Scanner(entrada);

            salida = new FileWriter(args[2]);
            pw = new PrintWriter(salida);

            while (sc.hasNext()) {
                stringEntrada= sc.next();
                listaJugadores = ParserParte3.parse(stringEntrada);
                Juego juego = new Juego(listaJugadores);
                juego.ordenarJugadores();
                pw.println(stringEntrada);
                for(Jugador j : juego.getJugadores()){
                    pw.print(j.toString().trim()+"\n");
                }
                pw.println();
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {
            try {
                if (salida != null) {
                    salida.close();
                }

            } catch (IOException e2) {
            }
        }

        System.out.print("acabado");
    }
}
