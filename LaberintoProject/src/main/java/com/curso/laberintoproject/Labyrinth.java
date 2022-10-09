/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.curso.laberintoproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;

/**
 *
 * @author jramir14
 */
public class Labyrinth {

    ArrayList<ArrayList<Character>> labyrinth;

    Labyrinth() {
        this.labyrinth = new ArrayList<ArrayList<Character>>();
    }

    void loadMap(String path) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                ArrayList<Character> aux = new ArrayList<Character>();
                for (char c : linea.toCharArray()) {
                    aux.add(c);
                }

                this.labyrinth.add(aux);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    void showLabyrinth() {
        for (int i = 0; i < this.labyrinth.size(); i++) {
            for (int j = 0; j < this.labyrinth.get(i).size(); j++) {
                System.out.print(this.labyrinth.get(i).get(j));
            }
            System.out.println("");
        }
    }

    @Override
    public String toString() {
        return "Labyrinth{" + "labyrinth=" + labyrinth + '}';
    }

    boolean checkRight(Position lastPosition, Position currentPosition) {
        int nextColumn = currentPosition.column + 1;
        return nextColumn < this.labyrinth.get(0).size()
                && nextColumn != lastPosition.column
                && !this.labyrinth.get(currentPosition.row).get(nextColumn).equals('#');
    }

    boolean checkLeft(Position lastPosition, Position currentPosition) {
        int previousColumn = currentPosition.column - 1;
        return previousColumn >= 0 && lastPosition.column != previousColumn
                && !this.labyrinth.get(currentPosition.row).get(previousColumn).equals('#');
    }

    boolean checkUp(Position lastPosition, Position currentPosition) {
        int previousRow = currentPosition.row - 1;
        return previousRow >= 0 && lastPosition.row != previousRow
                && !this.labyrinth.get(previousRow).get(currentPosition.column).equals('#');
    }

    boolean checkDown(Position lastPosition, Position currentPosition) {
        int nextRow = currentPosition.row + 1;
        return nextRow < this.labyrinth.size() && lastPosition.row != nextRow
                && !this.labyrinth.get(nextRow).get(currentPosition.column).equals('#');
    }

    Deque<Position> findExit(Deque<Position> way, Position currentPosition) {

        if (this.labyrinth.get(currentPosition.row).get(currentPosition.column) == 'S') {
            System.out.println("LLEGUEE");
            return way;
        } else {
            showLabyrinth();
            System.out.println("-------------");
            Position nextStep = new Position();

            if (way.isEmpty()) {
                currentPosition.setStartPosition(this.labyrinth);
                way.addFirst(currentPosition);
                way =  findExit(way, currentPosition);

            }

            if (checkRight(way.getFirst(), currentPosition)) {
                nextStep.column = currentPosition.column + 1;
                nextStep.row = currentPosition.row;
                this.labyrinth.get(currentPosition.row).set(currentPosition.column, '.');
                if (!way.contains(currentPosition)) {
                    way.addFirst(new Position(currentPosition.row, currentPosition.column));
                }

                way =  findExit(way, nextStep);
            }

            if (checkUp(way.getFirst(), currentPosition)) {
                nextStep.column = currentPosition.column;
                nextStep.row = currentPosition.row - 1;
                this.labyrinth.get(currentPosition.row).set(currentPosition.column, '.');
                if (!way.contains(currentPosition)) {
                    way.addFirst(new Position(currentPosition.row, currentPosition.column));
                }

                way =  findExit(way, nextStep);
            }

            if (checkLeft(way.getFirst(), currentPosition)) {
                nextStep.column = currentPosition.column - 1;
                nextStep.row = currentPosition.row;
                this.labyrinth.get(currentPosition.row).set(currentPosition.column, '.');
                if (!way.contains(currentPosition)) {
                    way.addFirst(new Position(currentPosition.row, currentPosition.column));
                }

                way =  findExit(way, nextStep);
            }

            if (checkDown(way.getFirst(), currentPosition)) {
                nextStep.column = currentPosition.column;
                nextStep.row = currentPosition.row + 1;
                if (!way.contains(currentPosition)) {
                    way.addFirst(new Position(currentPosition.row, currentPosition.column));
                }
                this.labyrinth.get(currentPosition.row).set(currentPosition.column, '.');
                way = findExit(way, nextStep);
            }

        }
        

        return way;
    }

}
