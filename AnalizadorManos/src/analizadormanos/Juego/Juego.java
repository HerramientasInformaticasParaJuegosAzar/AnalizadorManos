/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadormanos.Juego;

import analizadormanos.jugador.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Krnx
 */
public class Juego 
{
    private ArrayList<Jugador> listaJugadores = new ArrayList<>();
    
    public Juego()
    {
        
    }
    
    public Juego(ArrayList<Jugador> listaJugadores)
    {
        this.listaJugadores = listaJugadores;
            
    }
    
    public void ordenarJugadores()
    {
        quickSort(this.listaJugadores,0,this.listaJugadores.size() - 1);
    }
    
    
    
    int partition(ArrayList<Jugador> arr, int left, int right)
    {
      int i = left, j = right;
      Jugador tmp;
      Jugador pivot = arr.get((left + right) / 2);
     
      while (i <= j) 
      {                     // Mientras a < b
            while (arr.get(i).compararJugada(pivot.getMano()) < 0) // si a < b --> numero negativo
                  i++;
            while (arr.get(j).compararJugada(pivot.getMano()) > 0)
                  j--;
            if (i <= j) {
                  tmp = arr.get(i);
                  arr.set(i, arr.get(j));
                  arr.set(j, tmp);
                  i++;
                  j--;
            }
      };
     
      return i;
    }
 
    // Fuente: http://www.algolist.net/Algorithms/Sorting/Quicksort
    
    void quickSort(ArrayList<Jugador> arr, int left, int right) 
    {
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
    }
}
