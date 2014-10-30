/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package analizadormanos.mesa;

import analizadormanos.estructuraCartas.Carta;
import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public class Mesa 
{
    ArrayList<Carta> board;
    
    public Mesa(ArrayList<Carta> board)
    {
        this.board = board;
    }

    public ArrayList<Carta> getBoard() 
    {
        return board;
    }

    public void setBoard(ArrayList<Carta> board) 
    {
        this.board = board;
    }
    
    
}
