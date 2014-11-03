/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte2;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraManos.Mano;
import analizadormanos.parte1.ParserParte1;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vik
 */
public class MainParte2 {

    public static void main(String[] args) {
        File entrada = null;
        Scanner sc = null;
        
        FileWriter salida = null;
        PrintWriter pw = null;
        
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Mano m = null;
        
        try{
            
            entrada = new File(args[1]);
            sc = new Scanner(entrada);
            
            salida = new FileWriter(args[2]);
            pw = new PrintWriter(salida);
            
            while(sc.hasNext()){

                cartas = ParserParte2.parse(sc);
                m = new Mano(cartas);
                m.calculaJugada();
                pw.println(m.verbose);
            }
        }catch(IOException e){
            
            System.out.println(e.getMessage());
        
        } finally {
            try{
                if(salida != null) salida.close();
                
            } catch (IOException e2){}
        }
    }
    
}
