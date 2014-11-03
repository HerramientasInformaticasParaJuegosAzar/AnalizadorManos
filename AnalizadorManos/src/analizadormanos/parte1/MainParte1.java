/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte1;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraManos.Mano;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vik
 * La parte 1 es la de analizar 5 cartas
 */
public class MainParte1 {

    
    /*
    Algoritmo de escritura en archivo basado en:
    http://chuwiki.chuidiang.org/index.php?title=Lectura_y_Escritura_de_Ficheros_en_Java
    */
    public static void main(String[] args){
    
        
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

                cartas = ParserParte1.parse(sc);
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
