/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte2;

import analizadormanos.estructuraCartas.Carta;
import java.io.File;
import java.io.FileNotFoundException;
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
          
        String[] strA = entrada.split(";");
        
        if(strA.length != 3){
            
            throw new IOException("Formato de archivo incorrecto: Separacion con punto y coma");
        }
        

        if(strA[0].length() != 4){
            throw new IOException("Formato de archivo incorrecto: No hay dos cartas en la mano");
        }   
        if(strA[1].length() != 1){
            throw new IOException("Formato de archivo incorrecto: No hay un numero valido de caracteres entre los punto y coma");
        }
        
        int t;
        try{
            t = Integer.parseInt(strA[1]);
        }catch(NumberFormatException e){
            
            throw new IOException("Formato de archivo incorrecto: No hay un entero entre los punto y coma");
        }
        
        if(t < 3 || t > 5){
            throw new IOException("Formato de archivo incorrecto: Numero entre punto y coma no esta entre 3 y 5");
        }
        if(strA[2].length() != 2 * t){
            throw new IOException("Formato de archivo incorrecto: Numero de cartas no coincide con el numero entre punto y coma");
        }
            
        String crt = strA[0] + strA[2];
        
        for(int i = 0; i < t + 2; i++){
            
            cartas.add(new Carta(crt.substring(i * 2, i * 2 + 2)));

            //Excepcion si los caracteres no coinciden con los numeros y palos validos respectivamente
            if(cartas.get(i).getNumero() == null || cartas.get(i).getPalo() == null){     
                throw new IOException("Caracteres invalidos");
            }

            //Comprobacion si hay 2 cartas iguales
            for(int j = 0;j < i;j++){

                if(cartas.get(i).equals(cartas.get(j))){ 
                    throw new IOException("Cartas iguales en una misma mano");
                }
            }
            
            System.out.println(cartas.get(i).toString());
        }
              
        return cartas;   
    }
}
