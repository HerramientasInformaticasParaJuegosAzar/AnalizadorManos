/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte2;

import analizadormanos.estructuraCartas.Carta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vik
 */
public class ParserParte2 {
    
    public static ArrayList<Carta> parse(Scanner sc) 
            throws IOException{
        
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        String entrada = sc.next();
        
        if(entrada.length() != 13 && entrada.length() != 15 && entrada.length() != 17){
            
            throw new IOException("Numero de caracteres de entrada incorrecto");
        }
        
        
        
        
        
    }
}
