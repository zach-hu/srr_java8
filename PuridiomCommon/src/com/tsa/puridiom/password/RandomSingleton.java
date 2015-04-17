/*
 * @(#)RandomSingleton.java  2002-07-26
 *
 * Version 1.04
 *
 * Copyright (C) 26 juillet 2002 hughes monget
 * hmonget@users.sourceforge.net
 * http://jpassgen.sourceforge.net/
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.tsa.puridiom.password;

import com.tsa.puridiom.password.exceptions.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * This class is a singleton: it shares the same random object
 * between "Random" classes.
 *
 * @author hughes monget
 */
class RandomSingleton {

    //////////////////
    // CLASS FIELDS //
    //////////////////

    /**
     * The algorithm name of the random object.
     */
    public static final String ALGO_RANDOM = "SHA1PRNG";

    /**
     * The random object.
     */
    private static Random random = null;

    /**
     * The singleton instance.
     */
    private static RandomSingleton singleton = null;

    ///////////////////
    // CLASS MEMBERS //
    ///////////////////

    /**
     * We initialize the random algorithm (PRNG).
     */
    private RandomSingleton() {

        boolean secure = false;

        try {
            RandomSingleton.random = SecureRandom.getInstance(ALGO_RANDOM);
            // Force seed initialization
            int nexInt = RandomSingleton.random.nextInt();
            secure = true;
        } catch (NoSuchAlgorithmException e) {
            //throw new
            //        PassGenRuntimeException("SHA1:NoSuchAlgorithmException");
            System.err.println("** SHA:NoSuchAlgorithmException:"
                    + e.getMessage());
        } catch (Exception e) {
            //throw new PassGenRuntimeException("SHA1:Exception");
            System.err.println("** SHA:Exception:" + e.getMessage());
        }

        if (!secure) {
            throw new JPassGenRuntimeException("** Insecure algorithm");
        }
    }

    /**
     * This method is used by the "tri par fusion" shuffle method:
     * we split alternatively 1 array into 2 new arrays.<br>
     * "On définit une fonction qui répartit les éléments
     * alternativement sur deux tableaux"
     *
     * @param t The array we want to split.
     * @param t1 The first result array.
     * @param t2 The second result array.
     * @param n The length of the array we want to separate.
     *
     */
    private static void splitArray(Object t[], Object t1[], Object t2[],
            int n) {

       for (int i = 0 ; i < n; ++i) {
          t1[i / 2] = t[i];
          ++i;
          if (i == n) {
              break;
          }
          t2[i / 2] = t[i];
       }
    }

    /**
     * This method is used by the "tri par fusion" shuffle method:
     * we merge 2 arrays.<br>
     * "Puis une fonction de fusion de deux tableaux ordonnés:"
     *
     * @param t1 The first array.
     * @param t2 The second array.
     * @param n1 The length of the first array.
     * @param n2 The length of the second array.
     * @param t The result array.
     */
    private static void mergeArrays(Object t1[], Object t2[], int n1, int n2,
            Object t[]) {

       int i1, i2, i;
       i1 = 0;
       i2 = 0;
       i  = 0;
       for (; i1 < n1 && i2 < n2 ; ++i) {
          //if(t1[i1]<t2[i2]){
          // Here is the random object.
          if (RandomSingleton.getInstance().nextBoolean()) {
             t[i] = t1[i1];
             ++i1;
          } else {
             t[i] = t2[i2];
             ++i2;
          }
       }
       for (; i1 < n1 ; ++i, ++i1) {
           t[i] = t1[i1];
       }
       for (; i2 < n2 ; ++i, ++i2) {
           t[i] = t2[i2];
       }
    }

    /**
     * We shuffle an array of objects by a "tri par fusion" method.
     *
     *  @param t The array to shuffle.
     */
    public static void shuffleFusion(Object t[]) {

        shuffleFusion(t, t.length);
    }

    /**
     * We shuffle an array of objects by a "tri par fusion" method.<br>
     * "Le tri par fusion s'écrit récursivement :"
     * (See <a href="http://brassens.upmf-grenoble.fr/IMSS/limass/limass.html"
     *      target="_blank">le cours algo<a>)
     *
     *  @param t The array to shuffle.
     *  @param n The length of the array.
     */
    private static void shuffleFusion(Object t[], int n) {

       if (n <= 1) {
           return;
       } else {
          Object t1[], t2[];
          int n1, n2;
          n1 = (n + 1) / 2;
          n2 = n / 2;
          t1 = new Object [n1];
          t2 = new Object [n2];
          splitArray(t, t1, t2, n);
          shuffleFusion(t1, n1);
          shuffleFusion(t2, n2);
          mergeArrays(t1, t2, n1, n2, t);
          t1 = null;
          t2 = null;
       }
    }

    /**
     * We shuffle an array of objects. <br>
     * Type "shuffle cards cryptography faq" in Google.
     *
     *  @param arr The array to shuffle.
     */
    public static void shufflePermut(Object arr[]) {

        int length = arr.length;

        if (length <= 1) {
            return;
        } else {
            length--;
            for (int ii = length ; ii > 0 ; ii--) {
                int iiPermut = Math.abs(RandomSingleton.getInstance().nextInt())
                        % (ii + 1);
                if (iiPermut != ii) {
                    Object swap   = arr[ii];
                    arr[ii]       = arr[iiPermut];
                    arr[iiPermut] = swap;
                }
            }
        }
    }

    //////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
     * Default constructor : if it is not created, we initialize the
     * random object.
     * @return the singleton random object.
     */
    public static synchronized RandomSingleton getInstance() {

        if (RandomSingleton.singleton == null) {
            RandomSingleton.singleton = new RandomSingleton();
        }
        return RandomSingleton.singleton;
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    /**
     * Get Next Int.
     * @return the next int.
     */
    synchronized int nextInt() {

        return RandomSingleton.random.nextInt();
    }

    /**
     * Get Next Boolean.
     * @return the next boolean.
     */
    synchronized boolean nextBoolean() {

        return RandomSingleton.random.nextBoolean();
    }

} // End of RandomSingleton.java

