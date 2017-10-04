/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author everardo_martinez
 */
public class Algorithms {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Algorithms a = new Algorithms();
//        System.out.println("resultado: " + a.fibonacci(9));
        System.out.println("resultado: " + a.primo(10));
    }
    
    int fibonacci(int n) {
        return fibonacci(n, new HashMap());
    }
    
    int fibonacci(int n, Map<Integer, Integer> map) {
        if(n > 1) {
            if(map.get(n) != null) {
                return map.get(n);
            } else {
                map.put(n, fibonacci(n - 1, map) + fibonacci(n - 2, map));  //función recursiva
                return map.get(n);
            }
        }
        else if(n == 1) {  // caso base
            return 1;
        }
        else if(n == 0){  // caso base
            return 0;
        }
        else{ //error
            System.out.println("Debes ingresar un tamaño mayor o igual a 1");
            return -1; 
        }
    }
    
    int primo(int n) {
        if(n < 1) {
            System.out.println("Invalid number");
            return -1;
        }
        int primeNumber = 0;
        int countPrimes = 0;
        int i = 1;
        while(true) {
            int counter=0; 		  
            for(int num = i; num >= 1; num--) {
                if(i % num == 0) {
                    counter = counter + 1;
                }
            }
            if(counter == 2) {
                countPrimes++;
            }	
            if(countPrimes == n) {
                primeNumber = i;
                break;
            }
            i++;
         }	
        return primeNumber;
    }
    
    boolean isPrimeNumber(int number, int n, Set<Integer> primos) {
        if(primos.contains(number)) {
            return true;
        } else {
            if(number % n == 0 && number != 2) {
                return false;
            } else if(n > number / 2) {
                return true;
            } else {
                return isPrimeNumber(number, n+1, primos);
            }
        }
    }
    
}
