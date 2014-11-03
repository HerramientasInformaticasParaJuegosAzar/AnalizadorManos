/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadormanos.jugador;

import analizadormanos.estructuraCartas.Carta;
import analizadormanos.estructuraManos.Mano;
import analizadormanos.estructuraManos.jugadas.Jugadas;
import analizadormanos.mesa.Mesa;
import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public class Jugador 
{
    private int numeroJug;
    private Carta cartasPropias[] = new Carta[2];
    private Mesa mesa;
    private Mano mano;
    
    public Jugador(int numeroJug, Carta cartasPropias[], Mesa mesa)
    {
        this.cartasPropias = cartasPropias.clone();
        this.numeroJug = numeroJug;
        this.mesa = mesa;
        
        ArrayList<Carta> cartas = new ArrayList<>(mesa.getBoard());
        cartas.add(cartasPropias[0]);
        cartas.add(cartasPropias[1]);
        mano = new Mano(cartas);
        mano.calculaJugada();
        
    }
    
    // Si la mesa cambia se debe actualizar la mano.
    public void actualizarMano()
    {
        ArrayList<Carta> cartas = mesa.getBoard();
        cartas.add(cartasPropias[0]);
        cartas.add(cartasPropias[1]);
        mano.rellenarArrayCartas(cartas);
        mano.calculaJugada();
        
    }
    
    public int compararJugada(Mano manoB)
    {
        return this.mano.esMejorMano(manoB);
    }
    
    public Jugadas getMejorJugada()
    {
        return mano.getJugada();
    }
    
    public String getMejorJugadaString()
    {
        return mano.getVerbose();
    }

    public int getNumeroJug() 
    {
        return numeroJug;
    }

    public void setNumeroJug(int numeroJug) 
    {
        this.numeroJug = numeroJug;
    }

    public Carta[] getCartasPropias() {
        return cartasPropias;
    }

    public void setCartasPropias(Carta[] cartasPropias) 
    {
        this.cartasPropias = cartasPropias;
    }

    public Mesa getMesa() 
    {
        return mesa;
    }

    public void setMesa(Mesa mesa) 
    {
        this.mesa = mesa;
    }

    public Mano getMano() {
        return mano;
    }
    
    public String toString(){
        return "J"+this.numeroJug+":"+
                mano.verbose;
    }
    
    
    
}
