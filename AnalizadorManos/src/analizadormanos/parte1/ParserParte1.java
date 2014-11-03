/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte1;

import analizadormanos.estructuraCartas.Carta;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Vik
 */
public class ParserParte1 {
    
    /*
    Dado un scanner, parsea la entrada y devuelve un arraylist con las cartas generadas.
    Si el formato del archivo no coincide con el descrito en el enunciado de la practica, lanza una excepcion
    */
    public static ArrayList<Carta> parse(Scanner sc) 
            throws IOException{

        ArrayList<Carta> cartas = new ArrayList<Carta>();
        String entrada = sc.next();

        //Excepcion si no hay 10 caracteres en la linea
        if(entrada.length() != 10){         
            throw new IOException("Numero de caracteres por linea incorrecto");
        }

        for(int i = 0; i < 5; i++){

            cartas.add(new Carta(entrada.substring(i * 2, i * 2 + 2)));

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
        }

        return cartas;
    }
}
