/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.laberintoproject;

import java.util.ArrayList;

/**
 *
 * @author jramir14
 */
public class Position {

    int row;
    int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    Position() {
        this.row = 0;
        this.column = 0;
    }

    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", column=" + column + '}';
    }
    
    

    void setStartPosition(ArrayList<ArrayList<Character>> labyrinth) {
        boolean found = false;
        for (int row = 0; row < labyrinth.size() && !found; row++) {
            for (int column = 0; column < labyrinth.get(row).size() && !found; column++){
                if(labyrinth.get(row).get(column) == 'E'){
                    this.row = row;
                    this.column = column;
                    found = true;
                }
            }
        }

    }

}
