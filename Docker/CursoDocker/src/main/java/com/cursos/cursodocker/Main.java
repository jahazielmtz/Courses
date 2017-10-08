/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cursos.cursodocker;

import com.tailoredshapes.underbar.Dates;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jahaziel
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hola mundo");
        Dates date = new Dates() {};
        try {
            date.wait(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Termino la espera");
    }
}
