/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lector;


import analizadormanos.estructuraCartas.Carta;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


    /**
     * 
     * @author darnao
     */
    public class Lector1 {

        @SuppressWarnings("unchecked")
        public static ArrayList<Carta[]> leer(String archivo) 
                throws IOException{
            
            ArrayList lista = new ArrayList<>();
            Scanner sc = new Scanner(new File(archivo));
            
            
            while(sc.hasNext()){
                
                Carta[] carts = new Carta[5];
                String cartas = sc.next();
                
                //Excepcion si no hay 10 caracteres en cada linea
                if(cartas.length() != 10){         
                    throw new IOException("Numero de caracteres por linea incorrecto");
                }
                
                for(int i = 0; i < 5; i++){
                    
                    carts[i] = new Carta(cartas.substring(i * 2, i * 2 + 2));
                    
                    //Excepcion si no los caracteres no coinciden con los numeros y palos validos respectivamente
                    if(carts[i].getNumero() == null || carts[i].getPalo() == null){     
                        throw new IOException("Caracteres invalidos");
                    }
                    
                    for(int j = 0;j < i;j++){
                        
                        //Excepcion si hay dos cartas iguales
                        if(carts[i].equals(carts[j])){ 
                            throw new IOException("Cartas iguales en una misma mano");
                        }
                    }
                }
                
                
                lista.add(carts);
            }    
            return lista;
        }      
}

