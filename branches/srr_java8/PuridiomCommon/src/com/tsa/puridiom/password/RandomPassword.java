/*
 * @(#)RandomPassword.java  2002-07-26
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
/**
 * This class is used to generate random passwords.
 *
 * @author hughes monget
 */
public class RandomPassword {


    //////////////////
    // CLASS FIELDS //
    //////////////////

    /**
     * A list of lowercase characters.
     */
    public static final String LOWERS_LIST = "abcdefghijklmnopqrstuvwxyz";

    /**
     * A list of uppercase characters.
     */
    public static final String UPPERS_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * A list of digits.
     */
    public static final String DIGITS_LIST = "0123456789";

    /**
     * A list of other characters as punctuation.
     */
    public static final String OTHERS_LIST =
            "'!?,.:;@#$%&*+-()<>[]{}|_"; // Printable alphabet

    /**
     * The constructor mask for lowercase characters.
     */
    public static final int LOWERS  = 1;

    /**
     * The constructor mask for upperscase characters.
     */
    public static final int UPPERS  = 2;

    /**
     * The constructor mask for digits.
     */
    public static final int DIGITS  = 4;

    /**
     * The constructor mask for others characters.
     */
    public static final int OTHERS  = 8;

    /**
     * The constructor mask for ALL chars.
     */
    public static final int ALLS = LOWERS + UPPERS + DIGITS + OTHERS;

    /**
     * the array of masks.
     */
    public static final int MASK_ARRAY[] = {LOWERS, UPPERS, DIGITS,  OTHERS};

    /////////////////////
    // INSTANCE FIELDS //
    /////////////////////

    /**
     * A vector of <code>RandomCharacter()</code> object.
     */
    private RandomVector _vectRC;

    /**
     * The length of passwords to generate (must be positive).
     */
    private int _length;

    /**
     * the total count of characters that can be choosen.
     */
    private int _totalChars;


    //////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
     * Constructor.
     *
     * @param length the length of password to return.
     *.@param mask a type of char (mask).
     */
    public RandomPassword(int length, int mask) {
        this.setLength(length);
        this.initialize(mask);
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    /**
     * We set the length of passwords.
     *
     * @param length the length of passwords.
     */
    private void setLength(int length) {

        if (length > 0) {
            _length = length;
        } else {
            throw new JPassGenRuntimeException("** password can't be empty !");
        }
    }

    /**
     * We get the length of passwords.
     *
     * @return the length of passwords.
     */
    int getLength() {

        return _length;
    }

    /**
     * We generate one new password.
     *
     * @return the generated password.
     */
    public String getNext() {

        StringBuffer password = new StringBuffer();
        int length  = getLength();
        int shuffle = length - 1;
        int size    = _vectRC.size();

        _vectRC.scramble();

        // Generate the password.
        RandomCharacter rc;
        for (int ii = 0 ; ii < length ; ii++) {
            rc = (RandomCharacter) _vectRC.elementAt(ii % size);
            password.append(rc.get());
        }

        // Scramble the password.
        // (See "Applied Cryptography" by Bruce Schneier)
        for (int ii = shuffle ; ii > 0 ; ii--) {
            int iiPermut = Math.abs(RandomSingleton.getInstance().nextInt())
                    % (ii + 1);
            if (iiPermut != ii) {
                char permutChar  = password.charAt(iiPermut);
                password.setCharAt(iiPermut, password.charAt(ii));
                password.setCharAt(ii, permutChar);
            }
        }

        return password.toString();
    }

    /**
     * We return the total count of characters that can be choosen.
     *
     * @return  characters range
     */
    int getCharsRange() {

        return _totalChars;
    }

    /**
     * We calculate the "pseudo"-entropy of passwords.
     * (Try "diceware FAQ" in Google or
     * http://world.std.com/~reinhold/dicewarefaq.html)
     *
     * @return entropy of passwords
     */
    float getEntropy() {

        return (float) (getLength() * Math.log(getCharsRange())
              / Math.log(2.0f));
    }

    // TO DO: à ré-écrire sans utiliser les codes en dur.
    // cf setOptions() de GUI
    /**
     * We initialize the type of characters to return.
     *
     *.@param mask sum of types of characters.
     */
    private void initialize(int mask) {

        _vectRC = new RandomVector();
        _totalChars = 0;

        RandomCharacter rc;

        if ((mask & RandomPassword.LOWERS) == RandomPassword.LOWERS) {
            rc = new RandomCharacter(RandomPassword.LOWERS_LIST);
            _vectRC.add(rc);
            _totalChars += rc.getCount();
        }
        if ((mask & RandomPassword.UPPERS) == RandomPassword.UPPERS) {
            rc = new RandomCharacter(RandomPassword.UPPERS_LIST);
            _vectRC.add(rc);
            _totalChars += rc.getCount();
        }
        if ((mask & RandomPassword.DIGITS) == RandomPassword.DIGITS) {
            rc = new RandomCharacter(RandomPassword.DIGITS_LIST);
            _vectRC.add(rc);
            _totalChars += rc.getCount();
        }
        if ((mask & RandomPassword.OTHERS) == RandomPassword.OTHERS) {
            rc = new RandomCharacter(RandomPassword.OTHERS_LIST);
            _vectRC.add(rc);
            _totalChars += rc.getCount();
        }

        if (_vectRC.size() == 0) {
            throw new JPassGenRuntimeException(
                    "** passwords need at least one char type !");
        }
    }

} // End of RandomPassword.java
