/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadormanos.jugador;

import analizadormanos.estructuraCartas.Carta;

/**
 *
 * @author usuario_local
 */
public class Jugador 
{
    private int numeroJug;
    private Carta cartasPropias[] = new Carta[2];
    
    public Jugador(int numeroJug, Carta cartasPropias[])
    {
        this.cartasPropias = cartasPropias;
        this.numeroJug = numeroJug;
    }
    
}
