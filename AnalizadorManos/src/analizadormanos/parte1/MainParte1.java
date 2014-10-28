/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.parte1;

import java.io.File;

/**
 *
 * @author Vik
 * La parte 1 es la de analizar 5 cartas
 */
public class MainParte1 {


    public static void main(String[] args) {
    
        File entrada = new File(args[1]);
        ParserParte1.parse(entrada);
        File salida = new File(args[2]);
    }
    
}
