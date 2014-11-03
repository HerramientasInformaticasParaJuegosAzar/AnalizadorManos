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
    
    /*
    Dado un scanner lee en cada linea una jugada siguiendo el formato descrito en el enunciado de la practica.
    Si el archivo no cumple el formato adecuado, lanza una excepcion
    */
    public static ArrayList<Carta> parse(Scanner sc) 
            throws IOException{
        
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        String[] strA = null;
        String crt = null;
        String entrada = sc.next();
        
        //Excepcion si el numero de caracteres por linea no es el correcto
        if(entrada.length() != 13 && entrada.length() != 15 && entrada.length() != 17){
            
            throw new IOException("Numero de caracteres de entrada incorrecto");
        }
          
        strA = entrada.split(";");
        
        //Excepcion si no hay exactamente dos punto y coma por linea
        if(strA.length != 3){
            
            throw new IOException("Formato de archivo incorrecto: Separacion con punto y coma");
        }
        
        //Excepcion si antes del primer punto y coma no hay 4 caracteres
        if(strA[0].length() != 4){
            throw new IOException("Formato de archivo incorrecto: No hay dos cartas en la mano");
        }   
        //Excepcion si entre los dos puntos y coma no hay exactamente un caracter
        if(strA[1].length() != 1){
            throw new IOException("Formato de archivo incorrecto: No hay un numero valido de caracteres entre los punto y coma");
        }
        
        //Excepcion si el caracter que hay entre los dos puntos y coma no es un entero
        int t;
        try{
            t = Integer.parseInt(strA[1]);
        }catch(NumberFormatException e){
            
            throw new IOException("Formato de archivo incorrecto: No hay un entero entre los punto y coma");
        }
        //Excepcion si el entero que hay entre los dos puntos y coma no esta entre 3 y 5 (incluidos)
        if(t < 3 || t > 5){
            throw new IOException("Formato de archivo incorrecto: Numero entre punto y coma no esta entre 3 y 5");
        }
        //Excepcion si el numero de cartas despues del segundo punto y coma no coincide con el numero anterior
        if(strA[2].length() != 2 * t){
            throw new IOException("Formato de archivo incorrecto: Numero de cartas en la mesa no coincide con el numero entre punto y coma");
        }
        
        //Union de las 2 primeras cartas del jugador con las restantes cartas sobre la mesa (3, 4 ó 5)
        crt = strA[0] + strA[2];
        
        for(int i = 0; i < t + 2; i++){
            
            cartas.add(new Carta(crt.substring(i * 2, i * 2 + 2)));

            //Excepcion si los caracteres no coinciden con los numeros y palos validos respectivamente
            if(cartas.get(i).getNumero() == null || cartas.get(i).getPalo() == null){     
                throw new IOException("Caracteres invalidos");
            }

            //Excepcion si hay 2 cartas iguales
            for(int j = 0;j < i;j++){

                if(cartas.get(i).equals(cartas.get(j))){ 
                    throw new IOException("Cartas iguales en una misma mano");
                }
            }
        }
              
        return cartas;   
    }
}
