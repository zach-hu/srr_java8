/*
 * @(#)RandomCharacter.java  2002-07-26
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
 * This class is used to generate one random character.
 *
 * @author hughes monget
 */
class RandomCharacter {

    /////////////////////
    // INSTANCE FIELDS //
    /////////////////////

    /**
     * The characters list.
     */
    private String _characters;

    /**
     * The count of chars.
     */
    private int _count;

    //////////////////
    // CONSTRUCTORS //
    //////////////////

    /**
     * Constructor.
     *
     * @param characters the list of the characters.
     */
    RandomCharacter(String characters) {

        this.initialize(characters);
    }

    //////////////////////
    // INSTANCE METHODS //
    //////////////////////

    /**
     * We generate a random character.
     *
     * @return a random character.
     */
    char get() {

        return _characters.charAt(
                Math.abs(RandomSingleton.getInstance().nextInt()) % _count);
    }

    /**
     * We return the count of characters.
     *
     * @return the characters count.
     */
    int getCount() {

        return _count;
    }

    /**
     * We initialize the list of characters.
     *
     * @param characters a string of characters.
     */
    private void initialize(String characters) {

        _count = characters.length();
        if (_count > 0) {
            _characters = characters.intern();
        } else {
            throw new JPassGenRuntimeException(
                    "** characters can't be empty !");
        }
    }

} // End of RandomCharacter.java
