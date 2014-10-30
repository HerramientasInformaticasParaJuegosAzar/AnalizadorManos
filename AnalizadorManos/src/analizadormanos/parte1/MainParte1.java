/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte1;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraManos.Mano;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vik
 * La parte 1 es la de analizar 5 cartas
 */
public class MainParte1 {


    public static void main(String[] args){
    
        
        File entrada = new File(args[1]);
        File salida = new File(args[2]);
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        
        
        try{
            Scanner sc = new Scanner(entrada);

            while(sc.hasNext()){

                cartas = ParserParte1.parse(sc);
                Mano m = new Mano(cartas);
                m.calculaJugada();
                System.out.println(m.verbose);
                
            }
        }catch(IOException e){
            
            System.out.println(e.getMessage());
        }
    }
    
}
