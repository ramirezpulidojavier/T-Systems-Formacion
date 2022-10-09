/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.curso.laberintoproject;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * @author jramir14
 */
public class LaberintoProject {

    public static void main(String[] args) {
        Labyrinth labyrinth = new Labyrinth();
        labyrinth.loadMap("C:\\Users\\jramir14\\Documents\\NetBeansProjects\\LaberintoProject\\laberinto0");
        labyrinth.findExit(new ArrayDeque<>(), new Position());
    }
}
